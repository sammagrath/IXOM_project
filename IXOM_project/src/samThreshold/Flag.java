package samThreshold;

import java.util.ArrayList;

public class Flag {
	
	private String date;
	private String startTime;
	private String endTime;
	private int zone;
	private String phase;
	private String message;
	private String target;
	private double actual;
	private double lowerThreshold;
	private double upperThreshold;
	


	public Flag() {
		
		date = "";
		startTime = "";
		endTime = "";
		zone = 0;
		phase = "";
		message = "";
		target = "";
		actual = 0.0;
		lowerThreshold = 0;
		upperThreshold = 0;
	}
	
	public Flag(String startTime, String endTime, int zone, String phase, String message,String target, double actual) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.zone = zone;
		this.phase = phase;
		this.message = message;
		this.target = target;
		this.actual = Math.round(actual*100.0)/100.0;
	}

	public String print() {
		return "| " + startTime + " | " + endTime + " | " + phase + " | " + message + " | " + target + " | " + actual + " | \n"; 
	}
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public int getZone() {
		return zone;
	}

	public void setZone(int zone) {
		this.zone = zone;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public double getActual() {
		return actual;
	}

	public void setActual(double actual) {
		this.actual = actual;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getLowerThreshold() {
		return lowerThreshold;
	}

	public void setLowerThreshold(double lowerThreshold) {
		this.lowerThreshold = lowerThreshold;
	}

	public double getUpperThreshold() {
		return upperThreshold;
	}

	public void setUpperThreshold(double upperThreshold) {
		this.upperThreshold = upperThreshold;
	}
	
}
