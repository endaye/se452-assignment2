<!-- start #header -->
<%@ page import="main.Helper" %>
<header>
    <h1><a href="Home.jsp">Game<span>Speed</span></a></h1>
    <h2>Made by Yuancheng Zhang</h2>
</header>

<nav>
    <ul>
        <li class="start selected"><a href="Home.jsp">Home</a></li>
        <%
            if (session.getAttribute("usertype") != null && session.getAttribute("usertype").toString().equals("manager")) {
        %>
        <li><a href="AddNewUser">Add New User</a></li>
        <li><a href="AllOrderHistory">Order History</a></li>
        <%
        } else {
        %>
        <li><a href="ConsoleList">Consoles</a></li>
        <li><a href="GameList.jsp">Games</a></li>
        <li><a href="TabletList">Tablets</a></li>
        <li><a href="Trending">Trending</a></li>
        <%
            }
        %>
        <!-- end #header-->
        <span id='login'>
            <%
                Helper helper = new Helper(request);
                if (session.getAttribute("username") != null) {
                    String username = session.getAttribute("username").toString();
                    username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
            %>
            <li>
                <a>Hello, <%= username%>
                </a>
            </li>
            <li>
                <a href='Account'>Account</a>
            </li>
                <%
                    if (session.getAttribute("usertype") != null && session.getAttribute("usertype").toString().equals("customer")) {
                %>
                    <li><a href='Logout'>Logout</a></li>
                    <li class='end'>
                        <a href='Cart'>Cart(<%= Integer.toString(helper.CartCount())%>)</a>
                    </li>
                <%
                } else {
                %>
                    <li class='end'>
                        <a href='Logout'>Logout</a>
                    </li>
                <%
                    }
                } else {
                %>
                    <li><a href='Login'>Login</a></li>
                    <li class='end'>
                        <a href='Cart'>Cart(<%= Integer.toString(helper.CartCount())%>)</a>
                    </li>
                <%
                    }
                %>
        </span>
    </ul>
</nav>
<!-- end #header -->