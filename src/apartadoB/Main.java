/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apartadoB;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Morad
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {

            File f = new File("Zoo.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(f);

            doc.getDocumentElement().normalize();
            loopNodeXml(doc.getDocumentElement());

            System.out.println("Pulsa 1 para comprobar animales o 0 para salir del bucle");

            int opcion = sc.nextInt();

            while (opcion!=0) {

                comprobarAnimales(doc, sc);
                System.out.println("Pulsa 1 para comprobar animales y 0 para salir del bucle");
                opcion = sc.nextInt();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParserConfigurationException p) {
            System.out.println(p.getMessage());
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        }
    }

    //leer de forma recursiva
    public static void loopNodeXml(Node n) {

        System.out.println(n.getNodeName());
        NodeList hijos = n.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            Node hijo = hijos.item(i);

            if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                loopNodeXml(hijo);

                //para atributos
                /* if(hijo.getAttributes()!= null && hijo.getAttributes().getLength()>0){
                    NamedNodeMap atributos = hijo.getAttributes();
                    for (int j = 0; j < 10; j++) {
                        Node attr = atributos.item(j);
                        loopNodeXml(attr);
                    }
                }*/
            } else if (hijo.getNodeType() == Node.TEXT_NODE && !hijo.getTextContent().trim().isEmpty()) {
                System.out.println("Valor: " + hijo.getTextContent());

            }
        }

    }

    public static void comprobarAnimales(Document doc, Scanner sc) {

        System.out.println("Introduce nombre de elemento");
        String nombre = sc.next();

        NodeList listaMamiferos = doc.getElementsByTagName("mamiferos");
        for (int i = 0; i < listaMamiferos.getLength(); i++) {
            Node nodo = listaMamiferos.item(i);

            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element element1 = (Element) nodo;
                NodeList hijos = element1.getChildNodes();
                for (int j = 0; j < hijos.getLength(); j++) {
                    Node hijo = hijos.item(j);
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        Element eHijo = (Element) hijo;
                        if (eHijo.getTextContent().equalsIgnoreCase(nombre)) {
                            System.out.println(nombre + " Es un mamifero");
                        }
                    }
                }
            }

        }

        NodeList listaReptiles = doc.getElementsByTagName("reptiles");
        for (int i = 0; i < listaReptiles.getLength(); i++) {
            Node nodo = listaReptiles.item(i);

            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element element1 = (Element) nodo;
                NodeList hijos = element1.getChildNodes();
                for (int j = 0; j < hijos.getLength(); j++) {
                    Node hijo = hijos.item(j);
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        Element eHijo = (Element) hijo;
                        if (eHijo.getTextContent().equalsIgnoreCase(nombre)) {
                            System.out.println(nombre + " Es un reptil");
                        }

                    }
                }
            }

        }

    }

}
