<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="main.Helper" %>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>GameSpeed</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link href="style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<div id="container">
    <header>
        <h1><a href="Home.jsp">Game<span>Speed</span></a></h1>
        <h2>Made by Yuancheng Zhang</h2>
    </header>

    <nav>
        <ul>
            <li class="start selected"><a href="Home.jsp">Home</a></li>
            <%
                if (session.getAttribute("usertype")!=null && session.getAttribute("usertype").toString().equals("manager")) {
            %>
            <li><a href="AddNewUser">Add New User</a></li>
            <li><a href="AllOrderHistory">Order History</a></li>
            <%
            } else {
            %>
            <li><a href="ConsoleList">Consoles</a></li>
            <li><a href="GamesList">Games</a></li>
            <li><a href="TabletList">Tablets</a></li>
            <li><a href="Trending">Trending</a></li>
            <%
                }
            %>
            <!-- end #header-->
            <span id='login'>
                <%
                    Helper helper = new Helper(request);
                    if(session.getAttribute("username")!=null) {
                        String username = session.getAttribute("username").toString();
                        username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
                %>
                    <li><a>Hello, <%= username%></a></li>
                    <li><a href='Account'>Account</a></li>
                    <li><a href='Logout'>Logout</a></li>
                <%
                } else {
                %>
                    <li><a href='Login'>Login</a></li>
                <%
                    }
                %>
			    <li class='end'>
                    <a href='Cart'>Cart(<%= Integer.toString(helper.CartCount())%>)</a>
                </li>
            </span>
        </ul>
    </nav>
    <div id='page'>
        <!-- start #content -->
        <div id="body">
            <section id="content">
                <article>
                    <h2>
                        Welcome to GameSpeed
                    </h2>

                    <div class="article-info">Posted on <time datetime="now">14 May</time> by <a href="#" rel="author">Yuancheng Zhang</a>
                    </div>
                    <img src="images/site/consoles.png" style="width: 600px; display: block; margin-left: auto; margin-right: auto" />
                    <p>
                        The world trusts us to deliver SPEEDY service for video-gaming fans. <br>
                        We beat our competitors in all aspects. <br>
                        Price-Match Gauranteed.
                    </p>
                </article>

                <article class="expanded">

                    <h2>Best Sellers</h2>
                    <div class="article-info">most popular this week</div>
                    <div class="item">
                        <div class="item-image">
                            <img src="images/consoles/xbox1.jpg" alt="Xbox One"/>
                        </div>
                        <div class="item-title">
                            <h3>Xbox One</h3>
                            <strong>$399.99</strong>
                        </div>
                        <div class="item-detail">
                            <ul>
                                <li><span class="item-button"><a class="button" href="Cart?id=xboxone&type=consoles&maker=microsoft">Buy Now</a></span></li>
                                <li><span class="item-button"><a class="button" href="AccessoryList?maker=microsoft&console=xboxone">View Accessories</a></span></li>
                                <li><span class="item-button"><a class="button" href="Review?id=xboxone&type=consoles&maker=microsoft">Reviews</a></span></li>
                            </ul>

                        </div>
                    </div>

                    <div class="item">
                        <div class="item-image">
                            <img src="images/consoles/PS4-console-bundle.jpg" alt="PS4"/>
                        </div>
                        <div class="item-title">
                            <h3>PlayStation 4</h3>
                            <strong>$349.49</strong>
                        </div>
                        <div class="item-detail">
                            <ul>
                                <li><span class="item-button"><a class="button" href="Cart?id=ps4&type=consoles&maker=sony">Buy Now</a></span></li>
                                <li><span class="item-button"><a class="button" href="AccessoryList?maker=sony&console=ps4">View Accessories</a></span></li>
                                <li><span class="item-button"><a class="button">Reviews</a></span></li>
                            </ul>
                        </div>
                    </div>
                </article>
            </section>

            <!-- end #content -->


            <!-- start #sidebar-->
            <aside class="sidebar">
                <ul>
                    <%
                        if (session.getAttribute("usertype")!=null && session.getAttribute("usertype").toString().equals("manager")) {
                    %>
                    <%-- salesman sidebar --%>
                    <li>
                        <h4>Management</h4>
                        <ul>
                            <li id="first"><a href="AddNewUser">Add New User</a></li>
                            <li><a href="AllOrderHistory">Order History</a></li>
                        </ul>
                    </li>
                    <%
                    } else {
                    %>
                    <%-- customer sidebar --%>
                    <li>
                        <h4>Search Product</h4>
                        <ul>
                            <li class="text">
                                <div name="autofillform">
                                    <input type="text" name="complete-field" value="" class="input" id="complete-field" onkeyup="doCompletion()" autocomplete="off" placeholder="search here.." style="padding: 5px; font-size: 16px;" />
                                    <div id="auto-row">
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <h4>Consoles</h4>
                        <ul>
                            <li id="first"><a href="ConsoleList?maker=microsoft">Microsoft</a></li>
                            <li><a href="ConsoleList?maker=sony">Sony</a></li>
                            <li><a href="ConsoleList?maker=nintendo">Nintendo</a></li>
                        </ul>
                    </li>

                    <li>
                        <h4>Games</h4>
                        <ul>
                            <li><a href="GamesList?maker=electronicArts">Electronic Arts</a></li>
                            <li><a href="GamesList?maker=activision">Activision</a></li>
                            <li><a href="GamesList?maker=takeTwoInteractive">Take-Two Interactive</a></li>
                        </ul>
                    </li>
                    <li>
                        <h4>Tablets</h4>
                        <ul>
                            <li><a href="TabletList?maker=apple">Apple</a></li>
                            <li><a href="TabletList?maker=microsoft">Microsoft</a></li>
                            <li><a href="TabletList?maker=samsung">Samsung</a></li>
                        </ul>
                    </li>
                    <%
                        }
                    %>
                </ul>
            </aside>
            <div class="clear"></div>
        </div>

        <!-- end #sidebar-->

        <!-- start #footer -->
        <div style="clear: both;">&nbsp;</div>

        <footer>
            <div class="footer-bottom">
                <p>&copy; 2016 GameSpeed. All rights reserved.</p>
                <p>DePaul University - SE450 Assignment_2 | Student: Zhang, Yuancheng | Instructor: Bader, Atef</p>
            </div>

        </footer>
    </div>
    <!-- end #footer -->

</body>
</html>
