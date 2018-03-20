package restfull;
import javax.xml.soap.*;

public class SoapCaller {


    public static void main(String args[]) {
        
        String soapEndpointUrl = "http://localhost:8080/soap-project/services/KoopServiceImpl";
        String soapAction = "http://services.koop.com/KoopServiceImpl/getBetalingRequest";

        callSoapWebService(soapEndpointUrl, soapAction);
    }

    private static void createSoapEnvelope(SOAPMessage soapMessage) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "ser";
        String myNamespaceURI = "http://localhost:8080/soap-project/services/KoopServiceImpl";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

            

        // SOAP (Envelope) Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("getBetaling", myNamespace);
        
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("naam", myNamespace);
       SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("adres", myNamespace);
       SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("bedrag",myNamespace);
       
        soapBodyElem1.addTextNode("Naampie"); //k.getNaam()
        soapBodyElem2.addTextNode("testStraat"); //k.getAdres()
        soapBodyElem3.addTextNode("80"); // invoerveld.getBedrag()
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