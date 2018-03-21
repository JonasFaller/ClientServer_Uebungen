import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
	 
	
public class ServerEinzelnerThreadU6 
{
    public static void main(String args[]) throws IOException, InterruptedException
    {
    	// Socket erstellen
		ServerSocket serverSocket = new ServerSocket(2222);
		System.out.println("Server wird gestartet...");
		Socket socket = serverSocket.accept();// Eine Anfrage wird angenommmen 
 
       
        DataInputStream dataInput = new DataInputStream(socket.getInputStream()); 
        DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
       
        while (true)
        {
			
            String clienteingabe = dataInput.readUTF(); // Nachricht vom Client wird ausgelesen
 
           
           
            if (clienteingabe.toLowerCase().equals("zeit"))//Wenn Client zeit ausgewählt hat wird die zeit mit der Funktion getTime 
            {													//in die Variable antwort eingeschrieben und übergeben
            	String antwort = getTime();
				dataOutput.writeUTF(antwort);
            }
            else if (clienteingabe.toLowerCase().equals("zufaellig")) 
            {
            	int zufallszahl = getRandom(); // eine zufällige Zahl wird erstellt und übergegben
				dataOutput.writeUTF(Integer.toString(zufallszahl));
            }
            
        }
    }
    
    private static  int getRandom() throws InterruptedException		// synchronized kann weggelassen werden da wir nur einen Thread haben
	{
		Thread.sleep(2000);							// Thread schläft da berechnung lange dauern soll
    	Random random = new Random();
    	int zufallszahl = random.nextInt(1000) + 1;				// Zufallszahl zwischen 1 und 1000
    	
    	return zufallszahl;
	}
    
    private static String getTime() throws InterruptedException
	{
    	Thread.sleep(2000);							
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat datumsformat = new SimpleDateFormat("HH:mm:ss");
        
        return datumsformat.format(cal.getTime());			// Datum wird zurückgegeben 	
    }
}