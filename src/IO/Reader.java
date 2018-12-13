package IO;
import java.util.Scanner;

import data.Graph;

public class Reader {
	private final static int CAPACITY = 3;

	public static Graph read(String file) {
		Scanner sc = IOcreator.createScanner(file);
		if(sc == null) 
			return null;
		
		Graph g = new Graph(CAPACITY);
		sc.nextLine(); //remove line with titles
		
		while(sc.hasNextLine()){
			String s = sc.nextLine();
			// Merge data-points with newlines in them. Each datapoint is enclosed by "data"
			while(!s.substring(s.length() - 1).equals("\"")) 
				s = s + "\n" + sc.nextLine();

			String[] data = s.split("\",\"");
			g.addData(InputOracle.createEntry(data));
		}
		return g;
	}

}
