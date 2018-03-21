import java.io.*;
import java.net.*;
 
public class ServerU1{
     
    
    static String operation; 
    static int Zahl1;
    static int Zahl2;
    static int ergebnis = 0;
     
    public static void main(String[] args) throws IOException{
         
        //server-socket-start
        ServerSocket server = new ServerSocket(2222); //Es wird Port 2222 
        System.out.println("Server wird gestartet");
        Socket client = server.accept(); //Mit accept setzt man den port auf warten und sobald eine Anfrage kommt wird die Anfrage angenommen.
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
      
            operation  = in.readLine();  // Die operation wird übergeben und anschließend die Zahlen. 
            Zahl1  = Integer.parseInt(in.readLine());
            Zahl2 = Integer.parseInt(in.readLine());
             
            switch(operation){ // Mit switch werden die verschiedenen operationen ausgewählt 
                case "+":
                    ergebnis = Zahl1 + Zahl2;   
                break;
                 
                case "-":
                    ergebnis = Zahl1 - Zahl2;
                break;
                 
                case "*":
                    ergebnis = Zahl1 * Zahl2;
                break;
                 
                case "/":
                    ergebnis = Zahl1 / Zahl2;
                break;
                 
                default: break;
            }
             
            out.write(Integer.toString(ergebnis)); // das ergebnis wird dem client übergeben. 
            out.newLine();
            out.flush();
             
            in.readLine();
            in.close(); // Die Reader werden alle geschlossen und die Verbindung getrennt
            out.close();
            server.close();
            client.close();
            
        }
}
         
        
         
       
    
 
