package de.fx.spring.exception;

import javafx.scene.control.Control;

public class NoTranslationPossibleException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public <T> NoTranslationPossibleException(Class<T>controlClass, Control control) {
		super("No translation possible for '"+controlClass.getSimpleName()+"."+control.getId()+"'\n\t translation only"
				+ "possible for labels and buttons ");
		
	}
	
}
