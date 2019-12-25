package mandelbrot.complex;
import java.lang.Math.*;

/**
*<h1>Complex</h1>
*The Complex program implements all common functions of
*complex numbers. 
*<br>
*
*@author	Tetiana Kravchuk
*@version 	1.0
*@since 	2019-08-01
*/

public class Complex implements Field<Complex>{
	private double r, i;
	/**
	*Contructor initialize real and imaginary parts with 0.
	*/
	public Complex(){
		r = 0;
		i = 0;
	}
	/**
	*Contructor initialize real  part with <i>r</i> and imaginary 
	*part with 0.
	*@param r real part of complex number
	*/
	public Complex(double r) { this.r=r; i = 0;}
	/**
	*Contructor initialize real  part with <i>r</i> and imaginary 
	*part with <i>i</i>.
	*@param r real part of complex number
	*@param i imaginary part of complex number
	*/
	public Complex(double r , double i) { this.r = r; this.i = i; }
	/**
	*Contructor initialize present Complex with <i>x</i> value
	*@param x Complex number
	*/
	public Complex(Complex x) { r = x.re(); i = x.im(); }
	/**
	*Contructor initialize present Complex with values given in String
	*argument <i>x</i> in format <i>"-1.23+4.56i"</i>
	*@param x String value of Complex number
	*/
	public Complex(String x) { 
		Complex tmp = valueOf(x);
		r = tmp.re();
		i = tmp.im();
	}

	/* Poniższe metody modyfikują aktualny obiekt i zwracają 'this' */
	
	/**
	*This method adds x to present complex number
	*@param x This is parametr to add 
	*@return Complex Returns Complex as a sum of present and x
	*complex numbers.
	*/

	public Complex add(Complex x) {
		r = r + x.re();
		i = i + x.im();
	return this;
	}  
	/**
	*This method substracts x from present complex number
	*@param x This is parametr to substract
	*@return Complex Returns Complex as a substraction of present 
	* and x complex numbers. <br>
	*/
	public Complex sub(Complex x) {
		r = r - x.re();
		i = r - x.im();
	return this;
	}  
	/**
	*This method multiplies present complex number by x
	*@param x This is second parametr of multiplication
	*@return Complex Returns product of present  and x complex 
	*numbers <br>
	*/
	public Complex mul(Complex x) {
		double re = r * x.re() - i * x.im();
		double im = r * x.im() + im() * x.re();
		this.r = re;
		this.i = im;
	return this;
	}
	/**
	*This method divides present complex number by x
	*T
	*@param x This is second parametr of division
	*@return Complex Returns Complex as a divi of present 
	*and x complex numbers, throwing an exception if there 
	*is dividing by 0.
	*@exception DivideByZeroException if x is 0
	*@see DivideByZeroException
	*/
	public Complex div(Complex x) throws DivideByZeroException{
		double re = r, im = i;
		if(x.sqrAbs() == 0) throw new DivideByZeroException("0", "0");
		r = (re*x.re() + im*x.im()) / x.sqrAbs();
		i = (im*x.re() - re*x.im())  / x.sqrAbs();
	return this;
	}  
	/**
	*This method returns the absolute value of present complex
	*number <i> z = x + yi </i>. |z| = sqrt(x^2 + y^2) .
	*@return double Returns the absolute value of present complex
	*number <i> z = x + yi </i>. |z| = sqrt(x^2 + y^2) . 
	*/
	public double abs(){
		return Math.hypot(r, i);
	}
	/**
	*This method returns the square of absolute value of present complex
	*number <i> z = x + yi </i>. |z| = sqrt(x^2 + y^2) .
	*@return double Returns the absolute value of present complex
	*number <i> z = x + yi </i>. |z| = sqrt(x^2 + y^2) . 
	*/
	public double sqrAbs(){
		return Math.pow(abs(), 2);
	}
	// double phase();         // Faza
	/**
	*Method returns real part of pesent complex number.
	*@return double Returns real part of present complex number.
	*/
	public double re(){				
		return r;
	}  
	/**
	*Method returns imaginary part of present complex number.
	*@return double Returns imaginary part of present complex number.
	*/          
	public double im(){	
		return i;
	}
	//           
	// STATIC METHODS
	//

