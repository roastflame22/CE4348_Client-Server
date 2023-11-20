/* NOTICE: All materials provided by this project, and materials derived 
 * from the project, are the property of the University of Texas. 
 * Project materials, or those derived from the materials, cannot be placed 
 * into publicly accessible locations on the web. Project materials cannot 
 * be shared with other project teams. Making project materials publicly 
 * accessible, or sharing with other project teams will result in the 
 * failure of the team responsible and any team that uses the shared materials. 
 * Sharing project materials or using shared materials will also result 
 * in the reporting of all team members for academic dishonesty. 
 */ 
 
package utd.persistentDataStore.datastoreClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;

import utd.persistentDataStore.datastoreServer.DatastoreServer;


public class Client
{
	public static void main(String args[])
	{
		try {
			InetAddress inetAddress = InetAddress.getByName("localhost");
			int port = DatastoreServer.port;
			DatastoreClientImpl client = new DatastoreClientImpl(inetAddress, port);
			
			
			/* Write Function */
			String msg = "Projects.txt\n0032";
			byte[] writeData = "There was two project Verions???".getBytes();
			System.out.println("Sending Request: Write file " + msg);
			client.write(msg, writeData);
			
			System.out.println("Received Response <" + "This is a write dummy" + ">");
			
			/* Write Function */
			msg = "Professors.txt\n0032";
			writeData = "There was two project Verions???".getBytes();
			System.out.println("Sending Request: Write file " + msg);
			client.write(msg, writeData);
			
			System.out.println("Received Response <" + "This is a write dummy" + ">");
			
			/* Write Function */
			msg = "Students.txt\n0032";
			writeData = "There was two project Verions???".getBytes();
			System.out.println("Sending Request: Write file " + msg);
			client.write(msg, writeData);
			
			System.out.println("Received Response <" + "This is a write dummy" + ">");
			
			/* Read Function */
			String nameR = "Projects.txt";
			System.out.println("Sending Request: Read File " + nameR);
			byte[] ReadData = client.read(nameR);
			String parseReadData = new String(ReadData, StandardCharsets.UTF_8);
			System.out.println("Received Response <" + parseReadData + ">");
			
			/* Delete Function */
			String nameD = "Projects.txt";
			System.out.println("Sending Request: Delete File " + nameD);
			client.delete(nameD);
			System.out.println("Received Response <" + "This is a delete dummy" + ">");
			client.delete(nameD);
			
			/* Directory Function */
			List<String> Directory = client.directory();
			for (int x = 0; x < Directory.size(); x++) {
				System.out.println(Directory.get(x));
			}
			
		}
		catch(Exception ex) {
			System.out.println("Exception Thrown " + ex.getMessage());
			ex.printStackTrace();
		}
	}

}
