package item;
/*********
 http://www.saxproject.org/

 SAX is the Simple API for XML, originally a Java-only API.
 SAX was the first widely adopted API for XML in Java, and is a “de facto” standard.
 The current version is SAX 2.0.1, and there are versions for several programming language environments other than Java.

 The following URL from Oracle is the JAVA documentation for the API

 https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

 *********/


import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


////////////////////////////////////////////////////////////

/**************

 SAX parser use callback function to notify client object of the XML document structure.
 You should extend DefaultHandler and override the method when parsin the XML document

 ***************/

////////////////////////////////////////////////////////////

public class SaxParserXMLdataStore {

    private String accessoryXmlFileName = "/AccessoryCatalog.xml";
    private String consoleXmlFileName = "/ConsoleCatalog.xml";
    private String gameXmlFileName = "/GameCatalog.xml";
    private String tabletXmlFileName = "/TabletCatalog.xml";
    private String userXmlFileName = "/UserCatalog.xml";

    private HashMap<String, Accessory> accessories;
    private HashMap<String, Console> consoles;
    private HashMap<String, Game> games;
    private HashMap<String, Tablet> tablets;

    public SaxParserXMLdataStore(String consoleXmlFilePath) {
        if (consoleXmlFilePath.isEmpty()) {
            consoleXmlFilePath = "";
        }
        accessoryXmlFileName = consoleXmlFilePath + accessoryXmlFileName;
        consoleXmlFileName = consoleXmlFilePath + consoleXmlFileName;
        gameXmlFileName = consoleXmlFilePath + gameXmlFileName;
        tabletXmlFileName = consoleXmlFilePath + tabletXmlFileName;


        accessories = new HashMap<String, Accessory>();
        consoles = new HashMap<String, Console>();
        games = new HashMap<String, Game>();
        tablets = new HashMap<String, Tablet>();

        SAXParserAccessoryHandler accessoryHandler = new SAXParserAccessoryHandler(accessories);
        SAXParserConsoleHandler consoleHandler = new SAXParserConsoleHandler(consoles);
        SAXParserGameHandler gameHandler = new SAXParserGameHandler(games);
        SAXParserTabletHandler tabletHandler = new SAXParserTabletHandler(tablets);

        parseDocument(accessoryXmlFileName, accessoryHandler);
        parseDocument(consoleXmlFileName, consoleHandler);
        parseDocument(gameXmlFileName, gameHandler);
        parseDocument(tabletXmlFileName, tabletHandler);

        // link accessory with console HashMap
        linkConsoleAndAccessory();

        //prettyPrint();
    }

    public HashMap<String, Accessory> getAccessories() {
        return this.accessories;
    }

    public HashMap<String, Console> getConsoles() {
        return this.consoles;
    }

    public HashMap<String, Game> getGames() {
        return this.games;
    }

    public HashMap<String, Tablet> getTablets() { return this.tablets; }


    private void parseDocument(String file, DefaultHandler handler) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(file, handler);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

    private void linkConsoleAndAccessory() {
        for (String key1: consoles.keySet()) {
            Console console = consoles.get(key1);
            for (String key2: console.getAccessories().keySet()) {
                if (accessories.containsKey(key2)) {
                    console.getAccessories().put(key2, accessories.get(key2));
                }
            }
        }
    }

    private void prettyPrint() {
        System.out.println("\n####### Accessory #######\n");
        for (String key: accessories.keySet()) {
            String value = accessories.get(key).toString();
            System.out.println(key + ": " + value);
        }
        System.out.println("\n####### Console #######\n");
        for (String key: consoles.keySet()) {
            String value = consoles.get(key).toString();
            System.out.println(key + ": " + value);
        }
        System.out.println("\n####### Game #######\n");
        for (String key: games.keySet()) {
            String value = games.get(key).toString();
            System.out.println(key + ": " + value);
        }
    }
}
