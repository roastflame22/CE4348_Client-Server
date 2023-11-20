package utd.persistentDataStore.datastoreServer;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import utd.persistentDataStore.datastoreServer.commands.ServerCommand;
import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;

//code is not done
//code working output need to remove the testing statements

public class ReadHandler extends ServerCommand{

	@Override
    public void run() throws IOException, ServerException {
		//Read file on the server
		String inMessage = StreamUtil.readLine(inputStream);
		if (inMessage.length() == 0) {
			// send error
			StreamUtil.sendError("Empty String", outputStream);
			return;
		} 
		else {
			System.out.println("Write response message"); 
			sendOK();
			
			System.out.println("inMessage: " + inMessage);
			byte[] fileContent = FileUtil.readData(inMessage);
	           String Fstring = new String(fileContent, StandardCharsets.UTF_8);
	           // Print the string array
	           System.out.println("File data: " + Fstring);
	           System.out.print("Number of chars in the String: " + Fstring.length() + "\n");
	           String outMessage = Fstring.length() + "\n";
				StreamUtil.writeLine(outMessage, outputStream);
				StreamUtil.writeData(fileContent, outputStream);
				System.out.println("Finished writing message");
	        }
			System.out.println("testing the end of the read file");
		}

}


//File file = new File(inMessage);
//try(FileInputStream fis = new FileInputStream(file);
//        BufferedInputStream bis = new BufferedInputStream(fis)) {
//
//   byte[] fileContent = new byte[(int) file.length()];
//   bis.read(fileContent);