	/**
	*This method adds two Complex 
	*@param x First parametr to add
	*@param y Second parametr to add 
	*@return Complex Returns Complex as a sum of x and y complex numbers.
	*/
	public static Complex add(Complex x, Complex y){

		Complex result = new Complex(x.re()+y.re(), x.im() + y.im());
	return result;
	} 
	/**
	*This method substracts two Complex 
	*@param x First parametr of supstraction 
	*@param y Second parametr of substraction
	*@return Complex Returns Complex as a substraction of x and
	*y complex numbers.
	*/
	public static Complex sub(Complex x, Complex y){
	
		Complex result = new Complex(x.re()+y.re(), x.im() + y.im());
	return result;
	}
	/**
	*This method returns product od two Complex 
	*@param x First parametr of multiplication
	*@param y Second parametr of multiplicaton
	*@return Complex Returns Complex as a multiplication of x and
	*y complex numbers.
	*/
	public static Complex mul(Complex x, Complex y){

		Double a = x.re();
		Double b = x.im();
		Double c = y.re(); 
		Double d = y.im();
		Double newRe, newIm;
		newRe = a*c - b*d;
		newIm = b*c + a*d;
		Complex result = new Complex(newRe, newIm);
	return result;
	}
	/**
	*This method divides one Complex by another 
	*@param x First parametr of division
	*@param y Second parametr of division
	*@return Complex Returns Complex as a division of x and
	*y complex numbers, throwing an exception if there 
	*is dividing by 0.
	*@exception DivideByZeroException if x is 0
	*@see DivideByZeroException
	*/
	public static Complex div(Complex x, Complex y)
			throws DivideByZeroException{
		if(y.sqrAbs() == 0) throw new DivideByZeroException("0", "0");
		Double a = x.re();
		Double b = x.im();
		Double c = y.re(); 
		Double d = y.im();
		Double newRe, newIm;
		newRe = (a*c + b*d) / (Math.pow(c, 2) + Math.pow(d, 2));
		newIm = (b*c - a*d) / (Math.pow(c, 2) + Math.pow(d, 2));
		Complex result = new Complex(newRe, newIm);
	return result;
	}
	/**
	*Returns the square absolute value of given complex
	*number <i> z = x + yi </i>. |z| = sqrt(x^2 + y^2) .
	*@param x Complex number 
	*@return double Returns the absolute value of given complex
	*number <i> z = x + yi </i>. |z| = sqrt(x^2 + y^2) . 
	*/
	public static double abs(Complex x){
		return Math.hypot(x.re(), x.im());
	}       



	// static double phase(Complex);   !!!!!!!!


	/**
	*This method returns the square of absolute value of givent complex
	*number <i> z = x + yi </i>. |z| = sqrt(x^2 + y^2) .
	*@param x Complex number
	*@return double Returns the absolute value of present complex
	*number <i> z = x + yi </i>. |z| = sqrt(x^2 + y^2) . 
	*/
	public static double sqrAbs(Complex x){
		return Math.pow(abs(x), 2);
	}
	/**
	*Returns real part of given complex number
	*@param x Complex number
	*@return double Returns real part of given Complex
	*/
	public static double re(Complex x){
		return x.re();
	}
	/**
	*Returns imaginary part of given complex number
	*@param x Complex number
	*@return double Returns imaginary part of given Complex
	*/
	public static double im(Complex x){
		return x.im();
	}

	//
	// ADDITIONAL METHODS
	//

	/**
	*Returns String value of Complex in format "-1.23+4.56i"
	*@return String Return String value of present complex number
	*/
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		// String istr = String.format("%.2f", i);
		str.append(r + (i < 0 ? "" : "+") + i + "i");
	return str.toString();
	}
	/**
	*Returns Complex on value given as an argument in such
	*format: "-1.23+4.56i"
	*@param str String value of complex number 
	*@return Complex Return complex number converted from 
	*given String
	*/
	public static Complex valueOf(String str){
		Complex result;
		if(str.isEmpty()) {
			result = new Complex(0, 0);
		}else{
			Double re, im;
			int mid = (str.length() - 1) / 2;
			re = Double.valueOf(str.substring(0, mid));
			im = Double.valueOf(str.substring(mid, str.length()-1));
			result = new Complex(re, im);
		}
	return result;
	}
	/**
	*Sets real part of present complex number
	*@param r new real part of complex number
	*/
	public void setRe(double r){
		this.r = r;
	}
	/**
	*Sets imaginary part of present complex number
	*@param i new imaginary part of complex number
	*/
	public void setIm(double i){
		this.i = i;
	}
	/**
	*Sets value of present complex number
	*@param x new value of complex number
	*/
	public void setVal(Complex x){

		this.r = x.re();
		this.i = x.im();
	}
	/**
	*Sets value of present complex number.
	*@param r real part of complex number
	*@param i imaginary part of complex number
	*/
	public void setVal(double r, double i){
		this.r = r;
		this.i = i;
	}


	public static void main(String[] args){

		Complex c1 = new Complex(1.0, 0.5);
		System.out.println(c1);

		Complex c2 = new Complex("+2.00-1.00i");
		System.out.println(c2);
		c1.add(c2);
		System.out.println("c1 + c2 = " + c1);
		c2.sub(c1); 
		System.out.println("c2 - c1 = " + c2);
	}
	
}