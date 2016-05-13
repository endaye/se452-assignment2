package item;

import item.accessory.Accessory;
import item.console.Console;
import item.game.Game;
import item.tablet.Tablet;

/**
 * generate a item HTML div
 */

public class GenerateItemHtmlHandler {
    private String out = "";

    // Game
    public GenerateItemHtmlHandler(String gameId, Game game) {
        generateGameItemHtml(gameId, game.getName(), game.getPrice(), game.getImage(), game.getRetailer());
    }

    // Accessory
    public GenerateItemHtmlHandler(String accId, Accessory accessory, String consoleId) {
        generateAccessoryItemHtml(accId, accessory.getName(), accessory.getPrice(), accessory.getImage(), accessory.getRetailer(), consoleId);
    }

    // Console
    public GenerateItemHtmlHandler(String consoleId, Console console) {
        generateConsoleItemHtml(consoleId, console.getName(), console.getPrice(), console.getImage(), console.getRetailer());
    }

    // Tablet
    public GenerateItemHtmlHandler(String tabletId, Tablet tablet) {
        generateTabletItemHtml(tabletId, tablet.getName(), tablet.getPrice(), tablet.getImage(), tablet.getRetailer());
    }

    public String getHtml() {
        return out;
    }

    // Game
    private void generateGameItemHtml(String id, String name, double price, String image, String maker) {
        out += "<div class='item'><div class='item-image'>" +
                "<img src='images/games/"+ image + "' " +
                "alt='"+ name +"'/></div>" +
                "<div class='item-title'>" +
                "<h3>" + name + "</h3><strong>$" + price + "</strong></div>" +
                "<div class='item-detail'><ul>" +
                "<li><span class='item-button'><a class='button' " +
                "href='Cart?id="+ id +"&type=games&maker="+ maker.toLowerCase() +"'>" +
                "Buy Now</a></span></li>" +
                "<li><span class='item-button'><a class='button' " +
                "href='Review?id="+ id +"&type=games&maker="+ maker.toLowerCase() +"'>" +
                "Reviews</a></span></li>" +
                "</ul></div></div>";
    }

    // Accessory
    private void generateAccessoryItemHtml(String id, String name, double price, String image, String maker, String consoleId) {
        out += "<div class='item'><div class='item-image'>" +
                "<img src='images/accessories/"+ image + "' " +
                "alt='"+ name +"'/></div>" +
                "<div class='item-title'>" +
                "<h3>" + name + "</h3><strong>$" + price + "</strong></div>" +
                "<div class='item-detail'><ul>" +
                "<li><span class='item-button'><a class='button' " +
                "href='Cart?id="+ id +"&type=accessories&maker="+ maker.toLowerCase() +"&access="+ consoleId +"'>" +
                "Buy Now</a></span></li>" +
                "<li><span class='item-button'><a class='button' " +
                "href='Review?id="+ id +"&type=accessories&maker="+ maker.toLowerCase() +"&access="+ consoleId +"'>" +
                "Reviews</a></span></li></ul></div></div>";
    }

    // Console
    private void generateConsoleItemHtml(String id, String name, double price, String image, String maker) {
        out += "<div class='item'><div class='item-image'>" +
                "<img src='images/consoles/"+ image + "' " +
                "alt='"+ name +"'/></div>" +
                "<div class='item-title'>" +
                "<h3>" + name + "</h3><strong>$" + price + "</strong></div>" +
                "<div class='item-detail'><ul>" +
                "<li><span class='item-button'><a class='button' " +
                "href='Cart?id="+ id +"&type=consoles&maker="+ maker.toLowerCase() +"'>Buy Now</a></span></li>";
        out += "<li><span class='item-button'><a class='button' " +
                "href='AccessoryList?maker=" + maker.toLowerCase() + "&console=" + id.toLowerCase() + "'>" +
                "View Accessories</a></span></li>";
        out += "<li><span class='item-button'><a class='button' " +
                "href='Review?id="+ id +"&type=consoles&maker="+ maker.toLowerCase() +"'>" +
                "Reviews</a></span></li></ul></div></div>";
    }

    // Game
    private void generateTabletItemHtml(String id, String name, double price, String image, String maker) {
        out += "<div class='item'><div class='item-image'>" +
                "<img src='images/tablets/"+ image + "' " +
                "alt='"+ name +"'/></div>" +
                "<div class='item-title'>" +
                "<h3>" + name + "</h3><strong>$" + price + "</strong></div>" +
                "<div class='item-detail'><ul>" +
                "<li><span class='item-button'><a class='button' " +
                "href='Cart?id="+ id +"&type=tablets&maker="+ maker.toLowerCase() +"'>" +
                "Buy Now</a></span></li>" +
                "<li><span class='item-button'><a class='button' " +
                "href='Review?id="+ id +"&type=tablets&maker="+ maker.toLowerCase() +"'>" +
                "Reviews</a></span></li>" +
                "</ul></div></div>";
    }
}
