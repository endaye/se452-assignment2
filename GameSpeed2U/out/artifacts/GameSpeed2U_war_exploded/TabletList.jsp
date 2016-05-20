<%@ page import="item.tablet.Tablet" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="item.tablet.TabletHashMap" %>
<%@ page import="java.util.Map" %>
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
                        String name = null;
                        String CategoryName = request.getParameter("maker");
                        HashMap<String, Tablet> hm = new HashMap<String, Tablet>();

                        if (CategoryName == null) {
                            hm.putAll(TabletHashMap.apple);
                            hm.putAll(TabletHashMap.microsoft);
                            hm.putAll(TabletHashMap.samsung);
                            name = "";
                        } else {
                            if (CategoryName.equals("apple")) {
                                hm.putAll(TabletHashMap.apple);
                                name = TabletHashMap.string_apple;
                            } else if (CategoryName.equals("microsoft")) {
                                hm.putAll(TabletHashMap.microsoft);
                                name = TabletHashMap.string_microsoft;
                            } else if (CategoryName.equals("samsung")) {
                                hm.putAll(TabletHashMap.samsung);
                                name = TabletHashMap.string_samsung;
                            }
                        }
                    %>
                    <h2><%= name%> Tablet</h2>
                    <%
                        for (Map.Entry<String, Tablet> entry : hm.entrySet()) {
                            Tablet tablet = entry.getValue();
                    %>
                    <div class='item'>
                        <div class='item-image'>
                            <img src='images/tablets/<%= tablet.getImage()%>' alt='<%= tablet.getName()%>'/>
                        </div>
                        <div class='item-title'>
                            <h3>
                                <%= tablet.getName()%>
                            </h3>
                            <strong>
                                $<%= tablet.getPrice()%>
                            </strong>
                        </div>
                        <div class='item-detail'>
                            <ul>
                                <li>
                                    <span class='item-button'>
                                        <a class='button' href='Cart?id=<%= entry.getKey()%>&type=tablets&maker=<%= tablet.getRetailer().toLowerCase()%>'>
                                            Buy Now
                                        </a>
                                    </span>
                                </li>
                                <li>
                                    <span class='item-button'>
                                        <a class='button' href='Review?id=<%= entry.getKey()%>&type=tablets&maker=<%= tablet.getRetailer().toLowerCase()%>'>
                                            Reviews
                                        </a>
                                    </span>
                                </li>
                            </ul>
                        </div>
                    </div>
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
