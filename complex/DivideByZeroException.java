package mandelbrot.complex;
/**
*Exception when there is dividing by 0
*/


class DivideByZeroException extends Exception{
	String message; 
	DivideByZeroException(String a, String b){
		message = "Dividing by 0";
	}
	@Override
	public String getMessage(){
		return message;
	}
}