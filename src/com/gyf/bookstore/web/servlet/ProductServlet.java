package com.gyf.bookstore.web.servlet;


import com.gyf.bookstore.domain.PageResult;
import com.gyf.bookstore.domain.Product;
import com.gyf.bookstore.service.ProductService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/product/*")
public class ProductServlet extends Base {
    ProductService ps = new ProductService();

    //添加图书
    protected void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, FileUploadException {
        Product product = new Product();
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setSizeThreshold(1024 * 1024 * 1024);//设置最大文件上传大小1G;
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        List<FileItem> fileItems = servletFileUpload.parseRequest(req);
        Map<String, String> map = new HashMap<>();
        String url = "";
        for (FileItem item : fileItems) {
            if (item.isFormField()) {
                map.put(item.getFieldName(), item.getString("utf-8"));
            } else {//上传项
                String name = item.getName();//文件名
                String dir = "booksimg";
                //绝对路径
                File realDir = new File(req.getServletContext().getRealPath("/") + dir);
                url = dir + File.separator + File.separator + name; //图书保存页面路径
                File realUrl = new File(realDir + File.separator + name);
                if (!realDir.exists()) {
                    realDir.mkdir();
                }
                try {
                    item.write(realUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //封装数据
        try {
            BeanUtils.populate(product, map);
            product.setImgurl(url);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        int count = ps.addProduct(product);
        if (count == 1) {//成功
//            req.setAttribute("msg","添加成功");
//            req.getRequestDispatcher("/admin/login/home.jsp").forward(req,resp);
            resp.getWriter().write("图书添加成功");
        } else {
            req.setAttribute("msg", "添加失败");
            req.getRequestDispatcher("/admin/login/home.jsp").forward(req, resp);
        }

    }

    //书籍详情
    protected void productInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idstr = req.getParameter("id");
        int id = Integer.parseInt(idstr);
        Product book = ps.findBookById(id);
        if (book != null) {
            req.setAttribute("book", book);
            req.getRequestDispatcher("/product_info.jsp").forward(req, resp);
        } else {
            req.setAttribute("msg", "错误");
            req.getRequestDispatcher("/product_info.jsp").forward(req, resp);
        }
    }

    //分类查询书籍
    protected void showProductByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //获取参数
        String category = request.getParameter("category");//分类
        String page = request.getParameter("page");//显示页数
        //2.判断
        int currentPage = 1;
        int pageCount = 5;//每页显示5条数据,这个内部定义，不让外面传参数
        if (page != null) {
            currentPage = Integer.parseInt(page);
        }
        //3.调用service
        PageResult<Product> pr = ps.findPageBooks(currentPage, pageCount, category);

        //4.跳转
        request.setAttribute("pr", pr);
        request.setAttribute("category", category);
        request.getRequestDispatcher("/product_list.jsp").forward(request, response);
    }

    //模糊查询图书
    protected void findProductBySearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        String pageStr = req.getParameter("page");
        //设置默认值
        int currentPage = 1;
        if (pageStr != null) {
            currentPage = Integer.parseInt(pageStr);
        }
        int pageCount = 4;
        ProductService service = new ProductService();
        PageResult<Product> pr = service.findBookByName(currentPage, pageCount, name);
        if (pr == null) {
            req.setAttribute("msg", "网络崩溃了。。。");
            resp.getWriter().write("网络崩溃了。。。");
        } else {
            req.getSession().setAttribute("pr", pr);
            req.getSession().setAttribute("name", name);
            if (pr.getTotalCount() == 0) {
                req.getSession().setAttribute("searchBook", "本站暂时没有找到该类书籍");
            }
            resp.sendRedirect(req.getContextPath() + "/product_search_list.jsp");
        }

    }

    //多条件查询图书
    protected void findProductByManyCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取到条件
        String id = request.getParameter("id");
        String category = request.getParameter("category");
        String name = request.getParameter("name");
        String minprice = request.getParameter("minprice");
        String maxprice = request.getParameter("maxprice");
        String currentPageStr = request.getParameter("page");
        String pageCountStr = request.getParameter("pageCount");
        //设置默认分页数据
        int currentPage = 1;
        int pageCount = 5;
        if (currentPageStr != null && !"".equals(currentPageStr)) {
            currentPage = Integer.parseInt(currentPageStr);
        }
        if (pageCountStr != null && !"".equals(pageCountStr)) {
            pageCount = Integer.parseInt(pageCountStr);
        }
        //条件传给service
        PageResult<Product> pr = ps.findBookIf(id, category, name, minprice, maxprice, currentPage, pageCount);
        request.setAttribute("pr", pr);
        request.getSession().setAttribute("id", id);
        request.setAttribute("category", category);
        request.setAttribute("name", name);
        request.setAttribute("minprice", minprice);
        request.setAttribute("maxprice", maxprice);
        if (pr != null) {
            request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/admin/products/searchfail.jsp").forward(request, response);
        }
    }

    //id 删除图书
    protected void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int count = ps.deleteProduct(id);
        if (count == 0) {
            resp.getWriter().write("删除失败");
        } else {
            resp.getWriter().write("删除成功");
        }

    }

    //id修改图书
    protected void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, FileUploadException {
        Product product = new Product();
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setSizeThreshold(1024 * 1024 * 1024);//设置最大文件上传大小1G;
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        List<FileItem> fileItems = servletFileUpload.parseRequest(req);
        Map<String, String> map = new HashMap<>();
        String url = "";
        for (FileItem item : fileItems) {
            if (item.isFormField()) {
                map.put(item.getFieldName(), item.getString("utf-8"));
            } else {//上传项
                String name = item.getName();//文件名
                String dir = "booksimg";
                //绝对路径
                File realDir = new File(req.getServletContext().getRealPath("/") + dir);
                url = dir + File.separator + File.separator + name; //图书保存页面路径
                File realUrl = new File(realDir + File.separator + name);
                if (!realDir.exists()) {
                    realDir.mkdir();
                }
                try {
                    item.write(realUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //封装数据
        try {
            BeanUtils.populate(product, map);
            product.setImgurl(url);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        int count = ps.updateProduct(product.getId(), product.getName(), product.getPrice(), product.getCategory(),
                product.getPnum(), product.getImgurl(), product.getDescription());
        if (count == 1) {//成功
            resp.getWriter().write("图书修改成功");
        } else {
            resp.getWriter().write("图书修改失败");
        }

    }
    //后台id查询图书 >> 修改页面
    protected void findProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取到条件
        int id = Integer.parseInt(request.getParameter("id"));

        Product book = ps.findBookById(id);
        if (book != null) {
            request.setAttribute("book", book);
            request.getRequestDispatcher("/admin/products/edit.jsp").forward(request, response);
        } else {
             response.getWriter().write("图书异常");
        }
    }

}

