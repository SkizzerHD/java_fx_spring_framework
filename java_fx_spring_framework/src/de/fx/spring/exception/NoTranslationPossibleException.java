package de.fx.spring.exception;

import javafx.scene.control.Control;

public class NoTranslationPossibleException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public <T> NoTranslationPossibleException(Class<T> componentClass, Control c) {
		super("No translation possible for '"+componentClass.getSimpleName()+"."+c.getId()+"'\n\t translation only"
				+ "possible for labels and buttons ");
		
	}
	
}
