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
			for(String assignment : data){
				//
				String[] assign = assignment.split("X__X");
				String sender = assign[0];
				String[] receivers = Arrays.copyOfRange(assign,1,assign.length);
				
			    PrintWriter writer = new PrintWriter(p + "/" + sender.split(" - ")[0] + ".txt", "UTF-8");
			    writer.println(sender);
			    writer.println();
			    for(String r : receivers){
			    	writer.println(r);
			    	writer.println();
			    }
			    writer.close();
			}
		} catch (Exception e) {
		   // do something
		}		
	}

}

// 16
// 77