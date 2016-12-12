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
	private static ArrayList<Flag> flagList;
	private metricTaker metric;
	private static File input;
	
	public FlagGeneration(ArrayList<dataPoint> data) {
		
		FlagGeneration.data = data;
		flagList = new ArrayList<Flag>();
		metric = new metricTaker(data);
	}
	
	public void runFlags(ArrayList<Flag> flagList) throws FileNotFoundException {
		
		input = new File("/home/magratsam/git/cleanfile5.csv");
		CSV2Array c = new CSV2Array();
		data = c.populateData(input, data);
		
		flagOne(flagList);
		flagTwo(flagList);
		flagThree(flagList);
		flagFour(flagList);
		
	}
	
	public void flagOne(ArrayList<Flag> flagList) {
		
		HashMap<Integer,Double> condAverages = new HashMap<Integer,Double>();
		
		condAverages = metric.getCondAverages();
		
		if (condAverages.get(1) < 0.5) {
			
//			System.out.println("Pre-rinse: Caustic strength too low" + data.get(10).getTime() + "-" + data.get(metric.getBoundaryIndices().get(1)).getTime());
			Flag flag = new Flag(data.get(metric.getCountsToEffectivePeriod()).getTime(), data.get(metric.getBoundaryIndices().get(1)).getTime(), 1, "Pre-Rinse", "Caustic strength too low");
			flagList.add(flag);
		}

	}
	
	public void flagTwo(ArrayList<Flag> flagList) {
		
		HashMap<Integer,Double> condAverages = new HashMap<Integer,Double>();
		
		condAverages = metric.getCondAverages();
		
		if (condAverages.get(1) > 0.8) {
			
//			System.out.println("Pre-rinse: Caustic strength too high" + data.get(10).getTime() + "-" + data.get(metric.getBoundaryIndices().get(1)).getTime());
			Flag flag = new Flag(data.get(metric.getCountsToEffectivePeriod()).getTime(), data.get(metric.getBoundaryIndices().get(1)).getTime(), 1, "Pre-Rinse", "Caustic strength too high");
			flagList.add(flag);
		}

	}
		
	public void flagThree(ArrayList<Flag> flagList) {
		
		HashMap<Integer,Double> condAverages = new HashMap<Integer,Double>();
		
		condAverages = metric.getCondAverages();
		
		if (condAverages.get(2) < 1.0) {
			
			System.out.println();
			System.out.println(data.get(metric.getBoundaryIndices().get(1) + metric.getCountsToEffectivePeriod()).getTime() + "-" + data.get(metric.getBoundaryIndices().get(2)).getTime() + "\n Caustic-recirculation: Caustic strength too low");
			Flag flag = new Flag(data.get(metric.getBoundaryIndices().get(1) + metric.getCountsToEffectivePeriod()).getTime(), data.get(metric.getBoundaryIndices().get(2)).getTime(), 2, "Caustic-Recirculation", "Caustic strength too low");
			flagList.add(flag);
		}

	}
	
	public void flagFour(ArrayList<Flag> flagList) {
		
		HashMap<Integer,Double> condAverages = new HashMap<Integer,Double>();
		
		condAverages = metric.getCondAverages();
		
		if (condAverages.get(2) > 1.2) {
			
			System.out.println();
			System.out.println(data.get(metric.getBoundaryIndices().get(1) + metric.getCountsToEffectivePeriod()).getTime() + "-" + data.get(metric.getBoundaryIndices().get(2)).getTime() + "\n Caustic-recirculation: Caustic strength too high");
			Flag flag = new Flag(data.get(metric.getBoundaryIndices().get(1) + metric.getCountsToEffectivePeriod()).getTime(), data.get(metric.getBoundaryIndices().get(2)).getTime(), 2, "Caustic-Recirculation", "Caustic strength too high");
			flagList.add(flag);
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		input = new File("/home/magratsam/git/cleanfile5.csv");
		CSV2Array c = new CSV2Array();
		
		
		data = c.populateData(input, data);
		
		for(dataPoint d: data){
			System.out.println(d.print());
		}
		
		FlagGeneration f = new FlagGeneration(data);
		f.flagOne(flagList);
		f.flagTwo(flagList);
		f.flagThree(flagList);
		f.flagFour(flagList);
		
		for(Flag flag: flagList) {
			System.out.println();
			System.out.println(flag.print());
		}
		
	}
	
}
