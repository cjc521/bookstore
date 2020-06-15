package com.gyf.bookstore.web.servlet;


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
     //添加图书
    protected void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, FileUploadException {

        Product product = new Product();
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setSizeThreshold(1024*1024*1024);//设置最大文件上传大小1G;
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        List<FileItem> fileItems = servletFileUpload.parseRequest(req);
        Map<String,String> map = new HashMap<>();
        String url="";
        for(FileItem item:fileItems){
            if(item.isFormField()){
                map.put(item.getFieldName(),item.getString("utf-8"));
            }else {//上传项
                String name = item.getName();//文件名
                String dir="booksimg";
                //绝对路径
                File realDir=new File(req.getServletContext().getRealPath("/")+dir);
                url=dir+File.separator+File.separator+name; //图书保存页面路径
                File realUrl = new File(realDir+File.separator+name);
                if(!realDir.exists()){
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
            BeanUtils.populate(product,map);
            product.setImgurl(url);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        ProductService service = new ProductService();
        int count = service.addProduct(product);
        if(count==1){//成功
//            req.setAttribute("msg","添加成功");
//            req.getRequestDispatcher("/admin/login/home.jsp").forward(req,resp);
            resp.getWriter().write("图书添加成功");
        }else{
            req.setAttribute("msg","添加失败");
            req.getRequestDispatcher("/admin/login/home.jsp").forward(req,resp);
        }

    }

    protected void productInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idstr = req.getParameter("id");
        int id = Integer.parseInt(idstr);
        ProductService ps = new ProductService();
        Product book = ps.findBookById(id);

        if(book != null){
            req.setAttribute("book", book);
            req.getRequestDispatcher("/product_info.jsp").forward(req, resp);
        }else {
            req.setAttribute("msg", "错误");
            req.getRequestDispatcher("/product_info.jsp").forward(req, resp);
        }
    }
}
