package samThreshold;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<String> timeData = new ArrayList<String>();
		ArrayList<Double> conductivityData = new ArrayList<Double>();
		ArrayList<Double> soilData = new ArrayList<Double>();
		ArrayList<Double> tempData = new ArrayList<Double>();
		ArrayList<Integer> stepData = new ArrayList<Integer>();
		ArrayList<DataPoint> dataList = new ArrayList<DataPoint>();
		
		ArrayList<Double> conductivityAverage = new ArrayList<Double>();
		
		DataPoint test = new DataPoint();
		test.populateData(timeData, conductivityData, soilData, tempData, stepData);
		
		for(int i = 0; i < timeData.size(); i++) {
			
			DataPoint data = new DataPoint(timeData.get(i), 
					"phase", 
					conductivityData.get(i),
					soilData.get(i),
					tempData.get(i),
					stepData.get(i));
			dataList.add(data);
		}
		
		
//		for (DataPoint data: dataList) {
//			System.out.println("Time: " + data.getTimeStamp() + ", Cond: " + data.getConductivity()
//				+ ", Soil: " + data.getTurbidity() + ", Temp: " + data.getTemp() + ", Step: " + data.getStep());
//		}

//		ArrayList<Double> conductivityAverage;
//		conductivityAverage = test.runningAverage(conductivityData);
//		System.out.println(conductivityAverage.size());
//		for (Double i : conductivityAverage) {
//			System.out.println(i);
//		}
	
		FlagMethods flagmethod = new FlagMethods();
		
		conductivityAverage = flagmethod.runningAverageConductivity(dataList, 5);
		
			
		for (int i = 0; i < conductivityAverage.size(); i++) {
			System.out.println(i +". Time: " + dataList.get(i).getTimeStamp() + ", Average: " + conductivityAverage.get(i));
		}
		
		
		
		
	}

}
