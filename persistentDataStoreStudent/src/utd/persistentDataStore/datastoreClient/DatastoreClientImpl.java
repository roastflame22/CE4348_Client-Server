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
import java.util.List;
import utd.persistentDataStore.utils.StreamUtil;

public class DatastoreClientImpl implements DatastoreClient
{
	private InetAddress address;
	private int port;

	public DatastoreClientImpl(InetAddress address, int port)
	{
		this.address = address;
		this.port = port;
	}

	/* (non-Javadoc)
	 * @see utd.persistentDataStore.datastoreClient.DatastoreClient#write(java.lang.String, byte[])
	 */
	@Override
    public void write(String name, byte data[]) throws ClientException, ConnectionException
	{
		try
        {
            System.out.println("Opening Socket");
            Socket socket = new Socket();
            SocketAddress saddr = new InetSocketAddress(address, port);
            socket.connect(saddr);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            

            System.out.println("Writing Message");
            StreamUtil.writeLine("write\n", outputStream);
            StreamUtil.writeLine(name, outputStream);
            StreamUtil.writeData(data, outputStream);

            System.out.println("Reading Response");
            String response = StreamUtil.readLine(inputStream);
            System.out.println("Reading test");
            if (!"ok".equalsIgnoreCase(response.trim()))
            {
                socket.close();
                throw new ClientException("Error Response from Server: " + response);
            }
            System.out.println("Time is up for the reading test");
            //int Nlength = Integer.parseInt(length);
            //byte result[] = StreamUtil.readData(13, inputStream);
            //String output = new String(result, StandardCharsets.UTF_8); // Explicitly using UTF-8

            socket.close();
        }
        catch (IOException ex)
        {
            throw new ClientException(ex.getMessage(), ex);
        }
	}

	/* (non-Javadoc)
	 * @see utd.persistentDataStore.datastoreClient.DatastoreClient#read(java.lang.String)
	 */
	@Override
    public byte[] read(String name) throws ClientException, ConnectionException
	{
		try
        {
            System.out.println("Opening Socket");
            Socket socket = new Socket();
            SocketAddress saddr = new InetSocketAddress(address, port);
            socket.connect(saddr);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            

            System.out.println("Reading Message");
            StreamUtil.writeLine("read\n", outputStream);
            StreamUtil.writeLine(name, outputStream);
            System.out.println("Reading Response");
            String responseOk = StreamUtil.readLine(inputStream);
            System.out.println("Reading test");
            if (!"ok".equalsIgnoreCase(responseOk.trim()))
            {
                socket.close(); 
                throw new ClientException("Error Response from Server: " + responseOk);
            }
            String response = StreamUtil.readLine(inputStream);
            System.out.println("Time is up for the reading test");
            byte result[] = StreamUtil.readData(Integer.parseInt(response), inputStream);
            //String output = new String(result, StandardCharsets.UTF_8); // Explicitly using UTF-8

            System.out.println("Return Message " + result);

            socket.close();
            return result;
        }
        catch (IOException ex)
        {
            throw new ClientException(ex.getMessage(), ex);
        }
	}

	/* (non-Javadoc)
	 * @see utd.persistentDataStore.datastoreClient.DatastoreClient#delete(java.lang.String)
	 */
	@Override
    public void delete(String name) throws ClientException, ConnectionException
	{
		try
        {
            System.out.println("Opening Socket");
            Socket socket = new Socket();
            SocketAddress saddr = new InetSocketAddress(address, port);
            socket.connect(saddr);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            System.out.println("Writing Message");
            StreamUtil.writeLine("delete\n", outputStream);
            StreamUtil.writeLine(name, outputStream);

            System.out.println("Reading Response");
            String response = StreamUtil.readLine(inputStream);
            if (!"ok".equalsIgnoreCase(response.trim()))
            {
                socket.close();
                throw new ClientException("Error Response from Server: " + response);
            }

            socket.close(); 
        }
        catch (IOException ex)
        {
            throw new ClientException(ex.getMessage(), ex);
        }

	}

	/* (non-Javadoc)
	 * @see utd.persistentDataStore.datastoreClient.DatastoreClient#directory()
	 */
	@Override
    public List<String> directory() throws ClientException, ConnectionException
	{
		// Replace with implementation
		throw new RuntimeException("Executing Directory Operation");
	}

}
