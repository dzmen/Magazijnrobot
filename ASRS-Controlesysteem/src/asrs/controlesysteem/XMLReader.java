/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asrs.controlesysteem;

/**
 *
 * @author Quinten
 */
import java.util.List;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@XmlType(propOrder={"ordernummer", "klant", "datum", "artikelen", "XMLReader" })
public class XMLReader{
    int Ordernummer;
    String Klant;
    String Datum;
     ArrayList [] Artikelen;
    List <XMLReader>Bestelling;
    private boolean compleet = false;

    public XMLReader(File bestand) {
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(bestand);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("bestelling");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("Ordernummer: " + eElement.getAttribute("ordernummer"));
                    System.out.println("klant : " + eElement.getElementsByTagName("klant").item(0).getTextContent());
                    System.out.println("datum : " + eElement.getElementsByTagName("datum").item(0).getTextContent());
                    System.out.println("artikelnr : " + eElement.getElementsByTagName("artikelnr").item(1).getTextContent());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getCompleet() {
        return this.compleet;
    }
}

