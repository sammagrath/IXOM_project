package samThreshold;

import java.util.ArrayList;

public class Flag {

	private String startTime;
	private String endTime;
	private int zone;
	private String phase;
	private String message;
	private double target;
	private double actual;
	
	public Flag() {
		
		startTime = "";
		endTime = "";
		zone = 0;
		phase = "";
		message = "";
		target = 0.0;
		actual = 0.0;
	}
	
	public Flag(String startTime, String endTime, int zone, String phase, String message, double target, double actual) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.zone = zone;
		this.phase = phase;
		this.message = message;
		this.target = target;
		this.actual = actual;
	}

	public String print() {
		return "| " + startTime + " | " + endTime + " | " + phase + " | " + message + " | " + target + " | " + actual + " |\n"; 
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
	
	public double getTarget() {
		return target;
	}

	public void setTarget(double target) {
		this.target = target;
	}

	public double getActual() {
		return actual;
	}

	public void setActual(double actual) {
		this.actual = actual;
	}
	
}
