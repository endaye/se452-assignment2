<%@ page import="item.console.Console" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="item.console.ConsoleHashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="item.accessory.Accessory" %>
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
                        String CategoryName = request.getParameter("maker");
                        String ConsoleName = request.getParameter("console");
                        HashMap<String, Console> hm = new HashMap<String, Console>();
                        if(CategoryName.equals("microsoft")){
                            hm.putAll(ConsoleHashMap.microsoft);
                        }
                        else if(CategoryName.equals("sony")){
                            hm.putAll(ConsoleHashMap.sony);
                        }
                        else if(CategoryName.equals("nintendo")){
                            hm.putAll(ConsoleHashMap.nintendo);
                        }

                        Console console = hm.get(ConsoleName);
                    %>
                    <h2><%= console.getName()%> Accessories</h2>
                    <%
                        for(Map.Entry<String, Accessory> entry : console.getAccessories().entrySet()){
                            Accessory accessory = entry.getValue();
                    %>
                    <div class='item'>
                        <div class='item-image'>
                            <img src='images/accessories/<%= accessory.getImage()%>' alt='<%= accessory.getName()%>'/>
                        </div>
                        <div class='item-title'>
                            <h3>
                                <%= accessory.getName()%>
                            </h3>
                            <strong>
                                $<%= accessory.getPrice()%>
                            </strong>
                        </div>
                        <div class='item-detail'>
                            <ul>
                                <li>
                                    <span class='item-button'>
                                        <a class='button' href='Cart.jsp?id=<%= entry.getKey()%>&type=accessories&maker=<%= accessory.getRetailer().toLowerCase()%>&access=<%= ConsoleName%>'>
                                            Buy Now
                                        </a>
                                    </span>
                                </li>
                                <li>
                                    <span class='item-button'>
                                        <a class='button' href='Review.jsp?id=<%= entry.getKey()%>&type=accessories&maker=<%= accessory.getRetailer().toLowerCase()%>&access=<%= ConsoleName%>'>
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
</div>
</body>
</html>
