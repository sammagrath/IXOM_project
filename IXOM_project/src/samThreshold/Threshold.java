package samThreshold;

import java.util.ArrayList;
import java.util.HashMap;

public class Threshold {
	
	private String product;
	private String process;
	
	private double condPreflushUpper;
	private double condPreflushLower;
	private double tempPreflushUpper;
	private double tempPreflushLower;
	
	private double condCausticUpper;
	private double condCausticLower;
	private double tempCausticUpper;
	private double tempCausticLower;
	
	private double condAcidUpper;
	private double condAcidLower;
	private double tempAcidUpper;
	private double tempAcidLower;
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public double getCondPreflushUpper() {
		return condPreflushUpper;
	}
	public void setCondPreflushUpper(double condPreflushUpper) {
		this.condPreflushUpper = condPreflushUpper;
	}
	public double getCondPreflushLower() {
		return condPreflushLower;
	}
	public void setCondPreflushLower(double condPreflushLower) {
		this.condPreflushLower = condPreflushLower;
	}
	public double getTempPreflushUpper() {
		return tempPreflushUpper;
	}
	public void setTempPreflushUpper(double tempPreflushUpper) {
		this.tempPreflushUpper = tempPreflushUpper;
	}
	public double getTempPreflushLower() {
		return tempPreflushLower;
	}
	public void setTempPreflushLower(double tempPreflushLower) {
		this.tempPreflushLower = tempPreflushLower;
	}
	public double getCondCausticUpper() {
		return condCausticUpper;
	}
	public void setCondCausticUpper(double condCausticUpper) {
		this.condCausticUpper = condCausticUpper;
	}
	public double getCondCausticLower() {
		return condCausticLower;
	}
	public void setCondCausticLower(double condCausticLower) {
		this.condCausticLower = condCausticLower;
	}
	public double getTempCausticUpper() {
		return tempCausticUpper;
	}
	public void setTempCausticUpper(double tempCausticUpper) {
		this.tempCausticUpper = tempCausticUpper;
	}
	public double getTempCausticLower() {
		return tempCausticLower;
	}
	public void setTempCausticLower(double tempCausticLower) {
		this.tempCausticLower = tempCausticLower;
	}
	public double getCondAcidUpper() {
		return condAcidUpper;
	}
	public void setCondAcidUpper(double condAcidUpper) {
		this.condAcidUpper = condAcidUpper;
	}
	public double getCondAcidLower() {
		return condAcidLower;
	}
	public void setCondAcidLower(double condAcidLower) {
		this.condAcidLower = condAcidLower;
	}
	public double getTempAcidUpper() {
		return tempAcidUpper;
	}
	public void setTempAcidUpper(double tempAcidUpper) {
		this.tempAcidUpper = tempAcidUpper;
	}
	public double getTempAcidLower() {
		return tempAcidLower;
	}
	public void setTempAcidLower(double tempAcidLower) {
		this.tempAcidLower = tempAcidLower;
	}
	public Threshold(String product, String process, double condPreflushUpper, double condPreflushLower,
			double tempPreflushUpper, double tempPreflushLower, double condCausticUpper, double condCausticLower,
			double tempCausticUpper, double tempCausticLower, double condAcidUpper, double condAcidLower,
			double tempAcidUpper, double tempAcidLower) {
		
		this.product = product;
		this.process = process;
		this.condPreflushUpper = condPreflushUpper;
		this.condPreflushLower = condPreflushLower;
		this.tempPreflushUpper = tempPreflushUpper;
		this.tempPreflushLower = tempPreflushLower;
		this.condCausticUpper = condCausticUpper;
		this.condCausticLower = condCausticLower;
		this.tempCausticUpper = tempCausticUpper;
		this.tempCausticLower = tempCausticLower;
		this.condAcidUpper = condAcidUpper;
		this.condAcidLower = condAcidLower;
		this.tempAcidUpper = tempAcidUpper;
		this.tempAcidLower = tempAcidLower;
	}
	public String printThresholds() {
		
		return product + process + condPreflushUpper;
	}
	
}
