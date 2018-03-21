import java.io.*;
import java.net.*;
 
public class ClientU1{
    public static void main(String[] args) throws UnknownHostException, IOException{
         
     
        Socket client = new Socket("localhost", 2222);
        System.out.println("Client wird gestartet");
        
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        BufferedReader daten = new BufferedReader(new InputStreamReader(System.in)); //Mit System in schreibt man das in der Console eingegebene in Variablen ein.
       
     
         
        System.out.print("Welche Operation moechten Sie durchfuehren: \n + \n - \n * \n / \n");
        out.write(daten.readLine());
        out.newLine();
        out.flush();
         
        System.out.print("\nGeben Sie die erste Zahl: ");
        out.write(daten.readLine());
        out.newLine();
        out.flush();
         
        System.out.print("Geben Sie nun die zweite Zahl: ");
        out.write(daten.readLine());
        out.newLine();
        out.flush();
         
        System.out.println("Ergebnis: " + in.readLine());  // Ergebnis wird ausgegeben. mit in.readline() wird das Ergebnis vom Server übergeben.
         
        out.close(); 
        in.close(); 
        client.close();
    }
}