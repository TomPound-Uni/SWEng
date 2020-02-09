package tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StlParser {
	String fileType = "stl";
	public void stlParse(File file) throws FileNotFoundException {
		if(fileType == "stl") {
			//Buffered Read
			//Read in file
			//Or use scanner
			//
			
			Scanner facet_scanner = new Scanner(file);
			facet_scanner.useDelimiter("solid | facet normal | outer loop | vertex | endloop | endfacet");
			while(facet_scanner.next() != null) {
				System.out.println(facet_scanner.next()); 
			}
			
			
		}else{
			System.out.print("File type is not supported");
		}
		
		
		
	}
	
	
	
}
