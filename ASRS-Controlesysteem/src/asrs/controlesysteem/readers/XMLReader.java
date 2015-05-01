/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asrs.controlesysteem.readers;

/**
 *
 * @author Quinten
 */
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXParseException;

public class XMLReader {

    private ArrayList<Integer> Artikelen = new ArrayList<Integer>();
    private File bestand;
    private String ordernr, naam, adres, postcode, plaats, datum, melding;
    private boolean compleet = false;

    public XMLReader() {
        //Genereer een popup om een bestand te selecteren
        JFileChooser openFile = new JFileChooser();
        FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("Order file (*.xml)", "xml");
        //Zorgt ervoor dat alleen xml of * geselecteerd kan worden
        openFile.setFileFilter(xmlFilter);
        int resultaat = openFile.showOpenDialog(null);
        //Kijkt of er een bestand is geopend of dat er op cancel gedrukt is
        if (resultaat == openFile.APPROVE_OPTION) {
            this.bestand = openFile.getSelectedFile();
            read();
        } else {
            this.melding = "Geen bestand geselecteerd!";
        }
    }

    private void read() {
        try {
            //Maakt een object van de xml file
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(bestand);
            //Zorgt ervoor dat enters niet als lose objecten wordt gezien http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
            //Kijken of er een tag bestelling in het bestand zit (We doen hier verder niets meer)
            String bestelling = doc.getElementsByTagName("bestelling").item(0).getTextContent();
            //Koppel de tags aan een String atribuut. Geeft nullpointer als de tag niet gevonden is
            this.ordernr = doc.getElementsByTagName("ordernummer").item(0).getTextContent();
            this.naam = doc.getElementsByTagName("voornaam").item(0).getTextContent() + " " + doc.getElementsByTagName("achternaam").item(0).getTextContent();
            this.adres = doc.getElementsByTagName("adres").item(0).getTextContent();
            this.postcode = doc.getElementsByTagName("postcode").item(0).getTextContent();
            this.plaats = doc.getElementsByTagName("plaats").item(0).getTextContent();
            this.datum = doc.getElementsByTagName("datum").item(0).getTextContent();

            //Loop door alle artikelen en zet ze in een array
            NodeList artikelNrs = doc.getElementsByTagName("artikelnr");
            for (int i = 0; i < artikelNrs.getLength(); i++) {
                Artikelen.add(i, Integer.parseInt(artikelNrs.item(i).getTextContent()));
            }
            this.compleet = true;
            this.melding = "Het bestand is met succes geïmporteerd!";
        } catch (NullPointerException ne) {
            this.melding = "Het bestand ontbreekt informatie!";
        } catch (SAXParseException se) {
            this.melding = "Het bestand is niet het juiste formaat!";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getCompleet() {
        return this.compleet;
    }

    public ArrayList<Integer> getArtikelen() {
        return Artikelen;
    }

    public String getOrdernr() {
        return naam;
    }

    public String getNaam() {
        return naam;
    }

    public String getAdres() {
        return adres;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getPlaats() {
        return plaats;
    }

    public String getDatum() {
        return datum;
    }

    public String getMelding() {
        return this.melding;
    }

    public String getBestandNaam() {
        return this.bestand.getName();
    }
}
