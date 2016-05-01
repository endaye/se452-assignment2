
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;

public class SAXParserGameHandler extends DefaultHandler {

    String elementValueRead;

    Game game;
    HashMap<String, Game> games;

    ////////////////////////////////////////////////////////////

    /*************
     * There are a number of methods to override in SAX handler  when parsing your XML document :
     * <p>
     * Group 1. startDocument() and endDocument() :
     * Methods that are called at the start and end of an XML document.
     * Group 2. startElement() and endElement() :
     * Methods that are called at the start and end of a document element.
     * Group 3. characters() :
     * Method that is called with the text content in between the start and end tags of an XML document element.
     * <p>
     * There are few other methods that you could use for notification for different purposes, check the API at the following URL:
     * <p>
     * https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html
     ***************/

    ////////////////////////////////////////////////////////////
    public SAXParserGameHandler(HashMap<String, Game> games) {
        this.games = games;
    }

    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes)
            throws SAXException {
        if (elementName.equalsIgnoreCase("game")) {
            game = new Game();
            games.put(attributes.getValue("id"), game);
        }
    }

    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
        if (element.equalsIgnoreCase("game_name")) {
            game.setName(elementValueRead);
            return;
        }
        if (element.equalsIgnoreCase("game_price")) {
            game.setPrice(Double.parseDouble(elementValueRead));
            return;
        }
        if (element.equalsIgnoreCase("game_image")) {
            game.setImage(elementValueRead);
            return;
        }
        if (element.equalsIgnoreCase("game_retailer")) {
            game.setRetailer(elementValueRead);
            return;
        }
        if (element.equalsIgnoreCase("game_condition")) {
            game.setCondition(elementValueRead);
            return;
        }
        if (element.equalsIgnoreCase("game_discount")) {
            game.setDiscount(Double.parseDouble(elementValueRead));
            return;
        }
    }

    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }
}