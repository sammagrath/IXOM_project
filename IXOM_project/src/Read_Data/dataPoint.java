package Read_Data;

public class dataPoint {

	private String date;
	private String time;
	private double turbidity;
	private double conductivity;
	private double soil;
	private double temp;
	private int zone;
	
	public dataPoint(String date, String time, double turbidity, double conductivity, double soil, double temp,
			int zone) {
		
		this.date = date;
		this.time = time;
		this.turbidity = turbidity;
		this.conductivity = conductivity;
		this.soil = soil;
		this.temp = temp;
		this.zone = zone;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getTurbidity() {
		return turbidity;
	}
	public void setTurbidity(double turbidity) {
		this.turbidity = turbidity;
	}
	public double getConductivity() {
		return conductivity;
	}
	public void setConductivity(double conductivity) {
		this.conductivity = conductivity;
	}
	public double getSoil() {
		return soil;
	}
	public void setSoil(double soil) {
		this.soil = soil;
	}
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public int getZone() {
		return zone;
	}
	public void setZone(int zone) {
		this.zone = zone;
	}
	public String print(){
	
	return "| " + date + " | " + time + " | " + turbidity + " | " + conductivity + " | " + soil + " | " + temp + " | " +  zone + " | \n"; 
	
	}
	
}
