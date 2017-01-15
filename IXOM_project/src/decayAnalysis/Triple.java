package decayAnalysis;

public class Triple {

	double A;
	double B;
	double rSquared;
	
	public Triple(double x, double y, double z){
		A = x;
		B = y;
		rSquared = z;
	}

	public double getA() {
		return A;
	}

	public void setA(double a) {
		A = a;
	}

	public double getB() {
		return B;
	}

	public void setB(double b) {
		B = b;
	}

	public double getrSquared() {
		return rSquared;
	}

	public void setrSquared(double rSquared) {
		this.rSquared = rSquared;
	}
}
