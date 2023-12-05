package Server;

import java.net.Socket;
import java.io.*;

public class ServerThread extends Thread{

	Socket myConnection;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	String message2;
	String message3;
	Library myLib;
	
	public ServerThread(Socket s,Library list)
	{
		myConnection = s;
		myLib = list;
	}
	
	public void run()
	{
		try
		{
			out = new ObjectOutputStream(myConnection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(myConnection.getInputStream());
		
			//Server Comms
			
			do
			{
				sendMessage("Please enter 1 to ADD A BOOK or 2 to SEARCH FOR A BOOK");
				message = (String)in.readObject();
			
				if(message.equalsIgnoreCase("1"))
				{
					sendMessage("Please enter the book title");
					message = (String)in.readObject();
					
					sendMessage("Please enter the book author");
					message2 = (String)in.readObject();
					
					sendMessage("Please enter the book price");
					message3 = (String)in.readObject();
					
					//Add the book to the list....		
					myLib.addBook(message, message2, message3);
				}
				
				else if(message.equalsIgnoreCase("2"))
				{
					sendMessage("Please enter the book title");
					message = (String)in.readObject();
					
					//Search for the book
					message = myLib.searchBook(message);
					sendMessage(message);
				}
				
				sendMessage("Please enter 1 to repeat");
				message = (String)in.readObject();
				
			}while(message.equalsIgnoreCase("1"));
			
			in.close();
			out.close();
		}
		catch(ClassNotFoundException classnot)
		{
					System.err.println("Data received in unknown format");
		}
		catch(IOException e)
		{
			
		}
		
		
	}
	
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			System.out.println("server>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
}
