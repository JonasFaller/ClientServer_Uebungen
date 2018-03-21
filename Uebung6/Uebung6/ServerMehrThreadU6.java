import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;


public class ServerMehrThreadU6 
{
	public static void main(String[] args) throws Exception 
	{
	
		ServerSocket serverSocket = new ServerSocket(2222);
		
		while (true) // Jetzt mit while Schleife da mehrere Clients sich verbinden können, so werden immer neue Threads erstellt
		{
			System.out.println("Server wird gestartet...");
			
			
			Socket clientSocket = serverSocket.accept();
			
			ClientServiceThread clientThread = new ClientServiceThread(clientSocket); // Es wird immer die Klasse aufgerufen.
			clientThread.start();													//um jeden Thread einzeln auszuführen
		}
	}
}

class ClientServiceThread extends Thread 
{
	
	private Socket clientSocket;
	public ClientServiceThread(Socket clientSocket) // dies ist ein Konstruktor
	{
		this.clientSocket = clientSocket;
	}

	
	public void run() 
	{
	
		try 
		{  // Gleich wie bei ServerEinzelnerThread
			DataInputStream dataInput = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream dataOutput = new DataOutputStream(clientSocket.getOutputStream());
			
			while (true) 
			{
				
				String input = dataInput.readUTF();
				
				
				
					
					if (input.toLowerCase().equals("zeit")) 
					{
						String str = getTime();
						dataOutput.writeUTF(str);
					}
					else if (input.toLowerCase().equals("zufaellig")) 
					{
						int num = getRandom();
						dataOutput.writeUTF(Integer.toString(num));
					}
					
				}
			
		}
		catch (Exception e) 	
		{
			e.printStackTrace();
		}
	}
  
  
	private synchronized static int getRandom() throws InterruptedException
	{
		Thread.sleep(2000);						
    	Random rand = new Random();
    	int num = rand.nextInt(99) + 1;			
    	
    	return num;
	}
  
	private synchronized static String getTime() throws InterruptedException
	{
    	Thread.sleep(2000);						
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        
        return sdf.format(cal.getTime());		
    }
}