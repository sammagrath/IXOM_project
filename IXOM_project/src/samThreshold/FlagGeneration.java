package samThreshold;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import Read_Data.CSV2Array;
import Read_Data.dataPoint;
import henrysThreshold.metricTaker;

/**
 * @author magratsam
 *
 */


public class FlagGeneration {
	

	
	private static ArrayList<dataPoint> data;
	private metricTaker metric;
	private static File input;
	
	public FlagGeneration(ArrayList<dataPoint> data) {
		
		this.data = data;
		metric = new metricTaker(data);
	}
	
	public void flagOne() {
		
		HashMap<Integer,Double> condAverages = new HashMap<Integer,Double>();
		
		metric.getCondAverages();

		System.out.println("hi");
		
		
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		input = new File("/home/magratsam/git/cleanfile5.csv");
		CSV2Array c = new CSV2Array();
		
		
		data = c.populateData(input, data);
		
		for(dataPoint d: data){
			System.out.println(d.print());
		}
		
		FlagGeneration f = new FlagGeneration(data);
		f.flagOne();
		
	}
	
}
