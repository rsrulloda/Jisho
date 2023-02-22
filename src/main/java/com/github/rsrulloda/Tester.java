package com.github.rsrulloda;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.nio.charset.StandardCharsets;

public class Tester {
    public static void main(String[] args) {
        try {
            //creating a constructor of file class and parsing an XML file
            File file = new File("C:\\Users\\ronel\\Documents\\Jisho\\src\\dictionary\\XMLFile.xml");

            //an instance of factory that gives a document builder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            //an instance of builder to parse the specified xml file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("word");

            // nodeList is not iterable, so we are using for loop
            for (int i=0;i<nodeList.getLength();i++) {
                Node node = nodeList.item(i);
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("word: "+ element.getElementsByTagName("word").item(0).getTextContent());

                    System.out.println(element.getTextContent());
                    //String s = element.getElementsByTagName("kanji").item(0).getTextContent();
                    //String s1 = new String(s.getBytes(), StandardCharsets.UTF_8);
                    //System.out.println("kanji: "+ s1);

                    System.out.println("hiragana: "+ element.getElementsByTagName("hiragana").item(0).getTextContent());
                    System.out.println("description: "+ element.getElementsByTagName("description").item(0).getTextContent());
                    System.out.println("noun: "+ element.getElementsByTagName("noun").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
