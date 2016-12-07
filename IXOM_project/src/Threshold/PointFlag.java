package Threshold;

public class PointFlag extends Flag{
	
	// in all instances, the message2 will be used for the higher boundary being breached
	private String message1Stored;
	private String message2Stored;
	

	public PointFlag(double[] threshold, String type, String message1Stored, String message2Stored) {
		setThreshold(threshold);
		setType(type);
		this.message1Stored=message1Stored;
		this.message2Stored=message2Stored;
		
	}


	@Override
	boolean isConditionMet(double time, double soil, double temp, double cond) {
		if (getType().equals("soil")){
			if(soil<getThreshold()[1] && soil>getThreshold()[0] ){
				return true;
			}else if (soil>getThreshold()[1]){
				setMessage2(message2Stored);
				return false;
			}else if (soil<getThreshold()[0]){
				setMessage1(message1Stored);
				return false;
			}
		}else if(getType().equals("temp")){
			if(temp<getThreshold()[1] && temp>getThreshold()[0] ){
				return true;
			}else if (temp>getThreshold()[1]){
				setMessage2(message2Stored);
				return false;
			}else if (temp<getThreshold()[0]){
				setMessage1(message1Stored);
				return false;
			}
		}else if(getType().equals("cond")){
			if(cond<getThreshold()[1] && cond>getThreshold()[0] ){
				return true;
			}else if (cond>getThreshold()[1]){
				setMessage2(message2Stored);
				return false;
			}else if (cond<getThreshold()[0]){
				setMessage1(message1Stored);
				return false;
			}
		}
		return false;
	}
}
