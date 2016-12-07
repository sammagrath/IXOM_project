package Threshold;

public abstract class Flag{

	private double startTime=-1;
	private double endTime=-1;
	private String message1;
	private String message2;
	private boolean triggered=false;
	private double[] threshold;
	private String type;
	
	
	
	
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



	public String getMessage1() {
		return message1;
	}



	public void setMessage1(String message) {
		this.message1 = message;
	}



	public String getMessage2() {
		return message2;
	}

	public void setMessage2(String message2) {
		this.message2 = message2;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	
	
	
}
