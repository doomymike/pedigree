/**
 * Fraction.java
 * @author Max Tang
 *Dec 7 2018
 *Custom fraction class
 */
public class Fraction {
	int numerator;
	int denominator;
	
	/**
	 * Fraction
	 * 
	 * @param numerator
	 * @param denominaor
	 * Constructor for new fraction
	 */
	Fraction(int numerator, int denominaor){
		this.numerator = numerator;
		this.denominator = denominaor;
	}
	
	/**
	 * get
	 * @return int[]
	 * Returns fraction as int array
	 * 
	 */
	int[] get() {
		int[] ans = {numerator,denominator};
		return ans;
	}
	
	/**
	 * set
	 * 
	 * @param numerator
	 * @param denominator
	 * Assigns fraction
	 */
	void set(int numerator,int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	/**
	 * getNumber
	 * 
	 * @return double
	 * Returns fraction as double
	 */
	double getNumber() {
		return (double)numerator/(double)denominator;
	}
	
	/**
	 * add
	 * 
	 * @param other
	 * @return Fraction
	 * Returns sum of two fractions
	 */
	Fraction add(Fraction other) {
		int top = (numerator * other.denominator) + (other.numerator * denominator);
		int bottom = denominator * other.denominator;
		int GCD = GCD(top,bottom);
		top = top/GCD;
		bottom = bottom/GCD;
		return new Fraction(top,bottom);
	}
	
	/**
	 * multiply
	 * 
	 * @param other
	 * @return Fraction
	 * Returns product of two fractions
	 */
	Fraction multiply(Fraction other) {
		int top = numerator * other.numerator;
		int bottom = denominator * other.denominator;
		int GCD = GCD(top,bottom);
		top = top/GCD;
		bottom = bottom/GCD;
		return new Fraction(top,bottom);
	}
	
	/**
	 * subtract
	 * 
	 * @param other
	 * @return Fraction
	 * Returns difference of two fractions
	 */
	Fraction subtract(Fraction other) {
		return this.add(new Fraction(-other.numerator,other.denominator));
	}
	
	/**
	 * divide
	 * 
	 * @param other
	 * @return Fraction
	 * Returns quotient of two fractions
	 */
	Fraction divide(Fraction other) {
		return this.multiply(new Fraction(other.denominator,other.numerator));
	}
	
	/**
	 * GCD
	 * 
	 * @param a
	 * @param b
	 * @return int
	 * Returns greatest common denominator of two ints
	 */
	public int GCD(int a, int b) {
		   if (b==0) {
			   return a;
		   }
		   return GCD(b,a%b);
	}
}
