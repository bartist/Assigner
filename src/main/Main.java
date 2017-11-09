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
		if(false) {//args.length<1 || args.length > 2){
			System.out.println("Assign inputfile [output_directory]");
		}
		else
		{
			//src = args[0];
			src = "test.csv";
			if (args.length < 2)
				res = "result";
			else
				res = args[1];

			Graph data = Reader.read(src);
			if(data != null){
				int resflow = data.overflow();
				if(!data.flowcheck(resflow)){
				    data.fixFlow(resflow);
                }
                Writer.writeTo(res, data.allToString());
			}
			else
				System.out.println("A Error occured while loading data.");
		}
	}
	
}
