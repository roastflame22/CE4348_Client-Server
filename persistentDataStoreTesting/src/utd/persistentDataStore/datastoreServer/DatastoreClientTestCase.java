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
 
package utd.persistentDataStore.datastoreServer;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Random;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import org.junit.Test;

import utd.persistentDataStore.datastoreClient.*;

public class DatastoreClientTestCase
{
	int port = 10023;
	//String address = "localhost";
	String address = "ec2-54-191-96-81.us-west-2.compute.amazonaws.com";
	int dataSize = 100000;
	

	private InetAddress getAddress() throws UnknownHostException
	{
		InetAddress inetAddress = InetAddress.getByName(address);
		return inetAddress;
	}

	@Test
	public void testWrite() throws Exception
	{
		InetAddress address = getAddress();
		DatastoreClient dsClient = new DatastoreClientImpl(address, port);

		byte data[] = generateData(dataSize);
		dsClient.write("testData", data);
	}

	@Test
	public void testWrite2() throws Exception
	{
		InetAddress address = getAddress();
		DatastoreClient dsClient = new DatastoreClientImpl(address, port);

		byte data[] = generateData(dataSize);
		String fname = "testData " + String.valueOf(System.currentTimeMillis());
		dsClient.write(fname, data);
	}

	@Test
	public void testRead() throws Exception
	{
		InetAddress address = getAddress();
		DatastoreClient dsClient = new DatastoreClientImpl(address, port);

		byte dataOut[] = generateData(dataSize);
		dsClient.write("testData", dataOut);

		byte dataIn[] = dsClient.read("testData");
		assertEquals(dataSize, dataIn.length);

		Checksum dataOutChecksum = new CRC32();
		dataOutChecksum.update(dataOut, 0, dataOut.length);
		long checksumOut = dataOutChecksum.getValue();

		Checksum dataInChecksum = new CRC32();
		dataInChecksum.update(dataIn, 0, dataIn.length);
		long checksumIn = dataInChecksum.getValue();

		assertEquals(checksumOut, checksumIn);
	}

	/**
	 * Attempt to read named data that does not exist on the server. Expect a
	 * ClientException
	 */
	@Test(expected = ClientException.class)
	public void testReadBroken() throws Exception
	{
		InetAddress address = getAddress();
		DatastoreClient dsClient = new DatastoreClientImpl(address, port);

		byte dataIn[] = dsClient.read("missingData");
	}

	@Test
	public void testDelete() throws Exception
	{
		InetAddress address = getAddress();
		DatastoreClient dsClient = new DatastoreClientImpl(address, port);

		byte data[] = generateData(dataSize);
		dsClient.write("testData", data);

		dsClient.delete("testData");
	}

	/**
	 * Attempt to delete named data that does not exist on the server. Expect a
	 * ClientException
	 */
	@Test(expected = ClientException.class)
	public void testDeleteBroken() throws Exception
	{
		InetAddress address = getAddress();
		DatastoreClient dsClient = new DatastoreClientImpl(address, port);

		dsClient.delete("missingData");
	}

	@Test
	public void testDirectory() throws Exception
	{
		InetAddress address = getAddress();
		DatastoreClient dsClient = new DatastoreClientImpl(address, port);

		byte data[] = generateData(dataSize);
		dsClient.write("testData", data);

		List<String> names = dsClient.directory();
		assertTrue(names.size() > 0);
		for (String name : names) {
			System.out.println(name);
		}
	}

	private byte[] generateData(int size)
	{
		byte data[] = new byte[size];
		Random random = new Random();
		random.nextBytes(data);
		return data;
	}
}
