package samThresholds;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataPoint {
	
	private String timeStamp;
	private String phase;
	private Double conductivity;
	private Double turbidity;
	private Double temp;
	private int step;
	
	public DataPoint() {
		
	}
	
	public DataPoint(String timeStamp, String phase, Double conductivity, Double turbidity, Double temp, int step) {
		
		this.timeStamp = timeStamp;
		this.phase = phase;
		this.conductivity = conductivity;
		this.turbidity = turbidity;
		this.temp = temp;
		this.step = step;
	}


	public String getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}


	public String getPhase() {
		return phase;
	}


	public void setPhase(String phase) {
		this.phase = phase;
	}


	public Double getConductivity() {
		return conductivity;
	}


	public void setConductivity(Double conductivity) {
		this.conductivity = conductivity;
	}


	public Double getTurbidity() {
		return turbidity;
	}


	public void setTurbidity(Double turbidity) {
		this.turbidity = turbidity;
	}


	public Double getTemp() {
		return temp;
	}


	public void setTemp(Double temp) {
		this.temp = temp;
	}
	
	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}
	
	
	//to be worked on
	public void applyPhase(ArrayList<DataPoint> data) {
		
		int i = 1;
		
		for (DataPoint d: data)
			
			if (d.getStep() == 0 && i == 1) {
				
				d.setPhase("Pre-Flush");
							
			}
			
			else if (d.getStep() == 1 && i == 2) {
				d.setPhase("Caustic Recirculation");
			}
			
			else if (d.getStep() == 0 && i == 3) {
				d.setPhase("Intermediate Wash");
			}
			
			else if (d.getStep() == 1 && i == 4) {
				d.setPhase("Acid Recirculation");
			}
			
			else if (d.getStep() == 0 && i == 3) {
				d.setPhase("Final Rinse");
			}
	}
	
	public void populateData(ArrayList<String> time, ArrayList<Double> cond, ArrayList<Double> soil, ArrayList<Double> temp, ArrayList<Integer> step ) {
		
		
		
		File file = new File("timeStamp");
		File file2 = new File("conductivity");
		File file3 = new File("turbidity");
		File file4 = new File("temp");
		File file5 = new File("step");
		
		try {
			Scanner sc = new Scanner(file);
			
			while (sc.hasNextLine()) {
				String dataPoint = sc.nextLine();
				time.add(dataPoint);
				}
			
			sc = new Scanner(file2);
			
			while (sc.hasNextDouble()) {
				Double dataPoint = sc.nextDouble();
				cond.add(dataPoint);
				}
			
			sc = new Scanner(file3);
			
			while (sc.hasNextDouble()) {
				Double dataPoint = sc.nextDouble();
				soil.add(dataPoint);
				}
			
			sc = new Scanner(file4);
			
			while (sc.hasNextDouble()) {
				Double dataPoint = sc.nextDouble();
				temp.add(dataPoint);
				}
			
			sc = new Scanner(file5);
			
			while (sc.hasNextInt()) {
				Integer dataPoint = sc.nextInt();
				step.add(dataPoint);
				}
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	
}

