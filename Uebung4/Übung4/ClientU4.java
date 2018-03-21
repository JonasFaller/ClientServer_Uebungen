import java.io.*;
import java.net.*;

public class ClientU4
{
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException
	{
		Socket SOCKET = new Socket("localhost", 2222);
		
		
		ObjectOutputStream OUT = new ObjectOutputStream(SOCKET.getOutputStream());
		ObjectInputStream IN = new ObjectInputStream(SOCKET.getInputStream());
		
		BufferedReader DATEN = new BufferedReader(new InputStreamReader(System.in));
		
		byte[][] FELD = new byte[7][6];
		
		int ZUG;
		int CHECK = 0;
		int COUNT = 0;
		while(CHECK == 0)
		{
			for(int Y = 0; Y < 7; Y++)
			{
				for(int X = 0; X < 6; X++)
				{
					System.out.print(FELD[Y][X]);
					System.out.print(" ");
				}
				System.out.println("");
			}
			
			System.out.print("[1 - 6]: ");
			ZUG = Integer.parseInt(DATEN.readLine()) - 1;
			for(int COUNTER = 6; COUNTER >= 0; COUNTER--)
			{
				if(FELD[COUNTER][ZUG] == 0)
				{
					FELD[COUNTER][ZUG] = 1;
					break;
				}
			}
			OUT.writeObject(FELD);
			
			FELD = (byte[][]) IN.readObject();
			
			
			for(int Y = 0; Y < 7; Y++)
			{
				for(int X = 0; X < 6; X++)
				{
					if(FELD[Y][X] != 0)
					{
						COUNT++;
					}
				}
			}
			if(COUNT == 42)
			{
				CHECK = 3;
			}
			for(int Y = 0; Y < 7; Y++)
			{
				for(int X = 0; X < 3; X++)
				{
					if(FELD[Y][X] == 1 && FELD[Y][X + 1] == 1 && FELD[Y][X + 2] == 1 && FELD[Y][X + 3] == 1)
					{
						CHECK = 1;
					}
					else if(FELD[Y][X] == 2 && FELD[Y][X + 1] == 2 && FELD[Y][X + 2] == 2 && FELD[Y][X + 3] == 2)
					{
						CHECK = 2;
					}
				}
			}
			for(int Y = 0; Y < 4; Y++)
			{
				for(int X = 0; X < 6; X++)
				{
					if(FELD[Y][X] == 1 && FELD[Y + 1][X] == 1 && FELD[Y + 2][X] == 1 && FELD[Y + 3][X] == 1)
					{
						CHECK = 1;
					}
					else if(FELD[Y][X] == 2 && FELD[Y + 1][X] == 2 && FELD[Y + 2][X] == 2 && FELD[Y + 3][X] == 2)
					{
						CHECK = 2;
					}
				}
			}
			for(int Y = 0; Y < 4; Y++)
			{
				for(int X = 0; X < 3; X++)
				{
					if(FELD[Y][X] == 1 && FELD[Y + 1][X + 1] == 1 && FELD[Y + 2][X + 2] == 1 && FELD[Y + 3][X + 3] == 1)
					{
						CHECK = 1;
					}
					else if(FELD[Y][X] == 2 && FELD[Y + 1][X + 1] == 2 && FELD[Y + 2][X + 2] == 2 && FELD[Y + 3][X + 3] == 2)
					{
						CHECK = 2;
					}
				}
			}
			for(int Y = 0; Y < 4; Y++)
			{
				for(int X = 3; X < 6; X++)
				{
					if(FELD[Y][X] == 1 && FELD[Y + 1][X - 1] == 1 && FELD[Y + 2][X - 2] == 1 && FELD[Y + 3][X - 3] == 1)
					{
						CHECK = 1;
					}
					else if(FELD[Y][X] == 2 && FELD[Y + 1][X - 1] == 2 && FELD[Y + 2][X - 2] == 2 && FELD[Y + 3][X - 3] == 2)
					{
						CHECK = 2;
					}
				}
			}
			if(CHECK == 1)
			{
				System.out.println("Sie haben gewonnen!");
				OUT.writeObject(FELD);
				break;
			}
			else if(CHECK == 2)
			{
				System.out.println("Sie haben verloren!");
				OUT.writeObject(FELD);
				break;
			}
			else if(CHECK == 3)
			{
				System.out.println("Feld ist voll!");
				OUT.writeObject(FELD);
				break;
			}
		}
		
		OUT.close();
		IN.close();
		SOCKET.close();
	}
}