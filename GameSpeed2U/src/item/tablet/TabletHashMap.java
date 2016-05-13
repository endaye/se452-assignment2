package item.tablet;

import java.util.HashMap;

public class TabletHashMap {
	public static HashMap<String, Tablet> apple = new HashMap<String, Tablet>();
	public static HashMap<String, Tablet> microsoft = new HashMap<String, Tablet>();
	public static HashMap<String, Tablet> samsung = new HashMap<String, Tablet>();
	
	public static String string_apple = "Apple";
	public static String string_microsoft = "Microsoft";
	public static String string_samsung = "Samsung";
	
	public TabletHashMap(HashMap<String, Tablet> allTablet) {
		for (String key: allTablet.keySet()) {
			Tablet tablet = allTablet.get(key);
			String retailer = tablet.getRetailer();
			if (retailer.equalsIgnoreCase("Apple")) {
				apple.put(key, tablet);
			} else if (retailer.equalsIgnoreCase("Microsoft")) {
				microsoft.put(key, tablet);
			} else if (retailer.equalsIgnoreCase("Samsung")) {
				samsung.put(key, tablet);
			}
		}
	}
}
