package samThreshold;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import Read_Data.CSV2Array;
import Read_Data.dataPoint;
import henrysThreshold.metricTaker;

public class FlagGeneration {
	
	private static ArrayList<dataPoint> data;
	private metricTaker metric;
	private static File input;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		input = new File("/home/magratsam/git/IXOM_project/IXOM_project/src/Read_Data.cleanfile.csv");
		CSV2Array c = new CSV2Array();
		c.populateData(input, data);
		for(dataPoint d: data){
			System.out.println(d.print());
			
		}
	}
	
	public FlagGeneration(ArrayList<dataPoint> data) {
		
		this.data = data;
		metricTaker metric = new metricTaker(data);
	}
	
	public void Flag1() {
		
		for (int i = 0; i < metric.getCondAverages().size(); i++) {
			System.out.println(metric.getCondAverages().get(i));
		}
		
	}
	
}
