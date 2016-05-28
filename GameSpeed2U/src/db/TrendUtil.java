package db;

import item.CatalogItem;
import main.Helper;

import java.sql.*;
import java.util.HashMap;

public class TrendUtil {

    public static String runSelectSqlQuery (String query) {
        db.ConnectionPool pool = db.ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String out = "";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            out += SQLUtil.getHtmlTable(resultSet);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            pool.freeConnection(connection);
            return out;
        }
    }

    // ID
    public static String getAllProductIDFromDB () {
        db.ConnectionPool pool = db.ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String out = "";
        String query = "SELECT id FROM item";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            out += getIdHtmlSelectOption(resultSet);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            pool.freeConnection(connection);
            return out;
        }
    }

    public static String getIdHtmlSelectOption(ResultSet rs) throws SQLException {
        StringBuilder out = new StringBuilder();
        out.append("<select name='productName'>");
        out.append("<option value='all'>All Products</option>");
        HashMap<String, CatalogItem> items = new Helper().getAllItems();

        while (rs.next()) {
            String id = rs.getString(1);
            if (items.containsKey(id)) {
                out.append("<option value='"+id+"'>"+items.get(id).getName()+"</option>");
            }
        }
        out.append("</select>");
        return out.toString();
    }


    // Categroy
    public static String getAllProductCatalogFromDB () {
        db.ConnectionPool pool = db.ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String out = "";
        String query = "SELECT id, name FROM catalog";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            out += getCatalogHtmlSelectOption(resultSet);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            pool.freeConnection(connection);
            return out;
        }
    }

    public static String getCatalogHtmlSelectOption(ResultSet rs) throws SQLException {
        StringBuilder out = new StringBuilder();
        out.append("<select name='productCatalog'>");
        while (rs.next()) {
            String id = rs.getString(1);
            String catalog = rs.getString(2);
            out.append("<option value='"+id+"'>"+catalog+"</option>");
        }
        out.append("</select>");
        return out.toString();
    }

    // Maker
    public static String getAllMakerFromDB () {
        db.ConnectionPool pool = db.ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String out = "";
        String query = "SELECT id, name FROM Retailer";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            out += getMakerHtmlSelectOption(resultSet);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            pool.freeConnection(connection);
            return out;
        }
    }

    public static String getMakerHtmlSelectOption(ResultSet rs) throws SQLException {
        StringBuilder out = new StringBuilder();
        out.append("<select name='maker'>");
        while (rs.next()) {
            String id = rs.getString(1);
            String maker = rs.getString(2);
            out.append("<option value='"+id+"'>"+maker+"</option>");
        }
        out.append("</select>");
        return out.toString();
    }

}
