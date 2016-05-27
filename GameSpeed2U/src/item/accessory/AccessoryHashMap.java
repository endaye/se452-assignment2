package item.accessory;

import java.util.HashMap;

public class AccessoryHashMap {
    public static HashMap<String, Accessory> microsoft = new HashMap<String, Accessory>();
    public static HashMap<String, Accessory> sony = new HashMap<String, Accessory>();
    public static HashMap<String, Accessory> nintendo = new HashMap<String, Accessory>();

    public static String string_microsoft = "Microsoft";
    public static String string_sony = "Sony";
    public static String string_nintendo = "Nintendo";

    public AccessoryHashMap(HashMap<String, Accessory> allAccessories) {
        for (String key: allAccessories.keySet()) {
            Accessory accessory = allAccessories.get(key);
            String retailer = accessory.getRetailer();
            if (retailer.equalsIgnoreCase("Microsoft")) {
                microsoft.put(key, accessory);
            } else if (retailer.equalsIgnoreCase("Sony")) {
                sony.put(key, accessory);
            } else if (retailer.equalsIgnoreCase("Nintendo")) {
                nintendo.put(key, accessory);
            }
        }
    }
}
