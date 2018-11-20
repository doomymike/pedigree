public class Fraction {
	int numerator;
	int denominator;
	
	Fraction(int numerator, int denominator){
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	int[] get() {
		int[] ans = {numerator,denominator};
		return ans;
	}
	
	void set(int numerator,int denominaor) {
		this.numerator = numerator;
		this.denominator = denominaor;
	}
	
	double getNumber() {
		return numerator/denominator;
	}
	
	Fraction add(Fraction other) {
		int top = (numerator * other.denominator) + (other.numerator * denominator);
		int bottom = denominator * other.denominator;
		int GCD = GCD(top,bottom);
		top = numerator/GCD;
		bottom = denominator/GCD;
		return new Fraction(top,bottom);
	}
	
	Fraction multiply(Fraction other) {
		int top = numerator * other.numerator;
		int bottom = denominator * other.denominator;
		int GCD = GCD(numerator,denominator);
		top = numerator/GCD;
		bottom = denominator/GCD;
		return new Fraction(top,bottom);
	}
	
	Fraction subtract(Fraction other) {
		return this.add(new Fraction(-other.numerator,other.denominator));
	}
	
	Fraction divide(Fraction other) {
		return this.multiply(new Fraction(other.denominator,other.numerator));
	}
	
	public int GCD(int a, int b) {
		   if (b==0) {
			   return a;
		   }
		   return GCD(b,a%b);
	}
}
