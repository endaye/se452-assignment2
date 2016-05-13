package item;

import java.util.HashMap;

public class ConsoleHashMap {
	public static HashMap<String, Console> microsoft = new HashMap<String, Console>();
	public static HashMap<String, Console> sony = new HashMap<String, Console>();
	public static HashMap<String, Console> nintendo = new HashMap<String, Console>();
	
	public static String string_microsoft = "Microsoft";
	public static String string_sony = "Sony";
	public static String string_nintendo = "Nintendo";
	
	public ConsoleHashMap(HashMap<String, Console> allConsoles) {
        for (String key: allConsoles.keySet()) {
            Console console = allConsoles.get(key);
            String retailer = console.getRetailer();
            if (retailer.equalsIgnoreCase("Microsoft")) {
                microsoft.put(key, console);
            } else if (retailer.equalsIgnoreCase("Sony")) {
                sony.put(key, console);
            } else if (retailer.equalsIgnoreCase("Nintendo")) {
                nintendo.put(key, console);
            }
        }
	}
}
