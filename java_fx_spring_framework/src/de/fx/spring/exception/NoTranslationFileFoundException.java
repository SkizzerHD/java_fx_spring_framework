package de.fx.spring.exception;

/**
 * 
 * @author David Baumer
 *
 */
public class NoTranslationFileFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoTranslationFileFoundException() {
		super("No translation- .txt or .csv file found!\n"
				+ "Please create a file or set the file in your application launch");
	}

}
