package de.fx.spring.exception;
import javafx.scene.control.Control;

public class NoTranslationValueFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public <T> NoTranslationValueFoundException(Class<T>controlClass,Control control) {
		super("No translation found for '"+controlClass.getSimpleName()+"."+control.getId()+"' "
				+ "in translation-file");
	}

}
