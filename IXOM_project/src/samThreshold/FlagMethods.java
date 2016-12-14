package samThreshold;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import Read_Data.CSV2Array;
import Read_Data.dataPoint;
import henrysThreshold.metricTaker;

public class FlagMethods {

	
	private static ArrayList<Flag> flags;
	private static ArrayList<dataPoint> data;
	private metricTaker metric;
	private static File input;
	
	public FlagMethods(ArrayList<dataPoint> data) {
		
		FlagMethods.data = data;
		flags = new ArrayList<Flag>();
		metric = new metricTaker(data);		
		
	}
	
	public void populateFlags() {
		
	}
	
	public void conductivityFlag(int CIP, double lower, double upper) {
		
		Flag condFlag = new Flag();
		
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//User must change directory to local in order to test
		input = new File("/home/magratsam/git/cleanfile5.csv");
		CSV2Array c = new CSV2Array();
				
		data = c.populateData(input, data);
				
		FlagMethods fm = new FlagMethods(data);

				
		//Console print of flags
		for(Flag flag: flags) {
			System.out.println();
			System.out.println(flag.print());
		}
				
	}
}
