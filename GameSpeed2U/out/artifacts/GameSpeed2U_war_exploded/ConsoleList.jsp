<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="item.console.Console" %>
<%@ page import="item.console.ConsoleHashMap" %>
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
                        HashMap<String, Console> hm = new HashMap<String, Console>();
                        if (CategoryName == null) {
                            hm.putAll(ConsoleHashMap.microsoft);
                            hm.putAll(ConsoleHashMap.sony);
                            hm.putAll(ConsoleHashMap.nintendo);
                            name = "";
                        } else {
                            if (CategoryName.equals("microsoft")) {
                                hm.putAll(ConsoleHashMap.microsoft);
                                name = ConsoleHashMap.string_microsoft;
                            } else if (CategoryName.equals("sony")) {
                                hm.putAll(ConsoleHashMap.sony);
                                name = ConsoleHashMap.string_sony;
                            } else if (CategoryName.equals("nintendo")) {
                                hm.putAll(ConsoleHashMap.nintendo);
                                name = ConsoleHashMap.string_nintendo;
                            }
                        }
                    %>
                    <h2><%= name%> Console</h2>
                    <%
                        for (Map.Entry<String, Console> entry : hm.entrySet()) {
                            Console console = entry.getValue();
                    %>
                    <div class='item'>
                        <div class='item-image'>
                            <img src='images/consoles/<%=console.getImage()%>' alt='<%=console.getName()%>'/>
                        </div>
                        <div class='item-title'>
                            <h3>
                                <%=console.getName()%>
                            </h3>
                            <strong>
                                $<%=console.getPrice()%>
                            </strong>
                        </div>
                        <div class='item-detail'>
                            <ul>
                                <li>
                                    <span class='item-button'>
                                        <a class='button' href='Cart?id=<%=entry.getKey()%>&type=consoles&maker=<%=console.getRetailer().toLowerCase()%>'>
                                            Buy Now
                                        </a>
                                    </span>
                                </li>
                                <li>
                                    <span class='item-button'>
                                        <a class='button' href='AccessoryList?maker=<%=console.getRetailer().toLowerCase()%>&console=<%=entry.getKey().toLowerCase()%>'>
                                            View Accessories
                                        </a>
                                    </span>
                                </li>
                                <li>
                                    <span class='item-button'>
                                        <a class='button' href='Review?id=<%=entry.getKey()%>&type=consoles&maker=<%=console.getRetailer().toLowerCase()%>'>
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
