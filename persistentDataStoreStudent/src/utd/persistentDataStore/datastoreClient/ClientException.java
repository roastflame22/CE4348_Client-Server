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

/**
 * This exception is thrown when the client detects a problem with the 
 * messages received from the server i.e. a message format problem
 */
public class ClientException extends Exception
{
	public ClientException(String msg)
	{
		super(msg);
	}

	public ClientException(String msg, Throwable ex)
	{
		super(msg, ex);
	}

}
