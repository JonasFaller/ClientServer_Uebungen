import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
 
public class ServerU5 {
 
  public final static int SOCKET_PORT = 2222; // Port
 // public final static String FILE_TO_SEND = "H:\\Desktop\\TP\\ÜbungenServerClient\\Übung05\\Testdokument.txt";  // Hier kann man den File einsetzen
 
  public static void main (String [] args ) throws IOException {
	  
	  
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream os = null;
    ServerSocket servsocket = null;
    Socket socketclient = null;
    
    
    try {
      servsocket = new ServerSocket(SOCKET_PORT);    // verbindung wird herrgestellt
      while (true) {
        System.out.println("Server wird gestartet...");
        try {
          socketclient = servsocket.accept();
          System.out.println("Verbindung wurde akzeptiert : " + socketclient);
          // send file
          
          
          BufferedReader in = new BufferedReader(new InputStreamReader(socketclient.getInputStream()));
          BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socketclient.getOutputStream()));
          
          String adresse= in.readLine();
          
          System.out.println("Dies ServerSeite: ");
          System.out.println(adresse);
          final String FILE_TO_SEND = adresse;
          
          
          // Diesen Code habe aus dem Internet kopiert und ihn so darauf angepasst dass er funktioniert. 
          // und nur den File-Transfer macht.
          
          File myFile = new File (FILE_TO_SEND);  // File der Gesendet werden muss wird in myFile eingespeichert
          
          byte [] mybytearray  = new byte [(int)myFile.length()];  //Die byte Länge wird ermittelt und ein Array gemacht. 
          
          fis = new FileInputStream(myFile);  
          bis = new BufferedInputStream(fis);
          bis.read(mybytearray,0,mybytearray.length);
          os = socketclient.getOutputStream();
          System.out.println("Server sendet: " + FILE_TO_SEND + "(" + mybytearray.length + " bytes)"); // es wird nur ausgegeben was und in welcher Größe gesendet wird
          os.write(mybytearray,0,mybytearray.length); // wird dem Client gesendet. In den Zwischenspeicher eingeschrieben
          os.flush();
          System.out.println("Done.");
        }
        finally {
          if (bis != null) bis.close();
          if (os != null) os.close();
          if (socketclient!=null) socketclient.close();
        }
      }
    }
    finally {
      if (servsocket != null) servsocket.close();
    }
  }
}