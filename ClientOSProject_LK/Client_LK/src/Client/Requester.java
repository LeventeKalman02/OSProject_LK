package Client;

import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Requester{
	Socket requestSocket;
	ObjectOutputStream out;
 	ObjectInputStream in;
 	String message;
 	String response;
 	Scanner input;
	Requester(){
		
		input = new Scanner(System.in);
	}
	void run()
	{
		try{
			//1. creating a socket to connect to the server
			
			requestSocket = new Socket("127.0.0.1", 2004);
			System.out.println("Connected to localhost in port 2004");
			//2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			//3: Communicating with the server
			
			
			
			//Client Comms
			try 
			{
				
				do
				{
					message = (String)in.readObject();
					System.out.println(message);
					response = input.next();
					sendMessage(response);
					
					if(response.equalsIgnoreCase("1"))
					{
						message = (String)in.readObject();
						System.out.println(message);
						response = input.next();
						sendMessage(response);
						
						message = (String)in.readObject();
						System.out.println(message);
						response = input.next();
						sendMessage(response);
						
						message = (String)in.readObject();
						System.out.println(message);
						response = input.next();
						sendMessage(response);
					}
					
					else if(response.equalsIgnoreCase("2"))
					{
						message = (String)in.readObject();
						System.out.println(message);
						response = input.next();
						sendMessage(response);
						
						message = (String)in.readObject();
						
						System.out.println(message);
						
											}
					
					message = (String)in.readObject();
					System.out.println(message);
					response = input.next();
					sendMessage(response);
					
				}while(response.equalsIgnoreCase("1"));
				
			} 
			
			
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		catch(UnknownHostException unknownHost){
			System.err.println("You are trying to connect to an unknown host!");
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{
			//4: Closing connection
			try{
				in.close();
				out.close();
				requestSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			System.out.println("client>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		Requester client = new Requester();
		client.run();
	}
}