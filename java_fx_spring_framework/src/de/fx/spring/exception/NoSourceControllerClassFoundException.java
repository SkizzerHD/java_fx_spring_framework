package de.fx.spring.exception;

import de.fx.spring.launch.JavaFxApplicationLauncher;

/**
 * 
 * @author David Baumer
 *
 */
public class NoSourceControllerClassFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSourceControllerClassFoundException() {
		super("No source controller class found\n"
				+ "Please specify a JavaFx controller class at application launch with method 'setSourceControllerClass()'"
				+ " from "+JavaFxApplicationLauncher.class);
	}

}
