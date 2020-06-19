package com.cjc.bookstore.web.servlet;

import com.cjc.bookstore.domain.Order;
import com.cjc.bookstore.domain.OrderItem;
import com.cjc.bookstore.domain.Product;
import com.cjc.bookstore.domain.User;
import com.cjc.bookstore.service.OrderService;
import com.cjc.bookstore.service.ProductService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/order/*")
public class OrderServlet extends Base {
    OrderService os = new OrderService();
    ProductService ps = new ProductService();
    //用户名查询订单
    protected void findOrderByUserId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        //处理未登录用户
        if (id == 0) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        //获取定单
        List<Order> list = os.findOrdersByUserId(id);
        request.setAttribute("orders", list);
        request.setAttribute("count", list.size());
        //转发
        request.getRequestDispatcher("/orderlist.jsp").forward(request, response);
    }
    //创建订单
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String receiverAddress = request.getParameter("receiverAddress");
        String receiverName = request.getParameter("receiverName");
        String receiverPhone = request.getParameter("receiverPhone");
        Order order = new Order();
        //获取当前登录用户的信息
        User user = (User) request.getSession().getAttribute("user");
        try {
            //1.把请求通过封闭到Order中
            BeanUtils.populate(order, request.getParameterMap());
            //2.设置用户
            order.setUser(user);
            //3.设置ID
            order.setId(UUID.randomUUID().toString());
            //4.设置定单详情
            double totalPrice = 0;
            Map<Product, Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
            List<OrderItem> orderItems = new ArrayList<OrderItem>();
            for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                OrderItem oi = new OrderItem();
                oi.setBuynum(entry.getValue());
                oi.setP(entry.getKey());
                oi.setOrder(order);
                orderItems.add(oi);
                totalPrice += entry.getKey().getPrice() * entry.getValue();
            }
            order.setOrderItems(orderItems);
            //5.设置定单时间和金额
            order.setOrdertime(new Date());
            order.setMoney(totalPrice);
            //6.订单保存
            os.addOrder(order);
            //7.进入支付页面
            request.getSession().setAttribute("order", order);
            response.sendRedirect(request.getContextPath() + "/pay.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //订单id删除订单
    protected void deleteOrderById(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("orderId");
        //处理未登录用户
        if (id == null) {
            resp.getWriter().write("该订单已不存在");
        }
        //删除订单
        int count = os.deleteOrderById(id);
        if (count == 1) {
            req.getRequestDispatcher("/orderlist.jsp").forward(req, resp);
        } else {
            resp.getWriter().write("订单删除失败,请重试！！！！");
        }
    }
   //支付订单
   protected void paySuccess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Order order =(Order) req.getSession().getAttribute("order");
       String id = order.getId();
       String orderId = req.getParameter("orderId");

       try {
           os.updateState(orderId);
       } catch (SQLException e) {
           e.printStackTrace();
       }
       Map<Product, Integer> cart = (Map<Product, Integer>) req.getSession().getAttribute("cart");
       //修改数据库图书库存及删除相应购物车
       List<OrderItem> orderItems = order.getOrderItems();
       for(OrderItem item : orderItems){
           Product p = item.getP();
           int pid = p.getId();
           int num = item.getBuynum();
           ps.updatePnum(pid,num);
           cart.remove(p);
       }
       req.getSession().removeAttribute("cart");
       req.getSession().removeAttribute("order");
       req.getSession().setAttribute("cart",cart);
//       req.getRequestDispatcher("/paySuccess.jsp").forward(req,resp);
       resp.sendRedirect(req.getContextPath()+"/paySuccess.jsp");
   }
   //增加购物车
   protected void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //1.根据id查找图书
      String idstr = request.getParameter("id");
      int id = Integer.parseInt(idstr);
      Product book = ps.findBookById(id);
      request.setCharacterEncoding("utf-8");
      //2.从session中获取购物车信息
      Map<Product,Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");

      //3.如果是空，创建一个购物车集合
      if(cart == null){
          cart = new HashMap<Product,Integer>();
      }
      //4.判断购物车中是否有当前书的记录
      if(cart.containsKey(book)){
          cart.put(book, cart.get(book) + 1);
      }else{
          cart.put(book, 1);
      }
      //5.重新保存到session中
      request.getSession().setAttribute("cart", cart);
      //6.继续购物或者结算查看购物车
      String html = "<a href='"+ request.getContextPath() +"/product/showProductByPage'>继续购物</a>";
      html += "&nbsp;&nbsp;&nbsp;&nbsp;<a href='" + request.getContextPath()  + "/cart.jsp'>查看购物车</a>";
      response.getWriter().write(html);
  }
   //结账跳转
   protected void settleAccounts(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
      // TODO Auto-generated method stub
      User user = (User) request.getSession().getAttribute("user");
      if(user == null){
          request.getRequestDispatcher("/login.jsp").forward(request, response);
      }else{
          request.getRequestDispatcher("/order.jsp").forward(request, response);
      }
  }
  //订单id查询订单
    protected void findOrderById(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        //处理未登录用户
        if(orderId == null){
            resp.getWriter().write("该订单异常，请重试！！！");
        }
        //获取定单
        Order order = os.findOrderByOrderId(orderId);
        //订单详情
        List<OrderItem> orderItems = os.findOrderItemByOrderId(orderId);
        //测试
      /*  for (OrderItem item: orderItems){
            System.out.println(item);
            System.out.println(item.getP());
        }*/
        order.setOrderItems(orderItems);
        req.getSession().setAttribute("order",order );
        //转发
        req.getRequestDispatcher("/orderInfo.jsp").forward(req, resp);
    }


}



