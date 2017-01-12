package decayAnalysis;

import java.util.ArrayList;

import Read_Data.dataPoint;
import timeconverter.TimeConverter;

public class Analyser {
	
	public ArrayList<Coordinate> findSteepestCond(ArrayList<dataPoint> dataPoints){
		
		ArrayList<Coordinate> steepest = new ArrayList<>();
		ArrayList<dataPoint> condList = new ArrayList<>();
		ArrayList<ArrayList> LOL = new ArrayList<>();
		double gradient = 0;
		
		LOL = findAllDescents(dataPoints);
		
		int oldSize, newSize;
		
		while(true){
			oldSize = LOL.size();
			
			LOL = mergeAdjacentDescents(LOL);
			
			newSize = LOL.size();
			
			System.out.println(oldSize + " and " + newSize);
			
			if(oldSize == newSize){
				break;
			}
		}
		
		for(ArrayList a: LOL){
			if(pointToPointGradient(a) >= gradient){
				condList = a;
				gradient = pointToPointGradient(a);
			}
		}
		
		for(dataPoint d: condList){
			Coordinate c = new Coordinate(d.getTime(), d.getConductivity());
			
			System.out.println("("+ d.getTime() +", "+ d.getConductivity() +")");
			
			steepest.add(c);
		}
		
		System.out.println(gradient);
		return steepest;
		
	}
	
	//calculate the rate of decrease of conductivity for an ArrayList of dataPoints
	public double pointToPointGradient(ArrayList<dataPoint> dataPoints){
		TimeConverter tc = new TimeConverter();
		
		dataPoint x1y1 = dataPoints.get(dataPoints.size() - 1);
		dataPoint x2y2 = dataPoints.get(0);
		
		double x1 = tc.HMSToDec(x1y1.getTime());
		double x2 = tc.HMSToDec(x2y2.getTime());
		double y1 = x1y1.getConductivity();
		double y2 = x2y2.getConductivity();
		
		//double gradient = (y2 - y1)/((x1 - x2)*86400);
		double gradient = (y2 - y1);
		
		return gradient;
	}
	
	
	public ArrayList<ArrayList> mergeAdjacentDescents(ArrayList<ArrayList> LOL){
		
		ArrayList<ArrayList> newLOL = new ArrayList<>();
		ArrayList<dataPoint> adjacent, current;
		
		for(int i = 0; i< LOL.size() - 1; i++){
			
			current = LOL.get(i);
			adjacent = LOL.get(i+1);
			
			if(current.size() > 1 && adjacent.size() > 1){
				current = mergeArrays(current, adjacent);
				newLOL.add(current);
				i++;
			} else {
				newLOL.add(current);
				if(i == LOL.size() - 2){
					newLOL.add(adjacent);
				}
			}
		}
		
		return newLOL;
	}
	
	//merge two ArrayLists of dataPoints together
	public ArrayList<dataPoint> mergeArrays(ArrayList<dataPoint> a, ArrayList<dataPoint> b){
		
		for(dataPoint d: b){
			a.add(d);
		}
		
		return a;
	}
	
	//Go through a list of dataPoints and make ArrayList of ArrayLists of points with decreasing conductivity values
	public ArrayList<ArrayList> findAllDescents(ArrayList<dataPoint> dataPoints){
		ArrayList<dataPoint> tempList = new ArrayList<>();
		ArrayList<ArrayList> LOL = new ArrayList<>();
		
		dataPoint previous = new dataPoint(null, null, 0, Double.MAX_VALUE, 0, 0, 0);
		
		for(dataPoint d: dataPoints){
			if(d.getConductivity() <= previous.getConductivity()){
				tempList.add(d);
				previous = d;
			} else {
				LOL.add(tempList);
				tempList = new ArrayList<>();
				tempList.add(d);
				previous = d;
			}
		}
		
		return LOL;
	}
	
}
