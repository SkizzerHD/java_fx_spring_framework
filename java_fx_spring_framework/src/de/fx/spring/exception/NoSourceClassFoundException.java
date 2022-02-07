package de.fx.spring.exception;

import de.fx.spring.launch.JavaFxApplicationLauncher;

/**
 * 
 * @author David Baumer
 *
 */
public class NoSourceClassFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSourceClassFoundException() {
		super("No source class found\n"
				+ "Please specify a class at application launch with method 'setSourceClass()' from "+JavaFxApplicationLauncher.class
						+ " which scans the basePackeges");
	}

}
