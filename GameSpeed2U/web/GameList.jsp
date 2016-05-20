<%@ page import="item.game.Game" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="item.game.GameHashMap" %>
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
                        HashMap<String, Game> hm = new HashMap<String, Game>();

                        if (CategoryName==null) {
                            hm.putAll(GameHashMap.electronicArts);
                            hm.putAll(GameHashMap.activision);
                            hm.putAll(GameHashMap.takeTwoInteractive);
                            name = "";
                        } else {
                            if(CategoryName.equals("electronicArts")) {
                                hm.putAll(GameHashMap.electronicArts);
                                name = GameHashMap.string_electronicArts;
                            }
                            else if (CategoryName.equals("activision")) {
                                hm.putAll(GameHashMap.activision);
                                name = GameHashMap.string_activision;
                            }
                            else if (CategoryName.equals("takeTwoInteractive")) {
                                hm.putAll(GameHashMap.takeTwoInteractive);
                                name = GameHashMap.string_takeTwoInteractive;
                            }
                        }
                    %>
                    <h2><%= name%> Game</h2>
                    <%
                        for (Map.Entry<String, Game> entry : hm.entrySet()) {
                            Game game = entry.getValue();
                    %>
                    <div class='item'>
                        <div class='item-image'>
                            <img src='images/games/<%= game.getImage()%>' alt='<%= game.getName()%>'/>
                        </div>
                        <div class='item-title'>
                            <h3>
                                <%= game.getName()%>
                            </h3>
                            <strong>
                                $<%= game.getPrice()%>
                            </strong>
                        </div>
                        <div class='item-detail'>
                            <ul>
                                <li>
                                    <span class='item-button'>
                                        <a class='button' href='Cart?id=<%= entry.getKey()%>&type=games&maker=<%= game.getRetailer().toLowerCase()%>'>
                                            Buy Now
                                        </a>
                                    </span>
                                </li>
                                <li>
                                    <span class='item-button'>
                                        <a class='button' href='Review?id=<%= entry.getKey()%>&type=games&maker=<%= game.getRetailer().toLowerCase()%>'>
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
