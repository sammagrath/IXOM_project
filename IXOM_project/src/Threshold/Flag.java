package Threshold;

public abstract class Flag {

	private double startTime;
	private double endTime;
	private String message;
	private boolean triggered=false;
	private double threshold;
	
	public Flag(double threshold){
		this.threshold=threshold;
		
	}
	
	
	
	public void run(double val){
		
		if(this.isConditionMet(val) && !triggered){
			// all is well
			
		}
		else if (this.isConditionMet(val) && triggered){
			// assign the end time stamp
			
		}
		else if ( !(this.isConditionMet(val)) && !triggered){
			// starting
		}
		else if (!(this.isConditionMet(val)) && triggered) {
			// continue, but is triggered
		}
		
		
	}
	
	private boolean isConditionMet(double val){return true;}
	
	
	
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



	public boolean isTriggered() {
		return triggered;
	}



	public void setTriggered(boolean triggered) {
		this.triggered = triggered;
	}



	public double getThreshold() {
		return threshold;
	}



	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}



	
	
	
}
