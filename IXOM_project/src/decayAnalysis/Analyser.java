package decayAnalysis;

import java.util.ArrayList;

import Read_Data.dataPoint;
import timeconverter.TimeConverter;

public class Analyser {
	
	public ArrayList<Coordinate> findSteepestTurb(ArrayList<dataPoint> dataPoints){
		
		ArrayList<Coordinate> steepest = new ArrayList<>();
		ArrayList<dataPoint> tempList = new ArrayList<>();
		ArrayList<ArrayList> LOL = new ArrayList<>();
		
		dataPoint previous = new dataPoint(null, null, Double.MAX_VALUE, 0, 0, 0, 0);
		double gradient = 0;
		
		for(dataPoint d: dataPoints){
			if(d.getTurbidity() <= previous.getTurbidity()){
				tempList.add(d);
				previous = d;
			} else {
				LOL.add(tempList);
				tempList = new ArrayList<>();
				tempList.add(d);
				previous = d;
			}
		}
		
		for(ArrayList a: LOL){
			if(pointToPointGradient(a) >= gradient){
				tempList = a;
				gradient = pointToPointGradient(a);
			}
		}
		
		for(dataPoint d: tempList){
			Coordinate c = new Coordinate(d.getTime(), d.getTurbidity());
			
			System.out.println("("+ d.getTime() +", "+ d.getTurbidity() +")");
			
			steepest.add(c);
		}
		
		System.out.println(gradient);
		return steepest;
		
	}
	
	public double pointToPointGradient(ArrayList<dataPoint> coordinates){
		TimeConverter tc = new TimeConverter();
		
		dataPoint x1y1 = coordinates.get(coordinates.size() - 1);
		dataPoint x2y2 = coordinates.get(0);
		
		double x1 = tc.HMSToDec(x1y1.getTime());
		double x2 = tc.HMSToDec(x2y2.getTime());
		double y1 = x1y1.getTurbidity();
		double y2 = x2y2.getTurbidity();
		
		double gradient = (y2 - y1)/((x1 - x2)*86400);
		
		return gradient;
	}
	
}
