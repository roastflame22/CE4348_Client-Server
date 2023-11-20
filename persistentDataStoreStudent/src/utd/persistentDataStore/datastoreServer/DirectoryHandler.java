package utd.persistentDataStore.datastoreServer;

import java.io.IOException;
import java.util.List;
import utd.persistentDataStore.datastoreServer.commands.ServerCommand;
import utd.persistentDataStore.utils.StreamUtil;
import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;

public class DirectoryHandler extends ServerCommand {
	@Override
    public void run() throws IOException, ServerException {
		List<String> result = FileUtil.directory();
		System.out.println(result.size());
		for (int x = 0; x < result.size(); x++) {
			System.out.println(result.get(x));
		}
		sendOK();
		StreamUtil.writeLine(result.size() + "", outputStream);
		for (String object : result)
		{
			StreamUtil.writeLine(object, outputStream);
		}
		System.out.println("testing the end of the directory file");
		
		
	}
}
