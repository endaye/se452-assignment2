package db;

import java.sql.*;

public class SQLUtil {
    public static String getHtmlTable(ResultSet results)
            throws SQLException {
        StringBuilder htmlTable = new StringBuilder();
        ResultSetMetaData metaData = results.getMetaData();
        int columnCount = metaData.getColumnCount();
        htmlTable.append("<table>");
        // add header row
        htmlTable.append("<tr>");
        for (int i = 1; i <= columnCount; i++) {
            htmlTable.append("<th>");
            htmlTable.append(metaData.getColumnName(i));
            htmlTable.append("</th>");
        }
        htmlTable.append("</tr>");
        // add all other rows
        while (results.next()) {
            htmlTable.append("<tr>");
            for (int i = 1; i <= columnCount; i++) {
                htmlTable.append("<td>");
                htmlTable.append(results.getString(i));
                htmlTable.append("</td>");
            }
            htmlTable.append("</tr>");
        }
        htmlTable.append("</table>");
        return htmlTable.toString();
    }

    public static String getOrderHtmlTable(ResultSet results)
            throws SQLException {
        StringBuilder htmlTable = new StringBuilder();
        ResultSetMetaData metaData = results.getMetaData();
        int columnCount = metaData.getColumnCount();
        if (columnCount > 0) {
            while (results.next()) {
                String[] orderInfo = new String[6];
                for (int i = 0; i < columnCount; i++) {
                    orderInfo[i] = results.getString(i+1);
                }
                htmlTable.append("<form method='post' action='AllOrderHistory'>");
                htmlTable.append("<table>");
                htmlTable.append("<tr><th>Order #</th><th>" + orderInfo[0] +"</th><th></th></tr>");
                htmlTable.append("<tr><th>Buyer</th><th>" + orderInfo[3] +"</th><th></th></tr>");
                htmlTable.append("<tr><th>Order Date</th><th>" + orderInfo[1] +"</th><th></th></tr>");
                htmlTable.append("<tr><th>Delivery Date</th><th>" + orderInfo[2] + "</th><th></th></tr>");
                htmlTable.append("<tr><th>Total</th><th>$" + orderInfo[5] +"</th><th></th></tr>");
                htmlTable.append("<tr><th></th><th>Total</th><th>$"+orderInfo[5]+"</th></tr>");
                htmlTable.append("<tr><input hidden name='orderId' value='"+ orderInfo[0] +"'></input>");
                htmlTable.append("<th></th>");
                htmlTable.append("<th><input type='submit' class='button' name='ByUser' value='Remove' style='float: right;'></input></th>");
                htmlTable.append("<th><input type='submit' class='button' name='ByUser' value='Update' style='float: right;'></input></th></tr></table>");
                htmlTable.append("</form>");
            }
        } else {
            htmlTable.append("<h4 style='color:red'>Your have no order history.</h4>");
        }
        return htmlTable.toString();
    }
}