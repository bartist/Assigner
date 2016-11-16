package IO;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class IOcreator {

	public static Scanner createScanner(String s){
		try {
			Scanner sc = new Scanner(new BufferedReader(new FileReader(s)));
			return sc;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
