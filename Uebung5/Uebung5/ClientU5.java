import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
 
public class ClientU5 {
 
  public final static int SOCKET_PORT = 2222;      //Socket Port
  public final static String SERVER = "127.0.0.1";  // es wird IP adresse Angegeben die später als Information zu Socket ausgegeben wird
  public final static int FILE_SIZE = 6022386;   // Es wird die Maximale Größe angegeben
 
  public static void main (String [] args ) throws IOException {
    int bytesRead;
    int current = 0;
    FileOutputStream fos = null;
    BufferedOutputStream bos = null;
    Socket socket = null;
    
  
    
    /*
     * D:\\Java\\Uebung-5\\src\\TextTest.txt
     * D:\\Java\\Uebung-5\\src\\TextTestUmgeschieben.txt
     * */
    
    
    
    
    try {
      socket = new Socket(SERVER, SOCKET_PORT);
    
      
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      BufferedReader daten = new BufferedReader(new InputStreamReader(System.in));
      Scanner sc = new Scanner(System.in);// einfacher Scanner zum lesen was man in die Konsole eingegeben hat
      
   
      // Eingabe der Adresse in die hineingeschrieben werden soll //    // H:\\Desktop\\TP\\ÜbungenServerClient\\Übung05\\Testdokument22.txt //
      
      System.out.println("Geben sie die Adresse ein wo es hineingeschrieben werden soll:"); // Zuerst wird die Adresse eingegeben wo sie hingesendet werden soll 
      System.out.println("\nBeispiel wäre:--> D:\\Java\\Uebung-5\\src\\TextTestUmgeschieben.txt\n");
      String eingabe = sc.next(); // Adresse wird in eingabe hineingespeichert
      
      
      System.out.println("\nGeben sie die Adresse ein welche kopiert werden soll:");
      System.out.println("\nBeispiel wäre:--> D:\\Java\\Uebung-5\\src\\TextTest.txt\n");
      out.write(daten.readLine()); //Eingabe wird an Server gesendet 
      out.newLine(); // Neue Zeile wird bei Bufferwriter gemacht
      out.flush(); // Zwischenspeicher wird gelöscht
   
     
      
      //  Adresse wird eingeschrieben //
      final String Adresse_wo_hineingeschrieben_wird = eingabe;
      
      
 
      // Dieser Teil funktioniert ca. gleich wie Uebung 3
      byte [] mybytearray  = new byte [FILE_SIZE]; // Array wird erstellt. FileSize wird oben angegeben: 6022386.. Dies ist also die maximale Größe
      InputStream is = socket.getInputStream();
      fos = new FileOutputStream(Adresse_wo_hineingeschrieben_wird);
      bos = new BufferedOutputStream(fos);
      bytesRead = is.read(mybytearray,0,mybytearray.length);
      current = bytesRead; // Bytegröße
 
      do {// Hier wird byte für byte gelesen. 
         bytesRead =
            is.read(mybytearray, current, (mybytearray.length-current)); 
         if(bytesRead >= 0) current += bytesRead;
      } while(bytesRead > -1);
 
      bos.write(mybytearray, 0 , current);  // Wird in neue Adresse eingeschrieben
      bos.flush();
      System.out.println("Dokument wurde in neue Adresse geschrieben (" + current + " bytes wurden ausgelesen)");
    }
    finally {
      if (fos != null) fos.close();
      if (bos != null) bos.close();
      if (socket != null) socket.close();
    }
  }
 
}