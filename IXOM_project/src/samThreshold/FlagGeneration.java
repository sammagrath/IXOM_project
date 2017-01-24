package samThreshold;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;

import Read_Data.CSV2Array;
import Read_Data.DataPoint;
import henrysThreshold.MetricTaker2;
import henrysThreshold.Phase;
import henrysThreshold.metricTaker;

/**
 * @author magratsam
 *
 */

public class FlagGeneration {

	private static ArrayList<DataPoint> data;
	private static ArrayList<Flag> flagList;
	private static ArrayList<Integer> boundaries;
	private MetricTaker2 metric;
	private static File input;
	private String processName;
	private HashMap<String, ArrayList<Threshold>> processInfo;

	private ArrayList<String> phaseNames = new ArrayList<String>();

	public FlagGeneration(ArrayList<DataPoint> data, String processName, ArrayList<String> phaseNames) {

		FlagGeneration.data = data;
		this.processName = processName;
		

		FetchThresholds ft = new FetchThresholds();

		try {
			processInfo = ft.setThresholds(System.getProperty("user.dir") + File.separator + "thresholds.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		flagList = new ArrayList<Flag>();
		// metric = new metricTaker(data);
		metric = new MetricTaker2(data, phaseNames, 0.5, 60);
	}

	// measure of conductivity levels across wash phases
	public void thresholds(ArrayList<Flag> flagList) {
		
		// applyPhase(data);
		HashMap<Integer, Double> condAverages = new HashMap<Integer, Double>();
		HashMap<Integer, Double> tempAverages = new HashMap<Integer, Double>();
		

		// henry's taint
		// this populates the condAverages and tempAverages, so it can be used
		// as previously done
		for (int i = 0; i < metric.getNumberOfPhases(); i++) {
			tempAverages.put(i + 1, metric.getPhase(i).getTempAverages());
			condAverages.put(i + 1, metric.getPhase(i).getCondAverages());
		}
		//

		// tempAverages = metric.getTempAverages();
		// condAverages = metric.getCondAverages();

		double endIntRinseVal;
		double endFinalRinseVal;

		// endIntRinseVal = data.get(boundaries.get(3)).getConductivity();
		// endFinalRinseVal = data.get(data.size() - 1).getConductivity();

		// Caustic Prerinse chemical strength lower threshold
		for(Phase p : metric.getPhases()) {

			ArrayList<DataPoint> temp = p.getPhaseData();

			if((p.getName().contains("CAUSTIC PRERINSE")) && p.getCondAverages() < processInfo.get(processName).get(0).getCondLower()) {
				
				Flag flag = new Flag(
					
						temp.get(p.getEffectiveStartIndex()).getTime(), 

						temp.get(p.getEndIndex()).getTime(),
						0,
						p.getName(),
						"Chemical strength too low",
						processInfo.get(processName).get(0).getCondLower() + " - " + processInfo.get(processName).get(0).getCondUpper(),
						p.getCondAverages());

						flag.setType("Conductivity");
						flagList.add(flag);
				
			}
		}
		
		//Caustic Prerinse conductivity upper threshold
		for(Phase p : metric.getPhases()) {

			ArrayList<DataPoint> temp = p.getPhaseData();

			if((p.getName().contains("CAUSTIC PRERINSE")) && p.getCondAverages() > processInfo.get(processName).get(0).getCondUpper()) {
				
				Flag flag = new Flag(
					
						temp.get(p.getEffectiveStartIndex()).getTime(), 

						temp.get(p.getEndIndex()).getTime(),
						0,
						p.getName(),
						"Chemical strength too high",
						processInfo.get(processName).get(0).getCondLower() + " - " + processInfo.get(processName).get(0).getCondUpper(),
						p.getCondAverages());

						flag.setType("Conductivity");
						flagList.add(flag);
				
			}
		}

//		//Caustic Prerinse temperature lower threshold
		for(Phase p : metric.getPhases()) {

			ArrayList<DataPoint> temp = p.getPhaseData();

			if((p.getName().contains("CAUSTIC PRERINSE")) && p.getTempAverages() < processInfo.get(processName).get(0).getTempLower()) {
				
				Flag flag = new Flag(
						"start",
//						temp.get(p.getEffectiveStartIndex()).getTime(), 
						"end",
//						temp.get(p.getEndIndex()).getTime(),
						0,
						p.getName(),
						"Average temperature too low",
						processInfo.get(processName).get(0).getTempLower() + " - " + processInfo.get(processName).get(0).getTempUpper(),
						p.getTempAverages());

						flag.setType("Temperature");
						flagList.add(flag);
				
			}
		}
		
//		//Caustic Prerinse temperature upper threshold
		for(Phase p : metric.getPhases()) {

			ArrayList<DataPoint> temp = p.getPhaseData();

			if((p.getName().contains("CAUSTIC PRERINSE")) && p.getTempAverages() > processInfo.get(processName).get(0).getTempUpper()) {
				
				Flag flag = new Flag(
						"start",
//						temp.get(p.getEffectiveStartIndex()).getTime(), 
						"end",
//						temp.get(p.getEndIndex()).getTime(),
						0,
						p.getName(),
						"Average temperature too high",
						processInfo.get(processName).get(0).getTempLower() + " - " + processInfo.get(processName).get(0).getTempUpper(),
						p.getTempAverages());

						flag.setType("Temperature");
						flagList.add(flag);
				
			}
		}
		
		//Acid Prerinse chemical strength lower threshold
		for(Phase p : metric.getPhases()) {

			ArrayList<DataPoint> temp = p.getPhaseData();

			if((p.getName().contains("ACID PRERINSE")) && p.getCondAverages() < processInfo.get(processName).get(1).getCondLower()) {
				
				Flag flag = new Flag(
					
						temp.get(p.getEffectiveStartIndex()).getTime(), 

						temp.get(p.getEndIndex()).getTime(),
						0,
						p.getName(),
						"Chemical strength too low",
						processInfo.get(processName).get(1).getCondLower() + " - " + processInfo.get(processName).get(1).getCondUpper(),
						p.getCondAverages());

						flag.setType("Conductivity");
						flagList.add(flag);
				
			}
		}
		
		//Acid Prerinse conductivity upper threshold
		for(Phase p : metric.getPhases()) {

			ArrayList<DataPoint> temp = p.getPhaseData();

			if((p.getName().contains("ACID PRERINSE")) && p.getCondAverages() > processInfo.get(processName).get(1).getCondUpper()) {
				
				Flag flag = new Flag(
					
						temp.get(p.getEffectiveStartIndex()).getTime(), 

						temp.get(p.getEndIndex()).getTime(),
						0,
						p.getName(),
						"Chemical strength too high",
						processInfo.get(processName).get(1).getCondLower() + " - " + processInfo.get(processName).get(1).getCondUpper(),
						p.getCondAverages());

						flag.setType("Conductivity");
						flagList.add(flag);
				
			}
		}

//		//Acid Prerinse temperature lower threshold
		for(Phase p : metric.getPhases()) {

			ArrayList<DataPoint> temp = p.getPhaseData();

			if((p.getName().contains("CAUSTIC PRERINSE")) && p.getTempAverages() < processInfo.get(processName).get(1).getTempLower()) {
				
				Flag flag = new Flag(
						"start",
//						temp.get(p.getEffectiveStartIndex()).getTime(), 
						"end",
//						temp.get(p.getEndIndex()).getTime(),
						0,
						p.getName(),
						"Average temperature too low",
						processInfo.get(processName).get(1).getTempLower() + " - " + processInfo.get(processName).get(1).getTempUpper(),
						p.getTempAverages());

						flag.setType("Temperature");
						flagList.add(flag);
				
			}
		}
		
//		//Acid Prerinse temperature upper threshold
		for(Phase p : metric.getPhases()) {

			ArrayList<DataPoint> temp = p.getPhaseData();

			if((p.getName().contains("ACID PRERINSE")) && p.getTempAverages() > processInfo.get(processName).get(1).getTempUpper()) {
				
				Flag flag = new Flag(
						"start",
//						temp.get(p.getEffectiveStartIndex()).getTime(), 
						"end",
//						temp.get(p.getEndIndex()).getTime(),
						0,
						p.getName(),
						"Average temperature too high",
						processInfo.get(processName).get(1).getTempLower() + " - " + processInfo.get(processName).get(1).getTempUpper(),
						p.getTempAverages());

						flag.setType("Temperature");
						flagList.add(flag);
				
			}
		}
		
//		// Caustic Cycle chemical strength lower threshold
		for(Phase p : metric.getPhases()) {

			ArrayList<DataPoint> temp = p.getPhaseData();

			if((p.getName().contains("CAUSTIC CYCLE")) && p.getCondAverages() < processInfo.get(processName).get(2).getCondLower()) {
				
				Flag flag = new Flag(
						"start",
//						temp.get(p.getEffectiveStartIndex()).getTime(), 
						"end",
//						temp.get(p.getEndIndex()).getTime(),
						0,
						p.getName(),
						"Chemical strength too low",
						processInfo.get(processName).get(2).getCondLower() + " - " + processInfo.get(processName).get(2).getCondUpper(),
						p.getCondAverages());

						flag.setType("Conductivity");
						flagList.add(flag);
				
			}
		}
		
//		// Caustic Cycle chemical strength upper threshold
		for(Phase p : metric.getPhases()) {
			System.out.println(p.getName() + p.getCondAverages());
			ArrayList<DataPoint> temp = p.getPhaseData();

			if((p.getName().contains("CAUSTIC CYCLE")) && p.getCondAverages() > processInfo.get(processName).get(2).getCondUpper()) {
				
				Flag flag = new Flag(
						"start",
//						temp.get(p.getEffectiveStartIndex()).getTime(), 
						"end",
//						temp.get(p.getEndIndex()).getTime(),
						0,
						p.getName(),
						"Chemical strength too high",
						processInfo.get(processName).get(2).getCondLower() + " - " + processInfo.get(processName).get(2).getCondUpper(),
						p.getCondAverages());

						flag.setType("Conductivity");
						flagList.add(flag);
				
			}
		}
		
//		//Caustic Cycle temperature lower threshold
		for(Phase p : metric.getPhases()) {

			ArrayList<DataPoint> temp = p.getPhaseData();

			if((p.getName().contains("CAUSTIC CYCLE")) && p.getTempAverages() < processInfo.get(processName).get(2).getTempLower()) {
				
				Flag flag = new Flag(
						"start",
//						temp.get(p.getEffectiveStartIndex()).getTime(), 
						"end",
//						temp.get(p.getEndIndex()).getTime(),
						0,
						p.getName(),
						"Average temperature too low",
						processInfo.get(processName).get(2).getTempLower() + " - " + processInfo.get(processName).get(2).getTempUpper(),
						p.getTempAverages());

						flag.setType("Temperature");
						flagList.add(flag);
				
			}
		}
		
//		//Caustic Cycle temperature upper threshold
		for(Phase p : metric.getPhases()) {

			ArrayList<DataPoint> temp = p.getPhaseData();

			if((p.getName().contains("CAUSTIC CYCLE")) && p.getTempAverages() > processInfo.get(processName).get(2).getTempUpper()) {
				
				Flag flag = new Flag(
						"start",
//						temp.get(p.getEffectiveStartIndex()).getTime(), 
						"end",
//						temp.get(p.getEndIndex()).getTime(),
						0,
						p.getName(),
						"Average temperature too high",
						processInfo.get(processName).get(2).getTempLower() + " - " + processInfo.get(processName).get(2).getTempUpper(),
						p.getTempAverages());

						flag.setType("Temperature");
						flagList.add(flag);
				
			}
		}
		
//		//Acid Cycle chemical strength lower threshold
		for(Phase p : metric.getPhases()) {

			ArrayList<DataPoint> temp = p.getPhaseData();

			if((p.getName().contains("ACID CYCLE")) && p.getCondAverages() < processInfo.get(processName).get(3).getCondLower()) {
				
				Flag flag = new Flag(
						"start",
//						temp.get(p.getEffectiveStartIndex()).getTime(), 
						"end",
//						temp.get(p.getEndIndex()).getTime(),
						0,
						p.getName(),
						"Chemical strength too low",
						processInfo.get(processName).get(3).getCondLower() + " - " + processInfo.get(processName).get(3).getCondUpper(),
						p.getCondAverages());

						flag.setType("Conductivity");
						flagList.add(flag);
				
			}
		}
		
//		//Acid Cycle chemical strength upper threshold
		for(Phase p : metric.getPhases()) {

			ArrayList<DataPoint> temp = p.getPhaseData();

			if((p.getName().contains("ACID CYCLE")) && p.getCondAverages() > processInfo.get(processName).get(3).getCondUpper()) {
				
				Flag flag = new Flag(
						"start",
//						temp.get(p.getEffectiveStartIndex()).getTime(), 
						"end",
//						temp.get(p.getEndIndex()).getTime(),
						0,
						p.getName(),
						"Chemical strength too high",
						processInfo.get(processName).get(3).getCondLower() + " - " + processInfo.get(processName).get(3).getCondUpper(),
						p.getCondAverages());

						flag.setType("Conductivity");
						flagList.add(flag);
				
			}
		}
		
//		//Acid Cycle temperature lower threshold
		for(Phase p : metric.getPhases()) {

			ArrayList<DataPoint> temp = p.getPhaseData();

			if((p.getName().contains("ACID CYCLE")) && p.getTempAverages() < processInfo.get(processName).get(3).getTempLower()) {
				
				Flag flag = new Flag(
						"start",
//						temp.get(p.getEffectiveStartIndex()).getTime(), 
						"end",
//						temp.get(p.getEndIndex()).getTime(),
						0,
						p.getName(),
						"Chemical strength too low",
						processInfo.get(processName).get(3).getTempLower() + " - " + processInfo.get(processName).get(3).getTempUpper(),
						p.getCondAverages());

						flag.setType("Conductivity");
						flagList.add(flag);
				
			}
		}
		
//		//Acid Cycle chemical temperature upper threshold
		for(Phase p : metric.getPhases()) {

			ArrayList<DataPoint> temp = p.getPhaseData();

			if((p.getName().contains("ACID CYCLE")) && p.getTempAverages() > processInfo.get(processName).get(3).getTempUpper()) {
				
				Flag flag = new Flag(
						"start",
//						temp.get(p.getEffectiveStartIndex()).getTime(), 
						"end",
//						temp.get(p.getEndIndex()).getTime(),
						0,
						p.getName(),
						"Average temperature too high",
						processInfo.get(processName).get(3).getTempLower() + " - " + processInfo.get(processName).get(3).getTempUpper(),
						p.getTempAverages());

						flag.setType("Conductivity");
						flagList.add(flag);
				
			}
		}

		// Zero conductivity test at end of rinse phase
		for (Phase p : metric.getPhases()) {
			ArrayList<DataPoint> temp = p.getPhaseData();
			
			if (p.getIsRinse() && temp.get(temp.size()-1).getConductivity()!=0) {

				Flag flag = new Flag(temp.get(temp.size()- 1).getTime(), temp.get(temp.size()- 1).getTime(), 5, temp.get(temp.size() - 1).getPhase(),
						"Conductivity Non-Zero at Rinse End", "0.0", temp.get(temp.size()-1).getConductivity());

				flag.setType("Conductivity");
				
				flagList.add(flag);
			}
		}

	}

	

	// Applies phase labels to each data point
	public void applyPhase(ArrayList<DataPoint> data) {

		int dataSize = data.size();
		if (dataSize > 0) {
			for (int i = 0; i < data.size(); i++) {
				DataPoint d = data.get(i);
				d.setPhase(phaseNames.get(d.getZone() - 1));

			}
		}

	}



	public void setPhaseNames(ArrayList<String> phaseNames) {
		this.phaseNames = phaseNames;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

}
