import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Account", urlPatterns = "/Account")
public class Account extends HttpServlet {
    final private String errorMsg = "<h3>Oops!</h3> <p>This user account doesn't login.<br>Please <a href='Login'>Login</a> again.</p>";

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        displayAccount(request, pw);
    }

    protected void displayAccount(HttpServletRequest request, PrintWriter pw)
            throws ServletException, IOException {

        Helper helper = new Helper(request, pw);
        helper.printHtml("site_header.html");
        pw.print("<div id='body'><section id='content'><article class='expanded'>");
        pw.print("<h2>Account</h2>");
        try {
            User user = helper.getUser();
            if (user != null) {
                pw.print("<table><tr><td><h3>Username</h3></td>" +
                        "<td>" + user.getName() + "</td></tr>" +
                        "<tr><td><h3>Password</h3></td>" +
                        "<td>" + user.getPassword()+ "</td></tr>" +
                        "<tr><td><h3>User Type</h3></td>" +
                        "<td>" + user.getUsertype()+ "</td></tr></table>");
                pw.print("<div><a class='button' href='OrderHistory'>Order History</a></div>");
                pw.print("<div><a class='button' href='PersonalInfo'>Profile</a></div>");
            }
            else {
                pw.print(errorMsg);
            }
        } catch (Exception e) {
            pw.print(errorMsg);
            System.out.println("error: " + e.toString());
        } finally {
            pw.print("</article></section>");

            helper.printHtml("site_sidebar.html");
            helper.printHtml("site_footer.html");
        }
    }
}
