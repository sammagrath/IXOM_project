package Threshold;

import java.util.ArrayList;

public class phase {
	
	private String name;
	private int sequenceNumber;
	private ArrayList<Flag> possibleFlags = new ArrayList<Flag>();
	private ArrayList<Flag> flagEvents = new ArrayList<Flag>();
	
	public phase(String name, int sequenceNumber, ArrayList<Flag> possibleFlags){
		
	}
	
	public void checkPoint(double time, double soil, double temperature, double conductivity){
		for (int i=0; i<possibleFlags.size();i++){
			Flag f = possibleFlags.get(i);
			
			f.run(time, soil, temperature, conductivity);
			
			if (f.getEndTime()!=-1){
				//we know it has ended
				
			}
			
		}
	}
	
}


