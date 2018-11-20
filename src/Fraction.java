
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
	
	double getNumber() {
		return numerator/denominaor;
	}
	
	void add(Fraction other) {
		numerator = (numerator * other.denominaor) + (other.numerator * denominaor);
		denominaor = denominaor * other.denominaor;
		int GCD = GCD(numerator,denominaor);
		numerator = numerator/GCD;
		denominaor = denominaor/GCD;
	}
	
	void multiply(Fraction other) {
		numerator = numerator * other.numerator;
		denominaor = denominaor * other.denominaor;
		int GCD = GCD(numerator,denominaor);
		numerator = numerator/GCD;
		denominaor = denominaor/GCD;
	}
	
	public int GCD(int a, int b) {
		   if (b==0) {
			   return a;
		   }
		   return GCD(b,a%b);
	}
}
