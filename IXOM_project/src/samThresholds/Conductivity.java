package samThresholds;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Conductivity {
	
	public Conductivity() {
		
		
				
	}
	
	public void populate(ArrayList conductivity) {
		
		File file = new File("sample");
		
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextDouble()) {
				Double dataPoint = sc.nextDouble();
				conductivity.add(dataPoint);
				}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public ArrayList<Double> runningAverage(ArrayList<Double> conductivityList) {
		
		Double average = 0.0;
		Double sum = 0.0;
		ArrayList<Double> averageList = new ArrayList<Double>();
		ArrayList<Double> runningAverageList = new ArrayList<Double>();
		
		for (Double i : conductivityList) {
			
			averageList.add(i);
			sum += i;
			
			average = sum/averageList.size();
			runningAverageList.add(average);
//			System.out.println("Higher/Lower: 0.5/0.8 --- Running average: " + average);
		}
		
		return runningAverageList;
		
	}

}
