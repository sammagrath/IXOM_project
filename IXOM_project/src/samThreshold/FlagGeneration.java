package samThreshold;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

		// Caustic/Acid Prerinse chemical strength lower threshold
		for(Phase p : metric.getPhases()) {
			System.out.println(p.getName() + "- cond averages: " +p.getCondAverages());
			ArrayList<DataPoint> temp = p.getPhaseData();
			if(p.isPreRinse()) System.out.println("True dat");
			if((p.isPreRinse()) && p.getCondAverages() < 1 /*Dummy threshold used to trigger flag- processInfo.get(processName).get(0).getCondLower()*/) {
				
				System.out.println("true");
				Flag flag = new Flag(
						"start",
//						temp.get(p.getEffectiveStartIndex()).getTime(), 
//						"endtime",
						temp.get(temp.size()-1).getTime(),
						0,
						p.getName(),
						"Chemical strength too low",
						processInfo.get(processName).get(0).getCondLower() + " - " + processInfo.get(processName).get(0).getCondUpper(),
						p.getCondAverages());

						flag.setType("Conductivity");
						flagList.add(flag);
				
			}
		}
		
		
//		if (condAverages.get(1) < processInfo.get(processName).get(0).getCondLower()) {
//
//			Flag flag = new Flag(
//					data.get(metric.getIndexOfEffectivePeriod(processInfo.get(processName).get(0).getPhase()))
//							.getTime(),
//					data.get(boundaries.get(1)).getTime(), 1, data.get(boundaries.get(1)).getPhase(),
//					"Chemical strength too low", "0.5 - 0.8", condAverages.get(1));
//
//			flag.setType("Conductivity");
//
//			flagList.add(flag);
//		}
//
//		// Caustic/Acid Prerinse chemical strength upper threshold
//		System.out.println("actual cond av: " + condAverages.get(1));
//		if (condAverages.get(1) > processInfo.get(processName).get(0).getCondUpper()) {
//			// System.out.println("start: " +
//			// data.get(metric.getIndexOfEffectivePeriod(processInfo.get(processName).get(0).getPhase())).getTime());
//			// System.out.println("finish: " +
//			// data.get(boundaries.get(1)).getTime());
//			System.out.println("phase: " + data.get(boundaries.get(1)).getPhase());
//			System.out.println("condAverages: " + condAverages.get(1));
//			Flag flag = new Flag(
//					data.get(metric.getIndexOfEffectivePeriod(processInfo.get(processName).get(0).getPhase()))
//							.getTime(),
//					data.get(boundaries.get(1)).getTime(), 1, data.get(boundaries.get(1)).getPhase(),
//					"Chemical strength too high", "0.5 - 0.8", condAverages.get(1));
//
//			flag.setType("Conductivity");
//
//			flagList.add(flag);
//		}
//
//		// Caustic Cycle chemical strength lower threshold
//		if (condAverages.get(2) < processInfo.get(processName).get(2).getCondLower()) {
//
//			Flag flag = new Flag(
//					data.get(metric.getIndexOfEffectivePeriod(processInfo.get(processName).get(2).getPhase()))
//							.getTime(),
//					data.get(boundaries.get(2)).getTime(), 2, data.get(boundaries.get(2)).getPhase(),
//					"Chemical strength too low", "1.0 - 1.2", condAverages.get(2));
//
//			flag.setType("Conductivity");
//
//			flagList.add(flag);
//		}
//
//		// Caustic Cycle chemical strength upper threshold
//		if (condAverages.get(2) > processInfo.get(processName).get(2).getCondUpper()) {
//
//			Flag flag = new Flag(
//					data.get(metric.getIndexOfEffectivePeriod(processInfo.get(processName).get(2).getPhase()))
//							.getTime(),
//					data.get(boundaries.get(2)).getTime(), 2, data.get(boundaries.get(2)).getPhase(),
//					"Chemical strength too high", "1.0 - 1.2", condAverages.get(2));
//
//			flag.setType("Conductivity");
//
//			flagList.add(flag);
//		}
//
//		// Caustic Cycle temperature lower threshold
//		if (tempAverages.get(2) < processInfo.get(processName).get(2).getTempLower()) {
//
//			Flag flag = new Flag(
//					data.get(metric.getIndexOfEffectivePeriod(processInfo.get(processName).get(2).getPhase()))
//							.getTime(),
//					data.get(boundaries.get(2)).getTime(), 2, data.get(boundaries.get(2)).getPhase(),
//					"Average Temperature too low", "70.0 - 85.0", tempAverages.get(2));
//
//			flag.setType("Temperature");
//
//			flagList.add(flag);
//		}
//
//		// Caustic Cycle temperature upper threshold
//		if (tempAverages.get(2) > processInfo.get(processName).get(2).getTempUpper()) {
//
//			Flag flag = new Flag(
//					data.get(metric.getIndexOfEffectivePeriod(processInfo.get(processName).get(2).getPhase()))
//							.getTime(),
//					data.get(boundaries.get(2)).getTime(), 2, data.get(boundaries.get(2)).getPhase(),
//					"Average Temperature too high", "70.0 - 85.0", tempAverages.get(2));
//
//			flag.setType("Temperature");
//
//			flagList.add(flag);
//		}
//

		
//
//		// Acid Cycle chemical strength lower threshold
//		if (condAverages.get(4) < processInfo.get(processName).get(3).getCondLower()) {
//
//			Flag flag = new Flag(
//					data.get(metric.getIndexOfEffectivePeriod(processInfo.get(processName).get(3).getPhase()))
//							.getTime(),
//					data.get(boundaries.get(4)).getTime(), 4, data.get(boundaries.get(4)).getPhase(),
//					"Chemical strength too low", "0.8 - 1.0", condAverages.get(4));
//
//			flag.setType("Conductivity");
//
//			flagList.add(flag);
//		}
//
//		// Acid cycle chemical strength upper threshold
//		if (condAverages.get(4) > processInfo.get(processName).get(3).getCondUpper()) {
//
//			Flag flag = new Flag(
//					data.get(metric.getIndexOfEffectivePeriod(processInfo.get(processName).get(3).getPhase()))
//							.getTime(),
//					data.get(boundaries.get(4)).getTime(), 4, data.get(boundaries.get(4)).getPhase(),
//					"Chemical strength too high", "0.8 - 1.0", condAverages.get(4));
//
//			flag.setType("Conductivity");
//
//			flagList.add(flag);
//		}
//
//		// Acid Cycle temperature lower threshold
//		if (tempAverages.get(4) < processInfo.get(processName).get(3).getTempLower()) {
//
//			Flag flag = new Flag(
//					data.get(metric.getIndexOfEffectivePeriod(processInfo.get(processName).get(3).getPhase()))
//							.getTime(),
//					data.get(boundaries.get(4)).getTime(), 4, data.get(boundaries.get(4)).getPhase(),
//					"Average Temperature too low", "60.0 - 70.0", tempAverages.get(4));
//
//			flag.setType("Temperature");
//
//			flagList.add(flag);
//		}
//
//		// Acid Cycle temperature upper threshold
//		if (tempAverages.get(4) > processInfo.get(processName).get(3).getTempUpper()) {
//
//			Flag flag = new Flag(
//					data.get(metric.getIndexOfEffectivePeriod(processInfo.get(processName).get(3).getPhase()))
//							.getTime(),
//					data.get(boundaries.get(4)).getTime(), 4, data.get(boundaries.get(4)).getPhase(),
//					"Average Temperature too high", "60.0 - 70.0", tempAverages.get(4));
//
//			flag.setType("Temperature");
//
//			flagList.add(flag);
//		}

		// Zero conductivity test at end of final rinse phase
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

	// local main for testing flag methods

	// local main for testing flag methods

	public static void main(String[] args) throws FileNotFoundException {

		// User must change directory to local in order to test
		input = new File("/home/magratsam/git/cleanfile5.csv");
		CSV2Array c = new CSV2Array();

		data = c.populateData(input, data);

		// FlagGeneration f = new FlagGeneration(data);
		// f.applyPhase(data);
		// f.editBoundaries();
		// f.condThresholds(flagList);
		// f.endRinseCond(flagList);
		// f.tempThresholds(flagList);
		// f.thresholds(flagList);

		// Console print of flags
		// for (Flag flag : flagList) {
		// System.out.println();
		// System.out.println(flag.print());
		// }
		//
		// // console print of data
		// for (DataPoint d : data) {
		// System.out.println(d.getPhase());
		// }
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
