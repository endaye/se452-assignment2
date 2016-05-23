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
                    <h2>Account</h2>
                    <%
                        User user = helper.getUser();
                        if (user != null) {
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
