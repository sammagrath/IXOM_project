package henrysThreshold;

import java.util.ArrayList;
import java.util.HashMap;

import Read_Data.dataPoint;

public class Phase {

	private int startIndex;
	private int effectiveStartIndex;
	private int endIndex;
	private String name;
	private double tempAverages;
	private double condAverages;
	private double soilAverages;
	private ArrayList<dataPoint> phaseData;
	private ArrayList<dataPoint> effPeriodData;
	private boolean isRinse;
	
	public Phase(String name){
		this.name=name;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEffectiveStartIndex() {
		return effectiveStartIndex;
	}

	public void setEffectiveStartIndex(int effectiveStartIndex) {
		this.effectiveStartIndex = effectiveStartIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTempAverages() {
		return tempAverages;
	}

	public void setTempAverages(double tempAverages) {
		this.tempAverages = tempAverages;
	}

	public double getCondAverages() {
		return condAverages;
	}

	public void setCondAverages(double condAverages) {
		this.condAverages = condAverages;
	}

	public double getSoilAverages() {
		return soilAverages;
	}

	public void setSoilAverages(double soilAverages) {
		this.soilAverages = soilAverages;
	}

	public ArrayList<dataPoint> getPhaseData() {
		return phaseData;
	}

	public void setPhaseData(ArrayList<dataPoint> phaseData) {
		this.phaseData = phaseData;
	}

	
	public ArrayList<dataPoint> getEffPeriodData() {
		return effPeriodData;
	}

	public void setEffPeriodData(ArrayList<dataPoint> effPeriodData) {
		this.effPeriodData = effPeriodData;
	}


	public boolean getIsRinse() {
		return isRinse;
	}

	public void setIsRinse(boolean isRinse) {
		this.isRinse = isRinse;
	}
	
	
}
