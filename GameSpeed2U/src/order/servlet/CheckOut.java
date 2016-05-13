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

@WebServlet(name = "CheckOut", urlPatterns = "/CheckOut")
public class CheckOut extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Helper helper = new Helper(request, pw);
        String confirmNum = helper.orderConfirm();
        displayCheckout(request, response, confirmNum);
    }

    protected void displayCheckout(HttpServletRequest request,
                                   HttpServletResponse response,
                                   String confrimNum)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Helper helper = new Helper(request, pw);

        helper.printHtml("site_header.html");
        pw.print("<div id='body'><section id='content'><article class='expanded'>");
        pw.print("<h2>Check Out</h2>");
        try {
            if (!helper.isLoggedin()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("login_msg", "Please Login to add items to cart");
                response.sendRedirect("Login");
                return;
            }
            pw.print("<h3>Congratulations, " + helper.username() + "! Your order is confirmed.</h3>");
            OrderHistory oh = helper.getOrderHistory(confrimNum);
            if (oh.getItems().size() > 0) {
                pw.print("<table>");
                pw.print("<tr><th>Order #</th><th>" + oh.getId() +"</th><th></th></tr>");
                pw.print("<tr><th>Date</th><th>" + oh.getDate() +"</th><th></th></tr>");
                pw.print("<tr><th>Total</th><th>$" + oh.getTotalPrice() +"</th><th></th></tr>");
                int i = 1;
                for (OrderItem oi : oh.getItems()) {
                    pw.print("<tr>");
                    pw.print("<td>"+i+".</td><td>"+oi.getName()+"</td><td>$"+oi.getPrice()+"</td>");
                    pw.print("</tr>");
                    i++;
                }
                pw.print("<tr><th></th><th>Total</th><th>$"+oh.getTotalPrice()+"</th></table>");
            } else {
                pw.print("<h4 style='color:red'>Oops! Checkout Error!</h4>");
            }
        } catch (Exception e) {
            pw.print("<h4 style='color:red'>Oops! Checkout Error!</h4>");
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