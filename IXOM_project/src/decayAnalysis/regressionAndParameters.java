package decayAnalysis;

import java.util.ArrayList;
import timeconverter.*;



public class regressionAndParameters {
	double A; // in units turbidity or conductivity.
	double tau; // in units of seconds
	double RSquared;
	
	
	
	public regressionAndParameters(ArrayList<Coordinate> data){
		//y   = b   +     ax
		//lnT = lnA - 1/tau * t
		
		double[] y = new double[data.size()] , x = new double[data.size()];
		TimeConverter tc = new TimeConverter();
		double start = tc.HMSToDec(data.get(0).getTime())*86400;
		
		for (int i=0;i<data.size();i++){
			x[i]= tc.HMSToDec(data.get(i).getTime())*86400 - start;
			y[i]=Math.log((data.get(i).getValue()));
		}
		double n = (double) x.length;
		double b = ( mean(y)*sum(vmult(x, x))-mean(x)*sum(vmult(x,y)) ) / ( n*sum(vmult(x,x)) - sum(x)*sum(x) );
		double a = ( sum(vmult(x,y)) - n*mean(x)*mean(y) ) / ( sum(vmult(x,x)) -n*mean(x)*mean(x) );
		
		A = Math.exp(b);
		tau=-1/a;
		
		
		
		RSquared = calculateRSquared(x,y,a,b);
		
		
	}
	
	
	//Jacob sticking his nose where it doesn't belong. Alternate method to Henry's code above.
	//Returns A and B to fit a functional form y = Aexp(Bx) as well as rSquared value for the curve
	public Triple leastSquaresFitting(ArrayList<Coordinate> data){
		
		TimeConverter tc = new TimeConverter();
		int n = data.size();
		double start = tc.HMSToDec(data.get(0).getTime())*86400;
		double[] x = new double[n], y = new double[n];
		
		for(int i = 0; i < n; i++){
			x[i] = tc.HMSToDec(data.get(i).getTime())*86400 - start;
			y[i] = Math.log(data.get(i).getValue());
		}
		
		double a = ((sum(y)*sum(vmult(x, x))) - (sum(x)*sum(vmult(x, y)))) / ((n*sum(vmult(x, x))) - (sum(x)*sum(x)));
		double b = ((n*sum(vmult(x, y))) - (sum(x)*sum(y))) / ((n*sum(vmult(x, x))) - (sum(x)*sum(x)));
		
		double c = sum(vmult(x, x));
		double d = sum(y);
		double e = sum(vmult(x, y));
		double f = n;
		double g = sum(x);
		
		System.out.println("Numbers are "+c+", "+", "+", "+", ");
		
		
		
		
		
		
		
		double rSq = calculateRSquared(x, y, b, a);
		
		Triple toReturn = new Triple(Math.exp(a), b, rSq);
		
		return toReturn;
		
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
