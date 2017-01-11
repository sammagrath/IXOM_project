package decayAnalysis;

import java.util.ArrayList;



public class regressionAndParameters {
	public regressionAndParameters(ArrayList<Coordinate> data){
		//y   = b   +     ax
		//lnT = lnA - 1/tau * t
		
		double[] y = new double[data.size()] , x = new double[data.size()];
		
		for (int i=0;i<data.size();i++){
			x[i]=data.get(i).getTime();
			y[i]=Math.log((data.get(i).getValue()));
		}
		double n = (double) x.length;
		double b = ( mean(y)*sum(vmult(x, x))-mean(x)*sum(vmult(x,y)) ) / ( n*sum(vmult(x,x)) - sum(x)*sum(x) );
		double a = ( sum(vmult(x,y)) - n*mean(x)*mean(y) ) / ( sum(vmult(x,x)) -n*mean(x)*mean(x) );
		
		double A = Math.exp(b);
		double tau=-1/a;
		
		
		
		double RSquared = calculateRSquared(x,y,a,b);
		
		
	}
	
	private double calculateRSquared(double[] x, double[] y, double a, double b) {
		double total=0;
		for (int i=0; i<x.length;i++){
			total+=(y[i]-a*x[i]-b) * (y[i]-a*x[i]-b);
		}
		return total;
	}

	private double sum(double[] vals){
		double total=0;
		for (double v:vals){
			total+=v;
			
		}
		return total;
	}
	
	private double mean(double[] vals){
		double sum = sum(vals);
		return sum/(vals.length);
	}
	
	private double[] vmult(double[] x, double[] y){
		double[] array = new double[x.length];
		for (int i=0;i<x.length;i++){
			array[i]=x[i]*y[i];
		}
		return array;
	}
	
	
}
