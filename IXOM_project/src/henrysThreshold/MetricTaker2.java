package henrysThreshold;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import Read_Data.*;

public class MetricTaker2 {

	private ArrayList<Phase> phases;
	private int numOfPhases;
	private ArrayList<dataPoint> data;
	private ArrayList<Integer> BoundaryIndices;

	public MetricTaker2(ArrayList<dataPoint> data, ArrayList<String> phaseNames) {

		for (String s : phaseNames) {
			phases.add(new Phase(s));
		}
		this.numOfPhases = phaseNames.size();

		this.data = data;

		assignBoundaryIndicesAndDataToPhases();
		ascribePhaseType();
		
		calculateAverages();
	}

	private void assignBoundaryIndicesAndDataToPhases() {
		int phasecount = 0;
		ArrayList<dataPoint> temp = new ArrayList<dataPoint>(100);
		phases.get(phasecount).setStartIndex(0);
		
		dataPoint prevdatapoint = data.get(0);
		BoundaryIndices.add(0);
		for (int i=0;i<data.size();i++) {
			dataPoint d = data.get(i);
			
			
			
			if(i==data.size()-1){
				phases.get(phasecount).setEndIndex(i);
				phases.get(phasecount).setPhaseData(temp);
			}
			
			if (d.getZone() != prevdatapoint.getZone()) {
				phases.get(phasecount).setPhaseData(temp);
				phases.get(phasecount).setEndIndex(i-1);
				phasecount++;
				phases.get(phasecount).setStartIndex(i);
			}
			else {
				temp.add(d);
			}
			prevdatapoint = d;
		}

	}

	
	private void ascribePhaseType() {
		
		
		for(int i=0;i<phases.size();i++){
			Phase p = phases.get(i);
			if(p.getName().contains("RINSE")){
				p.setIsRinse(true);
			}
			else{
				p.setIsRinse(false);
				setEffectivePeriodDetails(p);
			}
		}
	}

	

	

	private void setEffectivePeriodDetails(Phase p) {
		p.setEffectiveStartIndex(p.getStartIndex());
		ArrayList<dataPoint> temp = new ArrayList<dataPoint>(100);
		for(int i=p.getEffectiveStartIndex();i<=p.getEndIndex();i++){
			temp.add(data.get(i));
		}
		p.setEffPeriodData(temp);
	}

	private void calculateAverages() {
		for (int i=0;i<phases.size();i++){
			Phase p = phases.get(i);
			findAverages(p.getEffPeriodData());
		}

	}

	private double[] findAverages(ArrayList<dataPoint> data) {
		double condtot =0;
		double soiltot =0;
		double temptot =0;
		double[] ret = new double[3];
		
		for(dataPoint d : data){
			temptot+=d.getTemp();
			condtot+=d.getConductivity();
			soiltot+=d.getSoil();
		}
		ret[0]=temptot/data.size();
		ret[1]=condtot/data.size();
		ret[2]=soiltot/data.size();
		return ret;
		
		
	}

	public ArrayList<Integer> getBoundaryIndices() {
		return BoundaryIndices;
	}

	public Phase getPhase(int i) {
		return phases.get(i);
	}

}
