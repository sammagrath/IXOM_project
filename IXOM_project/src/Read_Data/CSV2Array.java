package Read_Data;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.Scanner;

import csvcleaner.CSVCleaner;

/*
 * 
 * author: Patrick Smith
 * email: patrickmsmith0@gmail.com
 * 
 */

public class CSV2Array {

	public ArrayList<dataPoint> populateData(File dirtyCSVFile, ArrayList<dataPoint> data) throws FileNotFoundException {

		Scanner linescanner = new Scanner(dirtyCSVFile);

		data = new ArrayList<dataPoint>();

		CSVCleaner c = new CSVCleaner(); 
		
		linescanner.nextLine();
		linescanner.nextLine();
		
		while (linescanner.hasNextLine()) {

			
			
			String line = c.lineCleaner(linescanner.nextLine());
			
			if(!line.contains("null")){

				System.out.println(line);
				
				Scanner scanner = new Scanner(line);
				scanner.useDelimiter(", ");

				dataPoint d = new dataPoint(scanner.next(), scanner.next(), scanner.nextDouble(), scanner.nextDouble(),
						scanner.nextDouble(), scanner.nextDouble(), (int) scanner.nextDouble());
				data.add(d);
				scanner.close();
			}
			
		}

		linescanner.close();
		return data;

	}

	public static void printAll(ArrayList<dataPoint> data) {

		for (dataPoint output : data) {

			output.print();

		}

	}
	
	//testing array input


}
