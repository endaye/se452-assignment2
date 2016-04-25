import java.io.*;
import java.util.Iterator;

import org.json.*;

import java.util.HashMap;

public class GameHashMap {

	public static HashMap<String, Game> electronicArts = new HashMap<String, Game>();
	public static HashMap<String, Game> activision = new HashMap<String, Game>();
	public static HashMap<String, Game> takeTwoInteractive = new HashMap<String, Game>();
    public static HashMap<String, Game> allGames = new HashMap<String, Game>();
    
	public static String string_electronicArts = "Electronic Arts";
	public static String string_activision = "Activision";
	public static String string_takeTwoInteractive = "Take-Two Interactive";
	
	public GameHashMap() {
		this.getGameList();
        if (!allGames.isEmpty()) {
            for (HashMap.Entry<String, Game> entry : allGames.entrySet()) {
                String key = entry.getKey();
                Game value = entry.getValue();
                if (value.getRetailer().equals(string_electronicArts)) {
                    electronicArts.put(key, value);
                } else if (value.getRetailer().equals(string_activision)) {
                    activision.put(key, value);
                } else if (value.getRetailer().equals(string_takeTwoInteractive)) {
                    takeTwoInteractive.put(key, value);
                } else {
                    // do nothing
                }
            }
        }
        
	}

	public void getGameList() {
        try {
            JSONObject jsonObject = new JSONObject(readFile(
                    "/Library/Tomcat/webapps/GameSpeed/gamelist.json"));
            JSONArray jsonGames = (JSONArray) jsonObject.get("games");

            Iterator<Object> it = jsonGames.iterator();
            while (it.hasNext()) {
                JSONObject jsonTemp = (JSONObject) it.next();
                String name      = (String) jsonTemp.get("name");
                double price     = (double) jsonTemp.get("price");
                String image     = (String) jsonTemp.get("image");
                String retailer  = (String) jsonTemp.get("retailer");
                String condition = (String) jsonTemp.get("condition");
                double discount  = (double) jsonTemp.get("discount");
                Game gameItem = new Game(name, price, image, retailer, condition, discount);
                
                if (!allGames.containsKey(gameItem)) {
                    allGames.put(name, gameItem);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readFile(String filename) {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            result = sb.toString();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
