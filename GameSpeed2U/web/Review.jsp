<%@ page import="user.account.User" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="user.account.UserHashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>GameSpeed</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link href="style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<div id="container">
    <%@ include file="site_header.jsp"%>
    <div id='page'>
        <!-- start #content -->
        <div id="body">
            <section id="content">
                <article class="expanded">
                    <h2>Review</h2>
                    <%
                        String id = request.getParameter("id");
                        String type = request.getParameter("type");
                        String maker = request.getParameter("maker");
                        String access = request.getParameter("access");
                        String text = request.getParameter("text");

                        // Check login
                        if (!helper.isLoggedin()) {
                            session.setAttribute("login_msg", "Please Login to add items to cart");
                            response.sendRedirect("Login");
                            return;
                        }

                        if (id != null && type != null && maker != null) {
                            helper.storeReview(id, type, maker, access, text);
                        }

                        if (text != null && !text.isEmpty()) {
                    %>
                    <h4>Thank you.</h4>
                    <%
                    } else {
                        if (text != null && text.trim().isEmpty()) {
                    %><h4>Your form is empty. Please submit again.</h4><%
                    }
                %>
                    <form id='review' method='get'>
                        <input type='hidden' name='id' value='"+ id +"'>
                        <input type='hidden' name='type' value='"+ type +"'>
                        <input type='hidden' name='maker' value='"+ maker +"'>
                        <input type='submit' class='button' value='Submit Review'>
                    </form>
                    <textarea rows='20' cols='80' name='text' form='review'>Enter text here...</textarea>
                    <%
                        }
                    %>
                    <table>
                        <tr>
                            <td><h3>Username</h3></td>
                            <td><%=user.getName()%></td>
                        </tr>
                        <tr>
                            <td><h3>Password</h3></td>
                            <td><%=user.getPassword()%></td>
                        </tr>
                        <tr>
                            <td><h3>User Type</h3></td>
                            <td><%=user.getUsertype()%></td>
                        </tr>
                    </table>
                    <div><a class='button' href='OrderHistory.jsp'>Order History</a></div>
                    <div><a class='button' href='PersonalInfo.jsp'>Profile</a></div>
                    <%
                        }
                    %>
                </article>
            </section>
            <!-- end #content -->
        </div>
        <%@ include file="site_footer.jsp"%>
    </div>
</div>
</body>
</html>
