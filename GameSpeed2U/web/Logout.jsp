<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="main.Helper" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%

    Helper helper = new Helper(request);
    helper.logout();
    response.sendRedirect("Home.jsp");
%>
</body>
</html>
