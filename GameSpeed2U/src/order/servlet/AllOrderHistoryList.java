package order.servlet;

import main.Helper;
import order.history.OrderHistory;
import order.order.OrderItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "AllOrderHistory", urlPatterns = "/AllOrderHistory")
public class AllOrderHistoryList extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        displayOrderHistory(request, response);
    }

    protected void displayOrderHistory(HttpServletRequest request,
                                   HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Helper helper = new Helper(request, pw);

        helper.printHtml("site_header.html");
        pw.print("<div id='body'><section id='content'><article class='expanded'>");
        pw.print("<h2>All Order History</h2>");
        try {
            if (!helper.isLoggedin()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("login_msg", "Please Login to add items to cart");
                response.sendRedirect("Login");
                return;
            }
//            String sqlStatement = "SELECT id, date, delivery, user, status, total_price FROM Orders";
//            // Type 1: order;
//            pw.print(helper.sqlHelper(sqlStatement, 1));
            ArrayList<OrderHistory> ohs = helper.getAllOrderHistory();
            if (ohs.size() > 0) {
                String orderId  = request.getParameter("orderId");
                if (orderId != null) {
                    helper.removeOrder(orderId);
                }
                for (OrderHistory oh: ohs) {
                    if (oh.getItems().size() > 0) {
                        pw.print("<form method='post' action='AllOrderHistory'>");
                        pw.print("<table>");
                        pw.print("<tr><th>Order #</th><th>" + oh.getId() +"</th><th></th></tr>");
                        pw.print("<tr><th>Buyer</th><th>" + oh.getUser() +"</th><th></th></tr>");
                        pw.print("<tr><th>Order Date</th><th>" + oh.getDate() +"</th><th></th></tr>");
                        pw.print("<tr><th>Delivery Date</th><th>" + oh.getDelivery() + "</th><th></th></tr>");
                        pw.print("<tr><th>Total</th><th>$" + oh.getTotalPrice() +"</th><th></th></tr>");
                        int i = 1;
                        for (OrderItem oi : oh.getItems()) {
                            pw.print("<tr>");
                            pw.print("<td>"+i+".</td><td>"+oi.getName()+"</td><td>$"+oi.getPrice()+"</td>");
                            pw.print("</tr>");
                            i++;
                        }
                        pw.print("<tr><th></th><th>Total</th><th>$"+oh.getTotalPrice()+"</th></tr>");
                        pw.print("<tr><input hidden name='orderId' value='"+ oh.getId() +"'></input>");
                        pw.print("<th></th>");
                        pw.print("<th><input type='submit' class='button' name='ByUser' value='Remove' style='float: right;'></input></th>");
                        pw.print("<th><input type='submit' class='button' name='ByUser' value='Update' style='float: right;'></input></th></tr></table>");
                        pw.print("</form>");
                    } else {
                        pw.print("<h4 style='color:red'>Oops!</h4>");
                    }
                }
            } else {
                pw.print("<h4 style='color:red'>No order history.</h4>");
            }
        } catch (Exception e) {
            pw.print("<h4 style='color:red'>Oops!</h4>");
            System.out.println("error: " + e.toString());
        } finally {
            pw.print("</article></section>");
            helper.printHtml("site_sidebar.html");
            helper.printHtml("site_footer.html");
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
