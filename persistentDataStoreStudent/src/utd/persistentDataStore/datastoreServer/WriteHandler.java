package utd.persistentDataStore.datastoreServer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import utd.persistentDataStore.utils.StreamUtil;
import utd.persistentDataStore.datastoreServer.commands.ServerCommand;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

//needs to be completed current code should mostly be done
/*
 * need to remove test code
 * need to add error handleing
 * need to add polish
 */

public class WriteHandler extends ServerCommand{
	public void run() throws IOException{
		// Read message
		System.out.println("WriteHandler0");
		String Name = StreamUtil.readLine(inputStream);
		byte[] temp = StreamUtil.readData(5, inputStream);
		String temp2 = new String(temp, StandardCharsets.UTF_8);
		System.out.println("did we parse?");
		String[] SplitString = temp2.split("\n");
		System.out.println("SplitString: " + SplitString[0]);
		int inMessage = Integer.parseInt(SplitString[0]);
		System.out.println("WriteHandler1");
	 	byte[] inMessage2 = StreamUtil.readData(inMessage, inputStream);
		//byte[] inMessage2 = StreamUtil.readData(35, inputStream);
		System.out.println(Name);
		System.out.println("WriteHandler2");
		System.out.println("Name " + Name);
		System.out.println("inMessage: " + inMessage);
		System.out.println("data: " + inMessage2.toString());
		
		if (Name.length() == 0) {
			// send error
			StreamUtil.sendError("Empty String", outputStream); 
			return;
		} 
		else {
			System.out.println("Write response message");
			sendOK();
			System.out.println("Tesk ok");
			File Fstream = new File(Name);
            if (Fstream.createNewFile()) {
                System.out.println("File created: " + Fstream.getName());
            } else {
                System.out.println("File already exists.");
            }
            String output = new String(inMessage2, StandardCharsets.UTF_8);
            // Write content to the file
            FileWriter writer = new FileWriter(Fstream);
            writer.write(output);
            writer.close();
            Scanner scanner = new Scanner(Fstream);
            String fileTest = scanner.nextLine();
            System.out.println("First Line" + fileTest);
            scanner.close();

        }
			StreamUtil.writeData(inMessage2, outputStream);
			System.out.println("Finished writing message");
	}
}

