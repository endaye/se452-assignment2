
/*********
 http://www.saxproject.org/

 SAX is the Simple API for XML, originally a Java-only API.
 SAX was the first widely adopted API for XML in Java, and is a “de facto” standard.
 The current version is SAX 2.0.1, and there are versions for several programming language environments other than Java.

 The following URL from Oracle is the JAVA documentation for the API

 https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

 *********/


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;


////////////////////////////////////////////////////////////

/**************

 SAX parser use callback function to notify client object of the XML document structure.
 You should extend DefaultHandler and override the method when parsin the XML document

 ***************/

////////////////////////////////////////////////////////////

public class SaxParser4GameSpeedXMLdataStore {

    private String consoleXmlFileName = "/ConsoleCatalog.xml";
    private String gameXmlFileName = "/GameCatalog.xml";
    private String accessoryXmlFileName = "/AccessoryCatalog.xml";
    private String userXmlFileName = "/UserCatalog.xml";
    private String tabletXmlFileName = "/TabletCatalog.xml";

    String elementValueRead;

    HashMap<String, Console> consoles;
    HashMap<String, Game> games;


    public SaxParser4GameSpeedXMLdataStore(String consoleXmlFilePath) {
        if (consoleXmlFilePath.isEmpty()) {
            consoleXmlFilePath = "";
        }
        consoleXmlFileName = consoleXmlFilePath + consoleXmlFileName;
        gameXmlFileName = consoleXmlFilePath + gameXmlFileName;
        consoles = new HashMap<String, Console>();
        games = new HashMap<String, Game>();
        parseDocument();
        prettyPrint();
    }

    private void parseDocument() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            SAXParserConsoleHandler consoleHandler = new SAXParserConsoleHandler(consoles);
            SAXParserGameHandler gameHandler = new SAXParserGameHandler(games);
            System.out.println(consoleXmlFileName);
            parser.parse(consoleXmlFileName, consoleHandler);
            parser.parse(gameXmlFileName, gameHandler);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

    private void prettyPrint() {
        for (String key: consoles.keySet()) {
            String value = consoles.get(key).toString();
            System.out.println(key + ": " + value);
        }
        for (String key: games.keySet()) {
            String value = games.get(key).toString();
            System.out.println(key + ": " + value);
        }
    }
}
