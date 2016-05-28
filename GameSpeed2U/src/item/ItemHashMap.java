package item;

import item.accessory.Accessory;
import item.console.Console;
import item.game.Game;
import item.tablet.Tablet;

import java.util.HashMap;

public class ItemHashMap {
    public static HashMap<String, CatalogItem> allItems = new HashMap<>();

    public ItemHashMap(HashMap<String, Game> games,
                       HashMap<String, Console> consoles,
                       HashMap<String, Accessory> accessories,
                       HashMap<String, Tablet> tablets) {
        allItems.putAll(games);
        allItems.putAll(consoles);
        allItems.putAll(accessories);
        allItems.putAll(tablets);
    }
}