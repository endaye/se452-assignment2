package item;

import db.SQLUtil;
import main.Helper;
import db.TrendUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class Query {
	private String tableName;
	private String query;
	private String selected;
	private HashMap<String, String> joint;

	protected Query(String tableName) {
		setTableName(tableName);
		setQuery("");
		setSelected("");
		setJoint(new HashMap<String, String>());
	}

	String getTableName() {
		return tableName;
	}

	void setTableName(String tableName) {
		this.tableName = tableName;
	}

	String getQuery() {
		return query;
	}

	void setQuery(String query) {
		this.query = query;
	}

	String getSelected() {
		return selected;
	}

	void setSelected(String selected) {
		this.selected = selected;
	}

	HashMap<String, String> getJoint() {
		return joint;
	}

	private void setJoint(HashMap<String, String> joint) {
		this.joint = joint;
	}
}

@WebServlet(name = "Trending", urlPatterns = "/Trending")
public class Trending extends HttpServlet {

	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response) throws ServletException, IOException {
		displayTrending(request, response);
	}

	protected void displayTrending(HttpServletRequest request,
								   HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		helper.printHtml("site_header.html");
		pw.print("<div id='body'><section id='content'><article class='expanded'>");
		pw.print("<h2>Trending</h2>");
		checkSession(request, response);

		pw.print("</article></section>");
		helper.printHtml("site_sidebar.html");
		helper.printHtml("site_footer.html");
	}

	private void checkSession(HttpServletRequest request,
							  HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, response.getWriter());

		Map<String, String[]> paras = request.getParameterMap();
		ArrayList<Query> queries = new ArrayList<>();

		if(paras.containsKey("select")) {
			String[] select = paras.get("select");
			int len = select.length;
			if (len <= 0) {
				displayHtmlForm(pw);
			} else {
				for (int i = 0; i < len; i++) {
					//System.out.println(paras.get("select")[i]);
					buildSingleSqlQuery(paras.get("select")[i], paras, queries);
				}
				String q = "";
				if (len == 1) {
					q = queries.get(0).getQuery();
				} else if (len == 2) {
					q = buildTwoSqlQuery(queries.get(0), queries.get(1));
				} else {
					q = buildMultSqlQuery(queries);
				}
				System.out.println(q);
				pw.print(TrendUtil.runSelectSqlQuery(q));
			}
		} else {
			displayHtmlForm(pw);
		}
	}
	private void buildSingleSqlQuery(String selectIndex,
									 Map<String, String[]> paras,
									 ArrayList<Query> queries) {
		Query query = new Query("T" + new Integer(queries.size()+1).toString() );
		System.out.println("SelectIndex: " + selectIndex);
		int index = Integer.valueOf(selectIndex);
		switch (index) {
			case 1: {
				String productName = paras.get("productName")[0];
				query.setSelected("name");
				query.getJoint().put("item_id", "id");
				if (productName.equals("all")) {
					query.setQuery(" (SELECT name, id FROM Item) ");
				} else {
					query.setQuery(" (SELECT name, price, id FROM Item WHERE id = \"" + productName + "\") ");
				}
				break;
			}
			case 2: {
				String productCatalog = paras.get("productCatalog")[0];
				query.setSelected("name");
				query.getJoint().put("item_id", "id");
				query.setQuery(" (SELECT name, id FROM Item WHERE catalog_id=\""+productCatalog+"\") ");
				break;
			}
			case 3: {
				String price = paras.get("price")[0];
				query.setSelected("name");
				query.getJoint().put("item_id", "id");
				String cond = paras.get("priceCond")[0];
				String symbol;
				if (cond.equals("eq")) symbol = "=";
				else if (cond.equals("lt")) symbol = "<";
				else symbol = ">";
				query.setQuery(" (SELECT name, id FROM Item WHERE price"+symbol+price+") ");
				break;
			}
			case 15: {
				int rate = Integer.valueOf(paras.get("rate")[0]);
				query.setSelected("rate");
				query.getJoint().put("item_id", "item_id");
				String cond = paras.get("rateCond")[0];
				String symbol;
				if (cond.equals("eq")) symbol = "=";
				else if (cond.equals("lt")) symbol = "<";
				else symbol = ">";
				query.setQuery(" (SELECT item_id, CEILING(AVG(rate)) AS rate FROM Review GROUP BY item_id HAVING rate"+symbol+rate+") ");
				break;
			}
			default: {
				return;
			}
		}
		queries.add(query);
	}

	private String buildTwoSqlQuery(Query q1, Query q2) {
		// find joint;
		Set<String> q1ks = q1.getJoint().keySet();
		Set<String> q2ks = q2.getJoint().keySet();
		String joint = "";
		for (String key1 : q1ks) {
			for(String key2 : q2ks) {
				if (key1.equals(key2)) joint = key1;
			}
		}
		String longQuery = "";
		longQuery += " (" +
				//" SELECT "+q1.getTableName()+"."+q1.getSelected()+","+q2.getTableName()+"."+q2.getSelected()+
				" SELECT * "+
				" FROM "+q1.getQuery()+" AS "+q1.getTableName()+
				" INNER JOIN "+q2.getQuery()+" AS "+q2.getTableName()+
				" ON "+q1.getTableName()+"."+q1.getJoint().get(joint)+"="+q2.getTableName()+"."+q2.getJoint().get(joint)+
				") ";
		return longQuery;
	}

	private String buildMultSqlQuery(ArrayList<Query> queries) {
		if (queries.size() == 0) {
			return "";
		} else if (queries.size() == 1) {
			return queries.get(0).getQuery();
		} else if (queries.size() == 2) {
			return buildTwoSqlQuery(queries.get(0), queries.get(1));
		} else {
			Query q0 = queries.get(0);
			Query q1 = queries.get(1);
			int index = 0;
			Query[] qs = new Query[queries.size() - 1];
			qs[0] = new Query("X" + new Integer(index).toString());
			qs[0].setSelected("*");
			qs[0].setQuery(buildTwoSqlQuery(q0, q1));
			qs[0].getJoint().putAll(q0.getJoint());
			for (String k : q1.getJoint().keySet()) {
				if (!qs[0].getJoint().containsKey(k)) qs[0].getJoint().put(k, q1.getJoint().get(k));
			}
			for (int i = 2; i < queries.size(); i++) {
				++index;
				qs[index] = new Query("X" + new Integer(index).toString());
				qs[index].setSelected("*");
				qs[index].setQuery(buildTwoSqlQuery(qs[index-1], queries.get(i)));
				for (String k : queries.get(i).getJoint().keySet()) {
					if (!qs[index].getJoint().containsKey(k)) qs[index].getJoint().put(k, queries.get(i).getJoint().get(k));
				}
			}
			return qs[index].getQuery();
		}

	}

	private void displayHtmlForm(PrintWriter pw) {
		String out = "";
		out += "<form action='Trending'>\n" +
				"\t<table>\n" +
				"\t\t<thead>\n" +
				"\t\t\t<tr>\n" +
				"\t\t\t\t<td colspan='4'>Simple Search</td>\n" +
				"\t\t\t</tr>\n" +
				"\t\t</thead>\n" +
				"\t\t<tbody>\n" +
				"\t\t\t<tr>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type='checkbox' name='select' value='1'> Select\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\tProduct Name:\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				TrendUtil.getAllProductIDFromDB() +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td></td>\n" +
				"\t\t\t</tr>\n" +
				"\t\t\t<tr>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type='checkbox' name='select' value='2'> Select\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\tProduct Category:\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				TrendUtil.getAllProductCatalogFromDB() +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td></td>\n" +
				"\t\t\t</tr>\n" +
				"<tr>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type='checkbox' name='select' value='3'> Select\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\tProduct Price:\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type=\"number\" min=\"0\" max=\"5000\" name=\"price\" value='0'>\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type=\"radio\" name=\"priceCond\" value=\"eq\"> Equals<br>\n" +
				"\t\t\t\t\t<input type=\"radio\" name=\"priceCond\" value=\"gt\" checked='checked'> Greater Than<br>\n" +
				"\t\t\t\t\t<input type=\"radio\" name=\"priceCond\" value=\"lt\"> Less Than\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t</tr>\n" +
				"\t\t\t<tr>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type='checkbox' name='select' value='4'> Select\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\tRetailer:\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type=\"text\" name=\"retailer\">\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td></td>\n" +
				"\t\t\t</tr>\n" +
				"\t\t\t<tr>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type='checkbox' name='select' value='5'> Select\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\tRetailer Zip code:\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type=\"number\" min=\"00000\" max=\"99999\" name=\"zipcode\">\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td></td>\n" +
				"\t\t\t</tr>\n" +
				"\t\t\t<tr>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type='checkbox' name='select' value='6'> Select\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\tRetailer City:\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type=\"text\" name=\"city\">\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td></td>\n" +
				"\t\t\t</tr>\n" +
				"\t\t\t<tr>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type='checkbox' name='select' value='7'> Select\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\tRetailer State:\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type=\"text\" name=\"state\">\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td></td>\n" +
				"\t\t\t</tr>\n" +
				"\t\t\t<tr>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type='checkbox' name='select' value='8'> Select\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\tProduct On Sale:\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type=\"radio\" name=\"onsale\" value=\"1\"> Yes\n" +
				"\t\t\t\t\t<input type=\"radio\" name=\"onsale\" value=\"0\"> No\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td></td>\n" +
				"\t\t\t</tr>\n" +
				"\t\t\t<tr>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type='checkbox' name='select' value='9'> Select\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\tManufacturer Name:\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				TrendUtil.getAllMakerFromDB() +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td></td>\n" +
				"\t\t\t</tr>\n" +
				"\t\t\t<tr>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type='checkbox' name='select' value='10'> Select\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\tManufacturer Rebate:\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type=\"radio\" name=\"rebate\" value=\"1\"> Yes\n" +
				"\t\t\t\t\t<input type=\"radio\" name=\"rebate\" value=\"0\"> No\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td></td>\n" +
				"\t\t\t</tr>\n" +
				"\t\t\t<tr>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type='checkbox' name='select' value='11'> Select\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\tUser Id:\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type=\"text\" name=\"user\">\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td></td>\n" +
				"\t\t\t</tr>\n" +
				"\t\t\t<tr>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type='checkbox' name='select' value='12'> Select\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\tAge:\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type=\"number\" min=\"0\" max=\"99\" name=\"age\" value='20'>\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type=\"radio\" name=\"ageCond\" value=\"eq\"> Equals<br>\n" +
				"\t\t\t\t\t<input type=\"radio\" name=\"ageCond\" value=\"gt\" checked='checked'> Greater Than<br>\n" +
				"\t\t\t\t\t<input type=\"radio\" name=\"ageCond\" value=\"lt\"> Less Than\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t</tr>\n" +
				"\t\t\t<tr>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type='checkbox' name='select' value='13'> Select\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\tGender:\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type=\"radio\" name=\"gender\" value=\"eq\"> Male\n" +
				"\t\t\t\t\t<input type=\"radio\" name=\"gender\" value=\"gt\"> Female\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td></td>\n" +
				"\t\t\t</tr>\n" +
				"\t\t\t<tr>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type='checkbox' name='select' value='14'> Select\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\tOccupation:\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type=\"text\" name=\"job\">\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td></td>\n" +
				"\t\t\t</tr>\n" +
				"\t\t\t<tr>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type='checkbox' name='select' value='15'> Select\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\tReview Rating:\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type=\"number\" value='1' min='1' max=\"5\" name=\"rate\">\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type=\"radio\" name=\"rateCond\" value=\"eq\"> Equals<br>\n" +
				"\t\t\t\t\t<input type=\"radio\" name=\"rateCond\" value=\"gt\" checked='checked'> Greater Than<br>\n" +
				"\t\t\t\t\t<input type=\"radio\" name=\"rateCond\" value=\"lt\"> Less Than\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t</tr>\n" +
				"\t\t\t<tr>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type='checkbox' name='select' value='16'> Select\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\tDate:\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td>\n" +
				"\t\t\t\t\t<input type=\"text\" name=\"date\">\n" +
				"\t\t\t\t</td>\n" +
				"\t\t\t\t<td></td>\n" +
				"\t\t\t</tr>" +
				"\t\t</tbody>\n" +
				"\t</table>\n" +
				"\t<input type='submit' value='Submit'>\n" +
				"</form>";
		pw.print(out);
	}


	@Override
	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
