package user;

import main.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Review", urlPatterns = "/Review")
public class Review extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Helper helper = new Helper(request, pw);
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        String maker = request.getParameter("maker");
        String access = request.getParameter("access");
        String text = request.getParameter("text");
        if (id != null && type != null && maker != null) {
            helper.storeReview(id, type, maker, access, text);
        }
        displayCart(request, response);
    }

    protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Helper helper = new Helper(request,pw);
        helper.printHtml("site_header.html");
        pw.print("<div id='body'><section id='content'><article class='expanded'>");
        pw.print("<h2>Item Review</h2>");
        try {
            if (!helper.isLoggedin()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("login_msg", "Please Login to add items to cart");
                response.sendRedirect("Login");
                return;
            }
            String id = request.getParameter("id");
            String type = request.getParameter("type");
            String maker = request.getParameter("maker");
            String access = request.getParameter("access");
            String text = request.getParameter("text");

            if (text != null && !text.isEmpty()) {
                pw.print("<h4>Thank you.</h4>");
            } else {
                if (text != null && text.trim().isEmpty()) {
                    pw.print("<h4>Your form is empty. Please submit again.</h4>");
                }
                pw.print("<form id='review' method='get'>" +
                        "<input type='hidden' name='id' value='"+ id +"'>" +
                        "<input type='hidden' name='type' value='"+ type +"'>" +
                        "<input type='hidden' name='maker' value='"+ maker +"'>" +
                        "<input type='submit' class='button' value='Submit Review'>" + "</form>");
                pw.print("<textarea rows='20' cols='80' name='text' form='review'>" +
                        "Enter text here...</textarea>");
                //pw.print("<div><a class='button' href='Review?id="+id+"&type="+type+"&maker="+maker+"&access="+access+"&text="+text+"'>Submit</a></div>");
            }
        } catch (Exception e) {
            pw.print("Oops!");
            System.out.println("error: " + e.toString());
        } finally {
            pw.print("</article></section>");
            helper.printHtml("site_sidebar.html");
            helper.printHtml("site_footer.html");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
