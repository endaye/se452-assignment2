<%@ page import="order.order.OrderItem" %>
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
                    <%
                        if (!helper.isLoggedin()) {
                            session.setAttribute("login_msg", "Please Login to add items to cart");
                            response.sendRedirect("Login");
                        }
                    %>
                    <h2>Cart(<%= helper.CartCount()%>)</h2>
                    <%
                        if (helper.CartCount() > 0) {
                    %>
                    <table>
                        <tr>
                            <th>#</th>
                            <th>Item</th>
                            <th>Price</th>
                        </tr>
                        <%
                            int i = 1;
                            double total = 0;
                            for (OrderItem oi : helper.getCustomerOrders()) {
                        %>
                        <tr>
                            <td><%= i%></td>
                            <td><%= oi.getName()%></td>
                            <td>$<%= oi.getPrice()%></td>
                        </tr>
                        <%
                                total += oi.getPrice();
                                i++;
                            }
                        %>
                        <tr>
                            <th></th>
                            <th>Total</th>
                            <th>$<%= total%></th>
                        </tr>
                    </table>
                    <div>
                        <a class='button' href='CheckOut'>
                            Check Out
                        </a>
                    </div>
                    <%
                    } else {
                    %>
                    <h4 style='color:red'>Your Cart is empty</h4>
                    <%
                        }
                    %>
                </article>
            </section>
            <!-- end #content -->
            <%@ include file="site_sidebar.jsp" %>
        </div>
        <%@ include file="site_footer.jsp"%>
    </div>
</body>
</html>
