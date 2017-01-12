package decayAnalysis;

import java.util.ArrayList;

import Read_Data.dataPoint;
//import timeconverter.TimeConverter;

public class Analyser {
	
	public ArrayList<Coordinate> findSteepestCond(ArrayList<dataPoint> dataPoints){
		
		ArrayList<Coordinate> steepest = new ArrayList<>();
		ArrayList<dataPoint> condList = new ArrayList<>();
		ArrayList<ArrayList<dataPoint>> LOL = new ArrayList<>();
		double gradient = 0;
		
		LOL = findAllDescents(dataPoints);
		
		int oldSize, newSize;
		
		//repeat merging as long as there are two adjacent decreasing sets of dataPoints
		while(true){
			oldSize = LOL.size();
			
			LOL = mergeAdjacentDescents(LOL);
			
			newSize = LOL.size();
			
			System.out.println(oldSize + " and " + newSize);
			
			if(oldSize == newSize){
				break;
			}
		}
		
		//find the arraylist of dataPoints with the largest decrease in conductivity
		for(ArrayList<dataPoint> a: LOL){
			if(a.size() > 1){
				if(pointToPointGradient(a) >= gradient){
					condList = a;
					gradient = pointToPointGradient(a);
				}
			}
		}
		
		condList = completeZone(condList, dataPoints);
		
		//for the arraylist of dataPoints with the largest decrease in conductivity, construct coordinate objects for each dataPoint and return an arraylist of those coordinates
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
		//TimeConverter tc = new TimeConverter();
		
		dataPoint x1y1 = dataPoints.get(dataPoints.size() - 1);
		dataPoint x2y2 = dataPoints.get(0);
		
		//double x1 = tc.HMSToDec(x1y1.getTime());
		//double x2 = tc.HMSToDec(x2y2.getTime());
		double y1 = x1y1.getConductivity();
		double y2 = x2y2.getConductivity();
		
		//double gradient = (y2 - y1)/((x1 - x2)*86400);
		double gradient = (y2 - y1);
		
		return gradient;
	}
	
	
	public ArrayList<ArrayList<dataPoint>> mergeAdjacentDescents(ArrayList<ArrayList<dataPoint>> LOL){
		
		ArrayList<ArrayList<dataPoint>> newLOL = new ArrayList<>();
		ArrayList<dataPoint> adjacent, current;
		
		for(int i = 0; i < LOL.size() - 1; i++){
			
			current = LOL.get(i);
			adjacent = LOL.get(i+1);
			
			if((current.size() > 1 && adjacent.size() > 1) && (current.get(current.size() - 1).getZone() == adjacent.get(0).getZone())){
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
	public ArrayList<ArrayList<dataPoint>> findAllDescents(ArrayList<dataPoint> dataPoints){
		ArrayList<dataPoint> tempList = new ArrayList<>();
		ArrayList<ArrayList<dataPoint>> LOL = new ArrayList<>();
		
		dataPoint previous = new dataPoint(null, null, 0, Double.MAX_VALUE, 0, 0, 0);
		
		for(dataPoint d: dataPoints){
			if(!((d.getConductivity() <= previous.getConductivity()) && (previous.getZone() == d.getZone()))){
				LOL.add(tempList);
				tempList = new ArrayList<>();
			}
			tempList.add(d);
			previous = d;
		}
		LOL.add(tempList);
		
		return LOL;
	}
	
	//tag on the rest of the points in the same zone as the steepest decrease arraylist
	public ArrayList<dataPoint> completeZone(ArrayList<dataPoint> steepest, ArrayList<dataPoint> dataPoints){
		
		dataPoint oldEnd = steepest.get(steepest.size() - 1);
		boolean complete = false;
		int zone = oldEnd.getZone();
		
		for(dataPoint d: dataPoints){
			if(complete == true){
				if(d.getZone() == zone){
					steepest.add(d);
				} else {
					complete = false;
				}
			}
			
			if(d == oldEnd){
				complete = true;
			}
		}
		
		return steepest;
	}
	
}
