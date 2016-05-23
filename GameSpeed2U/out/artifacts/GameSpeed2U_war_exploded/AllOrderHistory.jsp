<%@ page import="order.history.OrderHistory" %>
<%@ page import="java.util.ArrayList" %>
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
                            return;
                        }
                    %>
                    <h2>All Order History</h2>
                    <%
                        ArrayList<OrderHistory> ohs = helper.getAllOrderHistory();
                        if (ohs.size() > 0) {
                            String orderId  = request.getParameter("orderId");
                            if (orderId != null) {
                                helper.removeOrder(orderId);
                            }
                            for (OrderHistory oh: ohs) {
                                if (oh.getItems().size() > 0) {
                    %>
                        <form method='post' action='AllOrderHistory'>
                        <table>
                        <tr>
                            <th>Order #</th>
                            <th><%=oh.getId()%></th>
                            <th></th>
                        </tr>
                        <tr>
                            <th>Buyer</th>
                            <th><%=oh.getUser()%></th>
                            <th></th>
                        </tr>
                        <tr>
                            <th>Order Date</th>
                            <th><%=oh.getDate()%></th>
                            <th></th>
                        </tr>
                        <tr>
                            <th>Delivery Date</th>
                            <th><%=oh.getDelivery()%></th>
                            <th></th>
                        </tr>
                        <tr>
                            <th>Total</th>
                            <th>$<%=oh.getTotalPrice()%></th>
                            <th></th>
                        </tr>
                        <%
                            int i = 1;
                            for (OrderItem oi : oh.getItems()) {
                                %>
                                <tr>
                                <td><%=i%>.</td><td><%=oi.getName()%></td><td>$<%=oi.getPrice()%></td>
                                </tr>
                            <%
                                i++;
                            }
                        %>
                        <tr>
                            <th></th>
                            <th>Total</th>
                            <th>$<%=oh.getTotalPrice()%></th></tr>
                        <tr>
                            <th><input hidden name='orderId' value='<%=oh.getId()%>'/></th>
                            <th><input type='submit' class='button' name='ByUser' value='Remove' style='float: right;'/></th>
                            <th><input type='submit' class='button' name='ByUser' value='Update' style='float: right;'/></th>
                        </tr>
                    </table>
                </form>
                    <%
                    } else {
                    %>
                    <h4 style='color:red'>Oops!</h4>
                    <%
                            }
                        }
                    } else {
                    %>
                    <h4 style='color:red'>No order history.</h4>
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
</div>
</body>
</html>
