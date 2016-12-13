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
		
		condThresholds(flagList);
		endRinseCond(flagList);
		tempThresholds(flagList);
		
		
	}
	
	//measure of conductivity levels across wash phases
	public void condThresholds(ArrayList<Flag> flagList) {
		
		HashMap<Integer,Double> condAverages = new HashMap<Integer,Double>();
		condAverages = metric.getCondAverages();
		
		if (condAverages.get(1) < 0.5) {
			
			Flag flag = new Flag(data.get(metric.getCountsToEffectivePeriod()).getTime(), data.get(metric.getBoundaryIndices().get(1)).getTime(), 1, "Pre-Rinse", "Caustic strength too low", "0.5 - 0.8" , condAverages.get(1));
			flagList.add(flag);
		}
		
		if (condAverages.get(1) > 0.8) {
		
			Flag flag = new Flag(data.get(metric.getCountsToEffectivePeriod()).getTime(), data.get(metric.getBoundaryIndices().get(1)).getTime(), 1, "Pre-Rinse", "Caustic strength too high", "0.5 - 0.8", condAverages.get(1));
			flagList.add(flag);
		}
		
		if (condAverages.get(2) < 1.0) {
			
			Flag flag = new Flag(data.get(metric.getBoundaryIndices().get(1) + metric.getCountsToEffectivePeriod()).getTime(), data.get(metric.getBoundaryIndices().get(2)).getTime(), 2, "Caustic Recirculation", "Caustic strength too low", "1.0 - 1.2", condAverages.get(2));
			flagList.add(flag);
		}
		
		if (condAverages.get(2) > 1.2) {
			
			Flag flag = new Flag(data.get(metric.getBoundaryIndices().get(1) + metric.getCountsToEffectivePeriod()).getTime(), data.get(metric.getBoundaryIndices().get(2)).getTime(), 2, "Caustic Recirculation", "Caustic strength too high", "1.0 - 1.2", condAverages.get(2));
			flagList.add(flag);
		}
		
		if (condAverages.get(4) < 0.8) {
			
			Flag flag = new Flag(data.get(metric.getBoundaryIndices().get(3) + metric.getCountsToEffectivePeriod()).getTime(), data.get(metric.getBoundaryIndices().get(4)).getTime(), 4, "Acid Recirculation", "Acidic strength too low", "0.8 - 1.0", condAverages.get(4));
			flagList.add(flag);
		}
		
		if (condAverages.get(4) > 1.0) {
			
			Flag flag = new Flag(data.get(metric.getBoundaryIndices().get(3) + metric.getCountsToEffectivePeriod()).getTime(), data.get(metric.getBoundaryIndices().get(4)).getTime(), 4, "Acid Recirculation", "Acidic strength too high", "0.8 - 1.0", condAverages.get(4));
			flagList.add(flag);
		}
	}
	
	//Method measures residual conductivity and end of rinse cycles - if non-zero, flag is thrown 
	public void endRinseCond(ArrayList<Flag> flagList) {
		
		double endIntRinseVal;
		double endFinalRinseVal;

		endIntRinseVal = data.get(metric.getBoundaryIndices().get(3)).getConductivity();
		endFinalRinseVal = data.get(data.size()-1).getConductivity();

		if (endIntRinseVal == 0.0) {
			
			Flag flag = new Flag(data.get(metric.getBoundaryIndices().get(2) + metric.getCountsToEffectivePeriod()).getTime(), data.get(metric.getBoundaryIndices().get(3)).getTime(), 3, "Intermediate Rinse", "Conductivity Non-Zero at Rinse End", "0.0", endIntRinseVal);
			flagList.add(flag);
		}
		
		if (endFinalRinseVal == 0.0) {
			
			Flag flag = new Flag(data.get(metric.getBoundaryIndices().get(4) + metric.getCountsToEffectivePeriod()).getTime(), data.get(data.size()-1).getTime(), 5, "Final Rinse", "Conductivity Non-Zero at Rinse End", "0.0", endFinalRinseVal);
			flagList.add(flag);			
		}
		
	}
	
	//Average temperature threshold tests
	public void tempThresholds(ArrayList<Flag> flagList) {
		
		HashMap<Integer,Double> tempAverages = new HashMap<Integer,Double>();
		tempAverages = metric.getTempAverages();
		
		if (tempAverages.get(2) < 70.0) {
			
			Flag flag = new Flag(data.get(metric.getBoundaryIndices().get(1) + metric.getCountsToEffectivePeriod()).getTime(), data.get(metric.getBoundaryIndices().get(2)).getTime(), 2, "Caustic Recirculation", "Average Temperature too low", "70.0 - 85.0", tempAverages.get(2));
			flagList.add(flag);
		}
		
		if (tempAverages.get(2) > 85.0) {
			
			Flag flag = new Flag(data.get(metric.getBoundaryIndices().get(1) + metric.getCountsToEffectivePeriod()).getTime(), data.get(metric.getBoundaryIndices().get(2)).getTime(), 2, "Caustic Recirculation", "Average Temperature too high", "70.0 - 85.0", tempAverages.get(2));
			flagList.add(flag);
		}
		
		if (tempAverages.get(4) < 60.0) {
			
			Flag flag = new Flag(data.get(metric.getBoundaryIndices().get(3) + metric.getCountsToEffectivePeriod()).getTime(), data.get(metric.getBoundaryIndices().get(4)).getTime(), 4, "Acid Recirculation", "Average Temperature too low", "60.0 - 70.0", tempAverages.get(2));
			flagList.add(flag);
		}
		
		if (tempAverages.get(4) > 70.0) {
			
			Flag flag = new Flag(data.get(metric.getBoundaryIndices().get(3) + metric.getCountsToEffectivePeriod()).getTime(), data.get(metric.getBoundaryIndices().get(4)).getTime(), 4, "Acid Recirculation", "Average Temperature too high", "60.0 - 70.0", tempAverages.get(2));
			flagList.add(flag);
		}
		
		
	}
	
	//local main for testing flag methods
	public static void main(String[] args) throws FileNotFoundException {
		
		input = new File("/home/magratsam/git/cleanfile5.csv");
		CSV2Array c = new CSV2Array();
		
		data = c.populateData(input, data);
		
		FlagGeneration f = new FlagGeneration(data);
		f.condThresholds(flagList);
		f.endRinseCond(flagList);
		f.tempThresholds(flagList);
		
		//Console print of flags
//		for(Flag flag: flagList) {
//			System.out.println();
//			System.out.println(flag.print());
//		}
		
	}
	
}
