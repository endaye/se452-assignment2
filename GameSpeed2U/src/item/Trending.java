package item;

import main.Helper;
import main.SQLUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Trending", urlPatterns = "/Trending")
public class Trending extends HttpServlet {
	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response) throws ServletException, IOException {

		String sqlStatement = "select * from items";
		String sqlResult = "";
		try {
			// load the driver
			Class.forName("com.mysql.jdbc.Driver");
			// get a connection
			String dbURL = "jdbc:mysql://localhost:3306/GameSpeed2U?autoReconnect=true&useSSL=false";
			String username = "root";
			String password = "1234567";
			Connection connection = DriverManager.getConnection(
					dbURL, username, password);
			// create a statement
			Statement statement = connection.createStatement();
			// parse the SQL string
			sqlStatement = sqlStatement.trim();
			if (sqlStatement.length() >= 6) {
				String sqlType = sqlStatement.substring(0, 6);
				if (sqlType.equalsIgnoreCase("select")) {
					// create the HTML for the result set
					ResultSet resultSet
							= statement.executeQuery(sqlStatement);
					sqlResult = SQLUtil.getHtmlTable(resultSet);
					resultSet.close();
				} else {
					int i = statement.executeUpdate(sqlStatement);
					if (i == 0) {
						// a DDL statement
						sqlResult =
								"<p>The statement executed successfully.</p>";
					} else {
						// an INSERT, UPDATE, or DELETE statement
						sqlResult =
								"<p>The statement executed successfully.<br>"
										+ i + " row(s) affected.</p>";
					}
				}
			}
			statement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			sqlResult = "<p>Error loading the database driver: <br>"
					+ e.getMessage() + "</p>";
		} catch (SQLException e) {
			sqlResult = "<p>Error executing the SQL statement: <br>"
					+ e.getMessage() + "</p>";
		}
		finally {
			displayTrending(request, response, sqlResult);
		}

	}

	protected void displayTrending(HttpServletRequest request,
								   HttpServletResponse response, String sqlResult)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		helper.printHtml("site_header.html");
		pw.print("<div id='body'><section id='content'><article class='expanded'>");
		pw.print("<h2>Trending</h2>");
		pw.print(sqlResult);
		pw.print("</article></section>");
		helper.printHtml("site_sidebar.html");
		helper.printHtml("site_footer.html");
	}


	@Override
	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response)
			throws ServletException, IOException {
		//String sqlStatement = request.getParameter("sqlStatement");
		String sqlStatement = "SHOW DATABASES";
		String sqlResult = "";
		try {
			// load the driver
			Class.forName("com.mysql.jdbc.Driver");
			// get a connection
			String dbURL = "jdbc:mysql://localhost:3306/GameSpeed2U";
			String username = "root";
			String password = "1234567";
			Connection connection = DriverManager.getConnection(
					dbURL, username, password);
			// create a statement
			Statement statement = connection.createStatement();
			// parse the SQL string
			sqlStatement = sqlStatement.trim();
			if (sqlStatement.length() >= 6) {
				String sqlType = sqlStatement.substring(0, 6);
				if (sqlType.equalsIgnoreCase("select")) {
					// create the HTML for the result set
					ResultSet resultSet
							= statement.executeQuery(sqlStatement);
					sqlResult = SQLUtil.getHtmlTable(resultSet);
					resultSet.close();
				} else {
					int i = statement.executeUpdate(sqlStatement);
					if (i == 0) {
						// a DDL statement
						sqlResult =
								"<p>The statement executed successfully.</p>";
					} else {
						// an INSERT, UPDATE, or DELETE statement
						sqlResult =
								"<p>The statement executed successfully.<br>"
										+ i + " row(s) affected.</p>";
					}
				}
			}
			statement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			sqlResult = "<p>Error loading the database driver: <br>"
					+ e.getMessage() + "</p>";
		} catch (SQLException e) {
			sqlResult = "<p>Error executing the SQL statement: <br>"
					+ e.getMessage() + "</p>";
		}
	}
}
