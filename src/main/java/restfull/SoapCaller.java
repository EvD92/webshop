package restfull;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Scanner;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import domain.Categorie;


@Path ("/betaling")
public class SoapCaller {
	  String soapEndpointUrl = "http://localhost:8989/soap-project/services/KoopServiceImpl";
      String soapAction = "http://services.koop.com/KoopServiceImpl/getBetalingRequest";
      static String globNaam = null;
      static String globAdres = null;
      static String globBedrag = null;
	
	@GET
	@Path("{adres}/{naam}/{bedrag}")
	@RolesAllowed("guest")
	@Produces("application/json")
	public String createBetaling(@PathParam("adres") String adres, @PathParam("naam") String naam, @PathParam("bedrag") String bedrag) throws SOAPException, Exception {
		JsonObjectBuilder job = Json.createObjectBuilder();
	
	        globNaam = naam;
	        globAdres = adres;
	        globBedrag = bedrag;
	        String soapEndpointUrl = "http://localhost:8989/soap-project/services/KoopServiceImpl";
	        String soapAction = "http://services.koop.com/KoopServiceImpl/getBetalingRequest";

	        //this.createSOAPRequest(soapAction) returned soapMessage
	        //this.createSoapEnvelope(this.createSOAPRequest(soapAction));
	        
	        //this.callSoapWebService(soapEndpointUrl, soapAction);
	        
	        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

	        
	        SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction), soapEndpointUrl);
	        soapConnection.close();
	        System.out.println("---SOAP Connection closed---\n");
            // Print the SOAP Response
            System.out.println("SOAP Response Message:");
            soapResponse.writeTo(System.out);

            
            final SOAPMessage message = soapResponse;
            final StringWriter sw = new StringWriter();

            try {
                TransformerFactory.newInstance().newTransformer().transform(
                    new DOMSource(message.getSOAPPart()),
                    new StreamResult(sw));
            } catch (TransformerException e) {
                throw new RuntimeException(e);
            }

            // Now you have the XML as a String:
            System.out.println("SWTOSTRING\n"+sw.toString());
            String sws = sw.toString();
            String c = sws.substring(392, 398);
            System.out.println(c);
            
          job.add("response", c);
   			return job.build().toString();
          }
           
	    

	    private static void createSoapEnvelope(SOAPMessage soapMessage) throws SOAPException {
	        SOAPPart soapPart = soapMessage.getSOAPPart();
	        
	        String myNamespace = "ser";
	        String myNamespaceURI = "http://localhost:8989/soap-project/services/KoopServiceImpl";

	        // SOAP Envelope
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

	            

	        // SOAP (Envelope) Body
	        SOAPBody soapBody = envelope.getBody();
	        SOAPElement soapBodyElem = soapBody.addChildElement("getBetaling", myNamespace);
	        
	        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("naam", myNamespace);
	       SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("adres", myNamespace);
	       SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("bedrag",myNamespace);
	       
	       System.out.println(globNaam+", "+globAdres+", "+globBedrag);
	        soapBodyElem1.addTextNode(globNaam); //k.getNaam()
	        soapBodyElem2.addTextNode(globAdres); //k.getAdres()
	        soapBodyElem3.addTextNode(globBedrag); // invoerveld.getBedrag()
	    }

	    private static void callSoapWebService(String soapEndpointUrl, String soapAction) {
	        try {
	            // Create SOAP Connection
	            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
	            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

	            // Send SOAP Message to SOAP Server
	            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction), soapEndpointUrl);

	            // Print the SOAP Response
	            System.out.println("Response SOAP Message:");
	            soapResponse.writeTo(System.out);
	            System.out.println();

	            soapConnection.close();
	        } catch (Exception e) {
	            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
	            e.printStackTrace();
	        }
	    }

	    private static SOAPMessage createSOAPRequest(String soapAction) throws Exception {
	        MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();

	        createSoapEnvelope(soapMessage);

	        MimeHeaders headers = soapMessage.getMimeHeaders();
	        headers.addHeader("SOAPAction", soapAction);

	        soapMessage.saveChanges();

	        /* Print the request message, just for debugging purposes */
	        System.out.println("Request SOAP Message:");
	        soapMessage.writeTo(System.out);
	        System.out.println("\n");

	        return soapMessage;
	    }
}

