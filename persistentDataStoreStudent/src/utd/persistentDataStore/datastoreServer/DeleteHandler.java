package utd.persistentDataStore.datastoreServer;

import java.io.File;
import java.io.IOException;

import utd.persistentDataStore.datastoreServer.commands.ServerCommand;
import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;


public class DeleteHandler extends ServerCommand{
	public void run() throws IOException, ServerException {
		// Read message 
		String inMessage = StreamUtil.readLine(inputStream);
		System.out.println("inMessage: " + inMessage);
		
		if (inMessage.length() == 0) {
			// send error
			StreamUtil.sendError("Empty String", outputStream);
			return;
		} 
		else {
			System.out.println("Deletion Starting");
			sendOK();
			
			FileUtil.deleteData(inMessage);
//			File file = new File(inMessage);
//			if (file.exists()) {
//	            boolean result = file.delete();
//	            if (result) {
//	                System.out.println("File deleted successfully.");
//	                sendOK();
//	            } else {
//	                System.out.println("Failed to delete the file.");
//	            }
//	        } else {
//	            System.out.println("File does not exist.");
//	        }
			System.out.println("Finished Deletion operation");
		}
	}
}
