package rest.resrvtn.exception;

public class AppException extends Exception{

	private static final long serialVersionUID = -1278309392442218826L;
	public AppException (String msg){
		super(msg);
	}
	public AppException (String msg, Throwable cause){
		super(msg, cause);
	}
}
