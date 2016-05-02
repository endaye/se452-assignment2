

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Cart", urlPatterns = "/Cart")
public class Cart extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Helper helper = new Helper(request, pw);
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        String maker = request.getParameter("maker");
        String access = request.getParameter("access");

        helper.storeProduct(id, type, maker, access);

        displayCart(request, response);
    }

	protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request,pw);
		if(!helper.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
		
		helper.printHtml("site_header.html");
        pw.print("<div id='body'><section id='content'><article class='expanded'>");
        pw.print("<h2>Cart("+helper.CartCount()+")</h2>");

        if (helper.CartCount() > 0) {
		pw.print("<table>");
		int i = 1;
		double total = 0;
		for (OrderItem oi : helper.getCustomerOrders()) {
			pw.print("<tr>");
			pw.print("<td>"+i+".</td><td>"+oi.getName()+"</td><td>$"+oi.getPrice()+"</td>");
			pw.print("</tr>");
			total = total +oi.getPrice();
			i++;
		}
		pw.print("<tr><th></th><th>Total</th><th>$"+total+"</th></table>");
		pw.print("<a style='float:right' class='button' href='CheckOut'>Check Out</a>");
		} else {
			pw.print("<h4 style='color:red'>Your Cart is empty</h4>");
		}

        pw.print("</article></section>");
		helper.printHtml("site_sidebar.html");
		helper.printHtml("site_footer.html");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
	}
}
