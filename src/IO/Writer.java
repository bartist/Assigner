package IO;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;

public class Writer {

	public static void writeTo(String directory, String[] data) {
		String p = directory;
		File path = new File(directory);
		// Create clear directory or delete content of directory
		if(!path.exists()) {
			path.mkdir();
		}
		else
		{
			int i = 1;
			while(path.exists()){
				p = directory + "(" + i + ")";
				path = new File(p);
				i++;
			}
		}
		
		try{
			for(String information : data) {
			    PrintWriter writer = new PrintWriter(p + "/" + information.split(" - ")[0] + ".txt", "UTF-8");
			    writer.println(information);
			    writer.close();
			}
		} catch (Exception e) {
		   // do something
		}		
	}

}

// 16
// 77