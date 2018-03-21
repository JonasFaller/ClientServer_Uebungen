import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
 
public class ClientUe6
{
    public static void main(String[] args) throws IOException
    {
        Socket socket = new Socket(InetAddress.getLocalHost(), 2222);			// Socket starten, auf IP + Port
        
        // Um Daten an den Server zu schicken + empfangen
        DataInputStream dataInput = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
        
        // String fuer die Antwort
        String stringeingabe = null;
        
        while (true)
        {
        	
        	Scanner sc = new Scanner(System.in);
        
        	
        	
        	System.out.println("Wählen zwischen Zeitangabe, Zufällige Zahl oder Beenden und schreiben die jeweilige Zahl hinein:  ");
        	System.out.println("\n0. Zeit\n1. Zufällige Zahl\n");
        	System.out.println("\n\nEingabe: ");
        	
        	String eingabe = sc.next();  
        	int auswahl = Integer.parseInt(eingabe); // wird in auswahl gespeichert
        	
       
            if(auswahl == 0) // wenn null eingegeben wird in in stringeingabe zeit 
            	stringeingabe = "zeit";
            else if(auswahl == 1)
            	stringeingabe = "zufaellig";
           

         
            dataOutput.writeUTF(stringeingabe);// wird an Server gesendet
            
        
            String answer = dataInput.readUTF(); // Nachricht vom Server wird in answer hineingeschrieben und ausgegeben2
            System.out.println(answer);
        }
    }
}