package item;

import item.Tablet;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;

public class SAXParserTabletHandler extends DefaultHandler {

    String elementValueRead;

    Tablet tablet;
    HashMap<String, Tablet> tablets;

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
    public SAXParserTabletHandler(HashMap<String, Tablet> tablets) {
        this.tablets = tablets;
    }

    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes)
            throws SAXException {
        if (elementName.equalsIgnoreCase("tablet")) {
            tablet = new Tablet();
            tablets.put(attributes.getValue("id"), tablet);
        }
    }

    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
        if (element.equalsIgnoreCase("tablet_name")) {
            tablet.setName(elementValueRead);
            return;
        }
        if (element.equalsIgnoreCase("tablet_price")) {
            tablet.setPrice(Double.parseDouble(elementValueRead));
            return;
        }
        if (element.equalsIgnoreCase("tablet_image")) {
            tablet.setImage(elementValueRead);
            return;
        }
        if (element.equalsIgnoreCase("tablet_retailer")) {
            tablet.setRetailer(elementValueRead);
            return;
        }
        if (element.equalsIgnoreCase("tablet_condition")) {
            tablet.setCondition(elementValueRead);
            return;
        }
        if (element.equalsIgnoreCase("tablet_discount")) {
            tablet.setDiscount(Double.parseDouble(elementValueRead));
            return;
        }
    }

    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }
}