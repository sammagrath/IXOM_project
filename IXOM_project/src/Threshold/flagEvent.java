package Threshold;

public class flagEvent {

	private double startTime;
	private double endTime;
	private String message;
	
	public flagEvent(double start, double end, String message){
		this.startTime=start;
		this.endTime=end;
		this.message=message;
	}
	
	
	public double getStartTime() {
		return startTime;
	}
	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}
	public double getEndTime() {
		return endTime;
	}
	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	
}
