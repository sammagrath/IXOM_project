package samThreshold;

public class Flag {

	private Double startTime;
	private Double endTime;
	private char type;
	private String phase;
	private String message;
	
	public Flag() {
		
		startTime = 0.0;
		endTime = 0.0;
		phase = "";
		message = "";
	}

	public Double getStartTime() {
		return startTime;
	}

	public void setStartTime(Double startTime) {
		this.startTime = startTime;
	}

	public Double getEndTime() {
		return endTime;
	}

	public void setEndTime(Double endTime) {
		this.endTime = endTime;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
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
