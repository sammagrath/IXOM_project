package henrysThreshold;

import java.util.ArrayList;
import java.util.HashMap;

import Read_Data.dataPoint;

public class MetricTaker2 {
	
	private HashMap<Integer, Double> tempAverages = new HashMap<Integer, Double>();
	private HashMap<Integer, Double> condAverages = new HashMap<Integer, Double>();
	private HashMap<Integer, Double> soilAverages = new HashMap<Integer, Double>();

	private ArrayList<String> phaseNames;
	private ArrayList<dataPoint> data;
	private HashMap<Integer, String> zone2phase;
	private ArrayList<Integer> relevantzones;
	private HashMap<Integer, ArrayList<dataPoint>> phaseData;
	private ArrayList<Integer> BoundaryIndices;

	public MetricTaker2(ArrayList<dataPoint> data, ArrayList<String> phaseNames) {

		this.phaseNames = phaseNames;
		this.data = data;
		
		assignBoundaryIndices();
		mapZoneToPhaseName();
		findRelevantPhases();
		assignDataToPhases();
		getEffectivePeriodStarts();
		calculateAverages();
	}

	private void assignBoundaryIndices() {
		dataPoint prevdatapoint = data.get(0);
		BoundaryIndices.add(0);
		for(dataPoint d : data){
			if(d.getZone()!=prevdatapoint.getZone()){
				BoundaryIndices.add(prevdatapoint.getZone());
			}
			prevdatapoint = d;
		}
	}

	private void mapZoneToPhaseName() {
		for (int i = 0; i < phaseNames.size(); i++) {
			zone2phase.put(i + 1, phaseNames.get(i));
		}

	}

	private void findRelevantPhases() {
		int counter = 1;
		for (String z : phaseNames) {
			if (z.contains("RINSE")) {
				counter++;
				continue;
			} else {
				relevantzones.add(counter);
			}
		}

	}

	private void assignDataToPhases() {

		ArrayList<dataPoint> temp = new ArrayList<dataPoint>();
		int prevzoneNumber = 1;
		boolean zoneSkipper = false;
		for (int i=0;i<data.size();i++) {
			dataPoint d = data.get(i);
			int currentzoneNumber = d.getZone();
			if (prevzoneNumber == currentzoneNumber) {
				zoneSkipper = addIfValid(d, temp);
				if (!zoneSkipper) {
					// skip to end of line
					while(prevzoneNumber == currentzoneNumber && i+1<data.size()){
						i++;
						d = data.get(i);
						prevzoneNumber=currentzoneNumber;
						currentzoneNumber=d.getZone();
					}
				}
			} else {
				// have reached the end of the line, clear the array, after
				// adding to phaseData
				ArrayList<dataPoint> tempclone = (ArrayList<dataPoint>) temp.clone();
				phaseData.put(prevzoneNumber, tempclone);
				temp.clear();
			}
			prevzoneNumber = currentzoneNumber;

		}

	}

	private boolean addIfValid(dataPoint d, ArrayList<dataPoint> temp) {
		boolean ret = false;
		for (int z : relevantzones) {
			if (z == d.getZone()) {
				temp.add(d);
				ret = true;
			}
		}
		return ret;

	}

	private void getEffectivePeriodStarts() {
		for (int i : phaseData.keySet()){
			ArrayList<dataPoint> temp = phaseData.get(i);
			
		}

	}

	private void calculateAverages() {
		// TODO Auto-generated method stub

	}

	public HashMap<Integer, Double> getTempAverages() {
		return tempAverages;
	}

	public void setTempAverages(HashMap<Integer, Double> tempAverages) {
		this.tempAverages = tempAverages;
	}

	public HashMap<Integer, Double> getCondAverages() {
		return condAverages;
	}

	public void setCondAverages(HashMap<Integer, Double> condAverages) {
		this.condAverages = condAverages;
	}

	public HashMap<Integer, Double> getSoilAverages() {
		return soilAverages;
	}

	public void setSoilAverages(HashMap<Integer, Double> soilAverages) {
		this.soilAverages = soilAverages;
	}

	public ArrayList<Integer> getBoundaryIndices() {
		return BoundaryIndices;
	}

	
	
}
