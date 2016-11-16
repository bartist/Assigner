package IO;
import java.util.Scanner;

import data.Graph;

public class Reader {

	public static Graph read(String file) {
		Scanner sc = IOcreator.createScanner(file);
		
		Graph g = new Graph();
		sc.nextLine(); //remove line with titles
		
		while(sc.hasNextLine()){
			String s = sc.nextLine();
			// Merge data-points with newlines in them. Each datapoint is enclosed by "data"
			while(!s.substring(s.length() - 1).equals("\"")) 
				s = s + "\n" + sc.nextLine();

			String[] data = s.split("\",\"");
			g.addData(data);
		}
		return g;
	}

}
