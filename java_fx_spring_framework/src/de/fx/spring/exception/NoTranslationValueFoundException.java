package de.fx.spring.exception;

import javafx.scene.control.Control;

public class NoTranslationValueFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public <T> NoTranslationValueFoundException(Class<T> componentClass,Control c) {
		super("No translation found for '"+componentClass.getSimpleName()+"."+c.getId()+"' "
				+ "in translation-file");
	}

}
