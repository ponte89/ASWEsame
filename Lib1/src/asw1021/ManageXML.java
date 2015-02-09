package asw1021;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/** Utility che permette di gestire DocumentXML, per l'invio e la ricezione
 * relativamente su OutputStream e da InputStream
 *
 * @author Mezzapesa Beatrice, Papini Alessia, Pontellini Lorenzo
 */

public class ManageXML {

    private Transformer transformer;
    private DocumentBuilder builder;
    
    /** Costruttore per la conversione Input-Document e Document-XmlDOM-Output
     * 
     * @throws TransformerConfigurationException
     * @throws ParserConfigurationException 
     */

    public ManageXML() throws TransformerConfigurationException, ParserConfigurationException {
        transformer = TransformerFactory.newInstance().newTransformer();
        builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }
    
    /** Crea un nuovo Document
     *
     * @return Restituisce un nuovo Document
     */

    public Document newDocument() {
        return builder.newDocument();
    }
    
    public Document newDocument(String rootTag) {
        Document newDoc = builder.newDocument(); 
        newDoc.appendChild(newDoc.createElement(rootTag));
        return newDoc;
    }
    
    /** Restituisce sullo Stream di Output i dati in formato XML-DOM, ottenuti
     * dalla conversione di un Document
     *
     * @param out Stream di Output su cui vengono inviati i dati trasformati in XML
     * 
     * @param document rappresentazione della sorgente xml di tipo Document da
     * trasformare in XML-DOM
     * 
     * @throws TransformerException
     * @throws IOException 
     */
    
    public void transform(OutputStream out, Document document) throws TransformerException, IOException {
        transformer.transform(new DOMSource(document), new StreamResult(out));
    }

    /** Costruisce un Document a partire dall'InputStream
     *
     * @param in Stream di Input da cui si ottiene il Document
     * 
     * @return Restituisce un Document associato allo Stream di Input passato
     * come parametro
     * 
     * @throws IOException
     * @throws SAXException 
     */
    
    public Document parse(InputStream in) throws IOException, SAXException {
        return builder.parse(in);
    }

    public Document parse(File in) throws IOException, SAXException {
        return builder.parse(in);
    }
}
