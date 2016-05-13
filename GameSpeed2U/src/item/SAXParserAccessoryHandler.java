package item;

import item.Accessory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;

public class SAXParserAccessoryHandler extends DefaultHandler{

    String elementValueRead;

    Accessory accessory;
    HashMap<String, Accessory> accessories;

    ////////////////////////////////////////////////////////////

    /*************

     There are a number of methods to override in SAX handler  when parsing your XML document :

     Group 1. startDocument() and endDocument() :
     Methods that are called at the start and end of an XML document.
     Group 2. startElement() and endElement() :
     Methods that are called at the start and end of a document element.
     Group 3. characters() :
     Method that is called with the text content in between the start and end tags of an XML document element.

     There are few other methods that you could use for notification for different purposes, check the API at the following URL:

     https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

     ***************/

    ////////////////////////////////////////////////////////////

    public SAXParserAccessoryHandler(HashMap<String, Accessory> accessories) {
        this.accessories = accessories;
    }


    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes)
            throws SAXException {
        if (elementName.equalsIgnoreCase("accessory")) {
            accessory = new Accessory();
            accessories.put(attributes.getValue("id"), accessory);
        }
    }

    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
        if (element.equalsIgnoreCase("accessory_name")) {
            accessory.setName(elementValueRead);
            return;
        }
        if(element.equalsIgnoreCase("accessory_price")){
            accessory.setPrice(Double.parseDouble(elementValueRead));
            return;
        }
        if (element.equalsIgnoreCase("accessory_image")) {
            accessory.setImage(elementValueRead);
            return;
        }
        if (element.equalsIgnoreCase("accessory_retailer")) {
            accessory.setRetailer(elementValueRead);
            return;
        }
        if (element.equalsIgnoreCase("accessory_condition")) {
            accessory.setCondition(elementValueRead);
            return;
        }
        if (element.equalsIgnoreCase("accessory_discount")) {
            accessory.setDiscount(Double.parseDouble(elementValueRead));
            return;
        }
    }

    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }
}
