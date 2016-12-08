package samThreshold;
import java.util.ArrayList;

public class FlagMethods {

	public FlagMethods() {
		
	}
	
	public ArrayList<Double> runningAverageConductivity(ArrayList<DataPoint> dataList, int phase) {
		
		Double average = 0.0;
		Double sum = 0.0;
		ArrayList<Double> averageList = new ArrayList<Double>();
		ArrayList<Double> runningAverageList = new ArrayList<Double>();
		
		for (int i = 0; i < dataList.size(); i++) {
			if (dataList.get(i).getStep() == phase) {
				averageList.add(dataList.get(i).getConductivity());
				sum += dataList.get(i).getConductivity();
			
				average = sum/averageList.size();
				runningAverageList.add(average);
//				System.out.println("Higher/Lower: 0.5/0.8 --- Running average: " + average);
			}
		}
		
		return runningAverageList;
		
	}
}
