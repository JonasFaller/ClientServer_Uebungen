import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientU3 {
	 public static void main(String[] args) throws UnknownHostException, IOException{
         
	     
	        Socket client = new Socket("localhost", 2222);
	        System.out.println("Client wurde gestartet");
	         
	        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
	        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
	        BufferedReader daten = new BufferedReader(new InputStreamReader(System.in)); 

	        System.out.print("Bei der URL muss https:// mit angegeben werden! \nBeispiel URL wäre: \n\nhttps://www.google.de/ \n\n");
	        System.out.print("Geben sie eine URL ein: "); //Eingabe durch den Bildschirm / Konsole
	        out.write(daten.readLine()); //Mit daten.readline wird das in der konsole eingegeben eingelesen und zwischengespeichert  
	        out.newLine(); //              mit out.write wird der String (URL) dem Server übergeben
	        out.flush();  // Zwischenspeicher wird gelöscht
	        
	        FileWriter fw = new FileWriter("URL3-Abspeichern.txt");  // Dies ist das Dokument (es wird hier erstellt) wo die "Website als File" abgespeichert wird
	        BufferedWriter bw = new BufferedWriter(fw);
	        String einspeichern;
	        
	         
	        System.out.println("Ergebnis: ");
	        einspeichern = in.readLine(); // Vom Server wird über die URL der Inhalt der Website ausgelesen. Das Ausgelesen wird als String zwischengespeichert
	        							// und dem Client übergegben. Mit readLine wird wird dies aus dem zwischenspeicher gelesen und in die String Variable 
	        							// einspeichern hinein gespeichert
	        
	        System.out.println(einspeichern);
	        bw.write(einspeichern);   //Hier wird ins die TExtdatei hineigespeichert
	        
	        bw.close();              // Komponenten werden alle geschlossen und Verbindung getrennt
	        out.close(); 
	        in.close(); 
	        client.close();
	    }
	}