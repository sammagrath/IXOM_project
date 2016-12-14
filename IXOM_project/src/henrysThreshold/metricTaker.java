package henrysThreshold;

import Read_Data.*;
import timeconverter.*;
import java.util.ArrayList;
import java.util.HashMap;

public class metricTaker {
	
	
	/**
	 * 
	 * 
	 * Author: Henry Coulson 
	 * email: h.coulson@outlook.com
	 * 
	 * 
	 */
	

	private ArrayList<dataPoint> data = new ArrayList<dataPoint>();
	private ArrayList<Integer> boundaryIndices = new ArrayList<Integer>();
	private HashMap<Integer,ArrayList<dataPoint>> effectivePeriods = new HashMap<Integer,ArrayList<dataPoint>>();
	private double sampleRate;
	private int countsToEffectivePeriod;
	private HashMap<Integer,Double> tempAverages = new HashMap<Integer,Double>();
	private HashMap<Integer,Double> condAverages = new HashMap<Integer,Double>();
	private HashMap<Integer,Double> soilAverages = new HashMap<Integer,Double>();
	
	
	
	
	
	public metricTaker(ArrayList<dataPoint> data){
		this.data=data;
		findAverageSampleRate();
		minutesToIncrements(7);
		assignBoundaryIndices();
		createEffectivePeriods();
		CalculateAverage();
		
		
	}

	private void minutesToIncrements(int i) {
		countsToEffectivePeriod=(int)((((double)i*60)/86400)/(sampleRate));
//		System.out.println("number of counts until "+i+" minutes: " + countsToEffectivePeriod);
		
	}

	private void CalculateAverage() {
		double temptot=0;
		double condtot=0;
		double soiltot=0;
		int counts=0;
		
		//System.out.println(effectivePeriods.size());
		
		for(Integer key: effectivePeriods.keySet()){
			ArrayList<dataPoint> pointsOfData = effectivePeriods.get(key);
//			System.out.println("pod size: " + pointsOfData.size());
			for (dataPoint dp : pointsOfData){
				temptot+=dp.getTemp();
				condtot+=dp.getConductivity();
				soiltot+=dp.getSoil();
				counts++;
//				System.out.println("condot: ");
			}
			
			tempAverages.put(key, temptot/counts);
//			System.out.println("averageCond: " + condtot/counts);
			condAverages.put(key, condtot/counts);
			soilAverages.put(key, soiltot/counts);
			
		}
		
	}

	private void createEffectivePeriods() {
		int counter = 1;
		
		for (int i=0;i<boundaryIndices.size()-1;i++){
			
			ArrayList<dataPoint> effectiveTime = new ArrayList<dataPoint>();
			
			// need to start at the beginning of the effective period
			for (int j=boundaryIndices.get(i)+countsToEffectivePeriod;j<boundaryIndices.get(i+1);j++){
//				System.out.println("data: " + data.get(j));
				effectiveTime.add(data.get(j));
			}
			//System.out.println(effectiveTime.size());
			effectivePeriods.put(counter, effectiveTime);
			//System.out.println(effectivePeriods.get(counter).size());
			counter++;
		}
		
	}


	private void assignBoundaryIndices() {
		
		boundaryIndices.add(0);
		int counter = 1;
		for(int i=1;i<data.size();i++){
			dataPoint p = data.get(i);
			dataPoint q = data.get(i-1);
			if(p.getZone()!=(q.getZone())){
				boundaryIndices.add(i);
//				System.out.println("boundary time "+counter +": "+ p.getTime()+" at index: "+i);
				counter++;
				
			}
			
		}
		
	}

	
	
	private void findAverageSampleRate(){
		TimeConverter tc = new TimeConverter();
		
		
		sampleRate = (tc.HMSToDec(data.get(data.size()-1).getTime())  -  tc.HMSToDec(data.get(0).getTime()))/data.size();
//		System.out.println("sample rate: " + sampleRate);
		
	}

	public HashMap<Integer, ArrayList<dataPoint>> getEffectivePeriods() {
		return effectivePeriods;
	}

	public void setEffectivePeriods(HashMap<Integer, ArrayList<dataPoint>> effectivePeriods) {
		this.effectivePeriods = effectivePeriods;
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
		return boundaryIndices;
	}

	public void setBoundaryIndices(ArrayList<Integer> boundaryIndices) {
		this.boundaryIndices = boundaryIndices;
	}

	public double getSampleRate() {
		return sampleRate;
	}

	public void setSampleRate(double sampleRate) {
		this.sampleRate = sampleRate;
	}

	public int getCountsToEffectivePeriod() {
		return countsToEffectivePeriod;
	}

	public void setCountsToEffectivePeriod(int countsToEffectivePeriod) {
		this.countsToEffectivePeriod = countsToEffectivePeriod;
	}
	
	
}
