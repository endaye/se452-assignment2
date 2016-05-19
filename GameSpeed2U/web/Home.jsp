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
            <%@ include file="site_sidebar.jsp" %>
        </div>
        <%@ include file="site_footer.jsp"%>
    </div>
</body>
</html>
