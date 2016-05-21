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
            <jsp:useBean id="login" class="user.bean.Login" scope="request"/>
            <%
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String usertype = request.getParameter("usertype");

                HashMap<String, User> hm = new HashMap<String, User>();
                if (usertype != null && usertype.equals("customer")) {
                    hm.putAll(UserHashMap.customer);
                } else if (usertype != null && usertype.equals("retailer")) {
                    hm.putAll(UserHashMap.retailer);
                } else if (usertype != null && usertype.equals("manager")) {
                    hm.putAll(UserHashMap.manager);
                }
                User user = hm.get(username);
                if(user!=null) {
                    String user_password = user.getPassword();
                    if (password.equals(user_password)) {
                        session.setAttribute("username", user.getName());
                        session.setAttribute("usertype", user.getUsertype());
                        response.sendRedirect("Home.jsp");
                        return;
                    }
                    login.setError(true);
                }
            %>
            <div class='post' style='float: none; width: 100%'>
                <h2 class='title meta'>
                    <a style='font-size: 24px;'>Login</a>
                </h2>
                <div class='entry'>
                    <div style='width:400px; margin:25px; margin-left: auto; margin-right: auto;'>
                        <%
                            if (login.isError()) {
                        %>
                        <h4 style='color:red'>Please check your username, password and user type!</h4>
                        <%
                            }
                            if(session.getAttribute("login_msg")!=null) {
                        %>
                        <h4 style='color:red'><%= session.getAttribute("login_msg")%></h4>
                        <%
                                session.removeAttribute("login_msg");
                            }
                        %>

                        <form method='post' action='Login'>
                            <table style='width:100%'>
                                <tr>
                                    <td>
                                        <h3>Username</h3>
                                    </td>
                                    <td>
                                        <input type='text' name='username' value='' class='input' required></input>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <h3>Password</h3>
                                    </td>
                                    <td>
                                        <input type='password' name='password' value='' class='input' required></input>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <h3>User Type</h3>
                                    </td>
                                    <td>
                                        <select name='usertype' class='input'>
                                            <option value='customer' selected>Customer</option>
                                            <option value='retailer'>Store Manager</option>
                                            <option value='manager'>Salesman</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>
                                        <input type='submit' class='btnbuy' value='Login.jsp' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>
                                        <strong>
                                            <a class='' href='Registration' style='float: right;height: 20px margin: 20px;'>New User? Register here!</a>
                                        </strong>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
            </section>
            <!-- end #content -->
        </div>
        <%@ include file="site_footer.jsp"%>
    </div>

</div>
</body>
</html>
