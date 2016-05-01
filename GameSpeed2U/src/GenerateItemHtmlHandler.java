/**
 * generate a item HTML div
 */

public class GenerateItemHtmlHandler {
    private String out = "";

    // Game
    public GenerateItemHtmlHandler(Game game) {
        generateGameItemHtml(game.getName(), game.getPrice(), game.getImage());
    }

    // Accessory
    public GenerateItemHtmlHandler(Accessory accessory) {
        generateAccessoryItemHtml(accessory.getName(), accessory.getPrice(), accessory.getImage());
    }

    // Console
    public GenerateItemHtmlHandler(String consoleId, Console console) {
        generateConsoleItemHtml(consoleId, console.getName(), console.getPrice(), console.getImage(), console.getRetailer());
    }

    // Tablet
    public GenerateItemHtmlHandler(Tablet tablet) {
        generateTabletItemHtml(tablet.getName(), tablet.getPrice(), tablet.getImage());
    }

    public String getHtml() {
        return out;
    }

    // Game
    private void generateGameItemHtml(String name, double price, String image) {
        out += "<div class='item'><div class='item-image'>" +
                "<img src='images/games/"+ image + "' " +
                "alt='"+ name +"'/></div>" +
                "<div class='item-title'>" +
                "<h3>" + name + "</h3><strong>$" + price + "</strong></div>" +
                "<div class='item-detail'><ul>" +
                "<li><span class='item-button'><a class='button' href='#'>Buy Now</a></span></li>" +
                "<li><span class='item-button'><a class='button' href='#'>Reviews</a></span></li>" +
                "</ul></div></div>";
    }

    // Accessory
    private void generateAccessoryItemHtml(String name, double price, String image) {
        out += "<div class='item'><div class='item-image'>" +
                "<img src='images/accessories/"+ image + "' " +
                "alt='"+ name +"'/></div>" +
                "<div class='item-title'>" +
                "<h3>" + name + "</h3><strong>$" + price + "</strong></div>" +
                "<div class='item-detail'><ul>" +
                "<li><span class='item-button'><a class='button' href='#'>Buy Now</a></span></li>" +
                "<li><span class='item-button'><a class='button' href='#'>Reviews</a></span></li>" +
                "</ul></div></div>";
    }

    // Console
    private void generateConsoleItemHtml(String id, String name, double price, String image, String retailer) {
        out += "<div class='item'><div class='item-image'>" +
                "<img src='images/consoles/"+ image + "' " +
                "alt='"+ name +"'/></div>" +
                "<div class='item-title'>" +
                "<h3>" + name + "</h3><strong>$" + price + "</strong></div>" +
                "<div class='item-detail'><ul>" +
                "<li><span class='item-button'><a class='button' href='#'>Buy Now</a></span></li>";
        out += "<li><span class='item-button'><a class='button' " +
                "href='AccessoryList?maker=" + retailer.toLowerCase() + "&console=" + id.toLowerCase() + "'>" +
                "View Accessories</a></span></li>";
        out += "<li><span class='item-button'><a class='button' href='#'>Reviews</a></span></li>" +
                "</ul></div></div>";
    }

    // Game
    private void generateTabletItemHtml(String name, double price, String image) {
        out += "<div class='item'><div class='item-image'>" +
                "<img src='images/tablets/"+ image + "' " +
                "alt='"+ name +"'/></div>" +
                "<div class='item-title'>" +
                "<h3>" + name + "</h3><strong>$" + price + "</strong></div>" +
                "<div class='item-detail'><ul>" +
                "<li><span class='item-button'><a class='button' href='#'>Buy Now</a></span></li>" +
                "<li><span class='item-button'><a class='button' href='#'>Reviews</a></span></li>" +
                "</ul></div></div>";
    }
}
