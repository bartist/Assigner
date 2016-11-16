package main;

import IO.Reader;
import IO.Writer;
import data.Graph;

public class Main {
	
	// Main function
	// Arguments: [loc_of_input_file, loc_of_output_file]
	public static void main(String[] args){
		String src;
		String res;
		if(args.length<1 || args.length > 2){
			src = "testfile.csv";
			res = "result";
		}
		else if(args.length == 1){
			src = args[0];
			res = "result";
		}
		else
		{
			src = args[0];
			res = args[1];
		}
		
		Graph data = Reader.read(src);
		data.overflow();
		//data.print();
		Writer.writeTo(res, data.allToString());
	}
	
}
