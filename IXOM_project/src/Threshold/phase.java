package Threshold;

import java.util.ArrayList;

public class phase {
	
	private String name;
	private int sequenceNumber;
	private ArrayList<Flag> possibleFlags = new ArrayList<Flag>();
	private ArrayList<flagEvent> flagEvents = new ArrayList<flagEvent>();
	
	public phase(String name, int sequenceNumber, ArrayList<Flag> possibleFlags){
		this.name=name;
		this.sequenceNumber=sequenceNumber;
		this.possibleFlags=possibleFlags;
	}
	
	public void checkPoint(double time, double soil, double temperature, double conductivity){
		for (int i=0; i<possibleFlags.size();i++){
			Flag f = possibleFlags.get(i);
			
			f.run(time, soil, temperature, conductivity);
			
			if (f.getEndTime()!=-1){
				//we know it has ended
				flagEvents.add(new flagEvent(f.getStartTime(), f.getEndTime(), f.getMessage()));
				f.setEndTime(-1);
				f.setStartTime(-1);
				f.setTriggered(false);
			}
			
		}
	}
	
}


