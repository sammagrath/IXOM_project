package Threshold;

public abstract class Flag{

	private double startTime=-1;
	private double endTime=-1;
	private String message;
	private boolean triggered=false;
	private double[] threshold;
	
	
	
	
	public void run(double time, double soil, double temperature, double conductivity){
		
		boolean Cond = this.isConditionMet(time, soil, temperature,  conductivity);
		
		if (Cond && triggered){
			this.endTime=time;
			
		}
		else if ( !(Cond) && !triggered){
			this.startTime = time;
			triggered = true;
		}

		
		
	}
	
	abstract boolean isConditionMet(double time, double soil, double temperature, double conductivity);
	
	
	
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



	public double[] getThreshold() {
		return threshold;
	}



	public void setThreshold(double[] threshold) {
		this.threshold = threshold;
	}



	
	
	
}
