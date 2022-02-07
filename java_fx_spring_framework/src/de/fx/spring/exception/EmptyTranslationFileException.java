package de.fx.spring.exception;

public class EmptyTranslationFileException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyTranslationFileException() {
		super("Your translation file is empty\n"
				+ "please define a translation in your translation-file when you call a "
				+ "translation-methods in your application");

	}

}
