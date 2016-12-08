package Read_Data;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.Scanner;

import csvcleaner.CSVCleaner;
//ewfweef
/*
 * 
 * author: Patrick Smith
 * email: patrickmsmith0@gmail.com
 * 
 */

public class CSV2Array {

	private static ArrayList<dataPoint> data;

	public static void populateData(String filename) throws FileNotFoundException {

		Scanner linescanner = new Scanner(new File(filename));

		data = new ArrayList<dataPoint>();

		CSVCleaner c = new CSVCleaner(); 
		
		while (linescanner.hasNextLine()) {

			
			
			String line = c.lineCleaner(linescanner.nextLine());

			Scanner scanner = new Scanner(line);
			scanner.useDelimiter(",");

			dataPoint d = new dataPoint(scanner.next(), scanner.next(), scanner.nextDouble(), scanner.nextDouble(),
					scanner.nextDouble(), scanner.nextDouble(), scanner.nextInt());
			data.add(d);
			scanner.close();

		}

		linescanner.close();

	}

	public static void printAll(ArrayList<dataPoint> data) {

		for (dataPoint output : data) {

			output.print();

		}

	}
	
	//testing array input


}
