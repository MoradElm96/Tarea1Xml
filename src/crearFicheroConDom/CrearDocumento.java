
package crearFicheroConDom;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author alumnotd
 * 
 * a. Implementa con DOM la generación de un archivo XML como el siguiente. 

<zoo>

   <mamiferos>

      <animal> Gato </animal>

      <animal> Perro </animal>

   </mamiferos>

   <reptiles>

      <animal> Boa </animal>

      <animal> Lagarto </animal>

   </reptiles>

</zoo>
 */
public class CrearDocumento {

    public static void main(String[] args) {

        File f = new File("Zoo.xml");
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

           
            //creamos el elemento raiz zoo y lo colgamos de documento
            Element raiz = doc.createElement("zoo");
            doc.appendChild(raiz);

            Element mamiferos = doc.createElement("mamiferos");
            raiz.appendChild(mamiferos);

            String nombreAnimalM = "Gato";
            for (int i = 0; i < 2; i++) {
                
                
                Element animal = doc.createElement("animal");
                animal.setTextContent(nombreAnimalM);
                mamiferos.appendChild(animal);
                nombreAnimalM = "Perro";
                
            }
            
            
            Element reptiles = doc.createElement("reptiles");
            raiz.appendChild(reptiles);
            
            String nombreAnimalR = "Boa";
            for (int i = 0; i < 2; i++) {
                
                 
                Element animal = doc.createElement("animal");
                animal.setTextContent(nombreAnimalR);
                reptiles.appendChild(animal);
                nombreAnimalR = "Lagarto";
                
            }
            
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(f);
            
            transformer.transform(source, result);
            
            

        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }catch(TransformerException t){
            System.out.println(t.getMessage());
        }

    }

}
