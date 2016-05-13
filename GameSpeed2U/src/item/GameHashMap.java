package item;

import java.util.HashMap;

public class GameHashMap {

	public static HashMap<String, Game> electronicArts = new HashMap<String, Game>();
	public static HashMap<String, Game> activision = new HashMap<String, Game>();
	public static HashMap<String, Game> takeTwoInteractive = new HashMap<String, Game>();
	
	public static String string_electronicArts = "Electronic Arts";
	public static String string_activision = "Activision";
	public static String string_takeTwoInteractive = "Take-Two Interactive";
	
	public GameHashMap(HashMap<String, Game> allGames) {
		for (String key: allGames.keySet()) {
			Game game = allGames.get(key);
			String retailer = game.getRetailer();
			if (retailer.equalsIgnoreCase("Electronic Arts")) {
				electronicArts.put(key, game);
			}else if (retailer.equalsIgnoreCase("Activision")) {
				activision.put(key, game);
			}else if (retailer.equalsIgnoreCase("Take-Two Interactive")) {
				takeTwoInteractive.put(key, game);
			}
		}
	}
}
