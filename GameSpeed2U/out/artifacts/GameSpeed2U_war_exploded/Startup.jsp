<%@ page import="item.dataload.SaxParserXMLdataStore" %>
<%@ page import="item.console.ConsoleHashMap" %>
<%@ page import="item.game.GameHashMap" %>
<%@ page import="item.tablet.TabletHashMap" %>
<%@ page import="user.account.UserHashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>GameSpeed</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
</head>
<body>
    <%!
        public void jspInit() {
            // read data from local xml file
            String path = getServletContext().getRealPath("");
            SaxParserXMLdataStore data = new SaxParserXMLdataStore(path);

            new ConsoleHashMap(data.getConsoles());
            new GameHashMap(data.getGames());
            new TabletHashMap(data.getTablets());

            new UserHashMap();
        }
    %>
    <%
        jspInit();
        response.sendRedirect("Home.jsp");
    %>
</body>
</html>
