package samThreshold;

public class Flag {

	private String startTime;
	private String endTime;
	private int zone;
	private String phase;
	private String message;
	
	public Flag() {
		
		startTime = "";
		endTime = "";
		zone = 0;
		phase = "";
		message = "";
	}
	
	public Flag(String startTime, String endTime, int zone, String phase, String message) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.zone = zone;
		this.phase = "Phase: " + phase;
		this.message = "Reason: " + message;
	}

	public String print() {
		return "| " + startTime + " | " + endTime + " | " + phase + " | " + message + " | \n"; 
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
	
	
	
}
