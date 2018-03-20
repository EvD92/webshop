package junitTest;

import static org.junit.Assert.*;

import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.PathParam;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;

public class soapTest {
	
	String a;
	
	public JsonObjectBuilder createBetaling() throws SOAPException, Exception {
		JsonObjectBuilder job = Json.createObjectBuilder();
	
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
            a =c;
            
          job.add("response", c);
          return job;
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
	       
	       //System.out.println(globNaam+", "+globAdres+", "+globBedrag);
	        soapBodyElem1.addTextNode("globNaam"); //k.getNaam()
	        soapBodyElem2.addTextNode("globAdres"); //k.getAdres()
	        soapBodyElem3.addTextNode("111"); // invoerveld.getBedrag()
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

	@Test
	public void test() throws SOAPException, Exception {
		
		JsonObjectBuilder testJson = this.createBetaling();
       System.out.println(testJson);
       try {
	        JsonObject o = (testJson.build());
	        System.out.println("SOAP has valid json");
	    } catch (JsonException e) {
	        System.out.println(("SOAP has no valid json"));
	        fail("SOAP has no valid json");
	    }
       
       if(a.length()!=6){
    	   System.out.println(a);
    	   System.out.println(a.length());
    	   fail("SOAP did not return valid number");
       } else{
    	   System.out.println(("SOAP did return valid number check1"));
       }
       int testInt = Integer.parseInt(a);
       
       if(testInt > 999999 || testInt<0){
    	   fail("SOAP return bad number");    	   
       } else{
       System.out.println("SOAP did return valid number check2");
       }
       
       System.out.println("SOAP werkt");
		}
	
}
