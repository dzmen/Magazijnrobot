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
import asrs.controlesysteem.GUI.Scherm;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XMLReader{
    private ArrayList<Integer> Artikelen = new ArrayList<Integer>();
    private boolean compleet = false;
    
    public XMLReader(File bestand, Scherm scherm) {

    
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(bestand);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("bestelling");


            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    NodeList artikelNrs = eElement.getElementsByTagName("artikelnr");
                    for(int i = 0; i < artikelNrs.getLength();){
                        Artikelen.add(i , Integer.parseInt(artikelNrs.item(i).getTextContent()));
                        i++;
                    }

                    scherm.printOrder("\nOrdernummer: " + eElement.getElementsByTagName("ordernummer").item(0).getTextContent());
                    scherm.printOrder("Naam : " + eElement.getElementsByTagName("voornaam").item(0).getTextContent()+ " " + eElement.getElementsByTagName("achternaam").item(0).getTextContent());
                    scherm.printOrder("Adres: " + eElement.getElementsByTagName("adres").item(0).getTextContent());
                    scherm.printOrder("Postcode: " + eElement.getElementsByTagName("postcode").item(0).getTextContent());
                    scherm.printOrder("Plaats : " + eElement.getElementsByTagName("plaats").item(0).getTextContent());
                    scherm.printOrder("Datum : " + eElement.getElementsByTagName("datum").item(0).getTextContent()+ "\n");
                    scherm.printOrder("Artikelen:");
                    for (int i = 0; i < Artikelen.size();){
                        scherm.printOrder("artikelnr: " + Artikelen.get(i));
                        i++;
                    }
                    compleet = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getCompleet() {
        return this.compleet;
    }
//    public Connection getConnection() throws SQLException {
//
//    Connection conn = null;
//    Properties connectionProps = new Properties();
//    connectionProps.put("user", this.userName);
//    connectionProps.put("password", this.password);
//
//    if (this.dbms.equals("mysql")) {
//        conn = DriverManager.getConnection(
//                   "jdbc:" + this.dbms + "://" +
//                   this.serverName +
//                   ":" + this.portNumber + "/",
//                   connectionProps);
//    } else if (this.dbms.equals("derby")) {
//        conn = DriverManager.getConnection(
//                   "jdbc:" + this.dbms + ":" +
//                   this.dbName +
//                   ";create=true",
//                   connectionProps);
//    }
//    System.out.println("Connected to database");
//    return conn;
//}
}

