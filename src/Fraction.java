
public class Fraction {
	int numerator;
	int denominaor;
	
	Fraction(int numerator, int denominaor){
		this.numerator = numerator;
		this.denominaor = denominaor;
	}
	
	int[] get() {
		int[] ans = {numerator,denominaor};
		return ans;
	}
	
	void set(int numerator,int denominaor) {
		this.numerator = numerator;
		this.denominaor = denominaor;
	}
	
	double getNumber() {
		return numerator/denominaor;
	}
	
	Fraction add(Fraction other) {
		int top = (numerator * other.denominaor) + (other.numerator * denominaor);
		int bottom = denominaor * other.denominaor;
		int GCD = GCD(top,bottom);
		top = numerator/GCD;
		bottom = denominaor/GCD;
		return new Fraction(top,bottom);
	}
	
	Fraction multiply(Fraction other) {
		int top = numerator * other.numerator;
		int bottom = denominaor * other.denominaor;
		int GCD = GCD(numerator,denominaor);
		top = numerator/GCD;
		bottom = denominaor/GCD;
		return new Fraction(top,bottom);
	}
	
	public int GCD(int a, int b) {
		   if (b==0) {
			   return a;
		   }
		   return GCD(b,a%b);
	}
}
