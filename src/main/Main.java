package main;

import IO.Reader;
import IO.Writer;
import data.Graph;

public class Main {
	
	// Main function
	// Arguments: [loc_of_input_file, loc_of_output_file]
	public static void main(String[] args){
		String src;
		String resultMap;
		if(args.length<1 || args.length > 2){
			System.out.println("Assign target_csv [output_directory]");
		}
		else
		{
			src = args[0];
			if (args.length < 2)
				resultMap = "result";
			else
				resultMap = args[1];

			Graph data = Reader.read(src);
			if(data != null){
				int resultFlow = data.overflow();
				if(data.isOptimalFlow(resultFlow) || data.fixFlow(resultFlow)){
					Writer.writeTo(resultMap, data.allToString());
                }
                else
					System.out.println("No viable solution");
			}
			else
				System.out.println("A Error occured while loading data.");
		}
	}
	
}
