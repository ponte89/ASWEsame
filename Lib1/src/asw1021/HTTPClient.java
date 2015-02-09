package asw1021;

import java.io.*;
import java.net.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/** Utility che permette di stabilire connessioni HTTP, emulando le principali
 * funzionalità del browser in Rich Internet Application.
 *
 * @author Mezzapesa Beatrice, Papini Alessia, Pontellini Lorenzo
 */


public class HTTPClient {

    private URL base = null;
    private String sessionId = null;

    /** Restituisce il sessionId associato alla connessione HTTP
     *
     * @return String che rappresenta il corrente sessionID
     */

    public String getSessionId() {
        return sessionId;
    }
    
    /** Imposta un sessionID
     *
     * @param sessionId session ID da impostare
     */

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    
    /** Imposta l'URL della connessione HTTP
     *
     * @param base
     */

    public void setBase(URL base) {
        this.base = base;
    }
    
    /** Restituisce l'URL associato alla connessione HTTP
     *
     * @return URL della connessione
     */

    public URL getBase() {
        return base;
    }

    /** Stabilisce una connessione HTTP, invia una request al server e attende
     * una response.
     *
     * @param address Stringa che rappresenta l'indirizzo con cui si vuole 
     * stabilire una connesisone HTTP
     * 
     * @param data Dati da inviare una volta stabilita la connessione HTTP
     * 
     * @return Restituisce la risposta, in formato Document, di una comunicazione HTTP
     * 
     * @throws TransformerException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws MalformedURLException 
     */
    
    public Document execute(String address, Document data) throws TransformerException, ParserConfigurationException, SAXException, IOException, MalformedURLException {
        ManageXML manageXML = new ManageXML();

        HttpURLConnection connection = (HttpURLConnection) new URL(base, address).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        if (sessionId != null) {
            connection.setRequestProperty("Cookie", "JSESSIONID="+sessionId);
        }
        connection.setRequestProperty("Accept", "text/xml");
        connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        connection.connect();

        OutputStream out = connection.getOutputStream();
        manageXML.transform(out, data);
        out.close();
        
        InputStream in = connection.getInputStream();
        Document answer = manageXML.parse(in);
        in.close();

        String setCookie = connection.getHeaderField("Set-Cookie");
        if (setCookie != null && !setCookie.equals("") && (setCookie.substring(0, setCookie.indexOf("=")).equals("JSESSIONID"))) {
            sessionId = setCookie.substring(setCookie.indexOf("=")+1, setCookie.indexOf(";"));
        }
        //System.out.println(connection.getHeaderFields());

        connection.disconnect();
        return answer;
    }

}


