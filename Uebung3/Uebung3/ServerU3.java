
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
 
public class ServerU3 {
 
    public static void main(String[] args) throws IOException {
       
    	
    	 ServerSocket server = new ServerSocket(2222); //Es wird wieder dieser Port 2222 verwendet
         System.out.println("Server wird gestartet");
    	
         Socket client = server.accept();
         System.out.println("Verbindung wurde herrgestellt");
         
         
         BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
         BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
         
         
        String  eingabeClient; // Zuerst wird die URL in einen String eingespeichert. 
    	URL url ;             // In Java ist URL ein eigener Datentyp. und wird deshalb so angegeben
        InputStream is = null;
        BufferedReader br;
        String downloadString;      
 
        eingabeClient= in.readLine();    // Die URL die eingegeben wurde wird in einem String Variable gespeichert. 
        System.out.println("Dies ist Server Seite: ");
        System.out.println(eingabeClient);  // Diese URL hat der Client eingegeben
        try {
           
        	url = new URL(eingabeClient);  // Der String wird in Datentyp URL Variable abgespeichert
        	
            is = url.openStream();  // Mit diesem Aufrauf kann man den Inhalt aus der URL lesen.  
            br = new BufferedReader(new InputStreamReader(is));
 
            downloadString = br.readLine();  // es wird aus dem Bufferreader gelesen und in einem String geschrieben
                System.out.println(downloadString);
                
                out.write(downloadString); // String wird übergeben
                out.newLine();
                out.flush();
               
        } catch (MalformedURLException mue) {
             mue.printStackTrace();
        } catch (IOException ioe) {
             ioe.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                
            }
        }
    }
 
     
}