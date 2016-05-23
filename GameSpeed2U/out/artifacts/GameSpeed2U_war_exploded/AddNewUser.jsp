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
            <jsp:useBean id="reg" class="user.bean.RegistrationBean" scope="request"/>
            <%
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String repassword = request.getParameter("repassword");
                String usertype = "customer";
                String error_msg = "";

                if(!helper.isLoggedin()) {
                    usertype = request.getParameter("usertype");
                }
                if(password != null && password.isEmpty() && !password.equals(repassword)) {
                    error_msg = "Passwords doesn't match!";
                } else if (error_msg != null || error_msg.isEmpty() || error_msg.equals("")) {
                    HashMap<String, User> hm = new HashMap<String, User>();
                    if (usertype != null && usertype.equals("customer")) {
                        hm.putAll(UserHashMap.customer);
                    } else if (usertype != null && usertype.equals("retailer")) {
                        hm.putAll(UserHashMap.retailer);
                    } else if (usertype != null && usertype.equals("manager")) {
                        hm.putAll(UserHashMap.manager);
                    }

                    if (username == null || username.isEmpty()) {
                        error_msg = "Username is Empty";
                    } else if (password == null || username.isEmpty()) {
                        error_msg = "Password is Empty";
                    } else if (hm.containsKey(username)){
                        error_msg = "Username already exist as " + usertype;
                    }
                    else {
                        User user = new User(username,password,usertype);
                        if (usertype != null && usertype.equals("customer")) {
                            UserHashMap.customer.put(username, user);
                        } else if (usertype != null && usertype.equals("retailer")) {
                            UserHashMap.retailer.put(username, user);
                        } else if (usertype != null && usertype.equals("manager")) {
                            UserHashMap.manager.put(username, user);
                        }
                        session.setAttribute("error_msg", "Your " + usertype + " account has been created. Please login");
                    }
                }

            %>
            <div class='post' style='float: none; width: 100%'>
                <h2 class='title meta'>
                    <a style='font-size: 24px;'>
                        Add New User
                    </a>
                </h2>
                <div class='entry'>
                    <div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>
                        <%
                            if(error_msg != null && !error_msg.isEmpty() && !error_msg.equals("")) {
                        %>
                        <h4 style='color:red'><%=error_msg%></h4>
                        <%
                            }
                        %>
                        <form method='post' action='AddNewUser.jsp'>
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
                                        <h3>Re-Password</h3>
                                    </td>
                                    <td>
                                        <input type='password' name='repassword' value='' class='input' required></input>
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
                            </table>
                            <input type='submit' class='btnbuy' name='ByUser' value='Create User' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>
                        </form>
                    </div>
                </div>
            </div>
            <!-- end #content -->
        </div>
        <%@ include file="site_footer.jsp"%>
    </div>
</div>
</body>
</html>
