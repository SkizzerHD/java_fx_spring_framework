package de.fx.spring.resources;

import java.util.HashMap;
import org.springframework.stereotype.Component;
import de.fx.spring.exception.EmptyTranslationFileException;
import de.fx.spring.exception.NoTranslationFileFoundException;
import de.fx.spring.exception.NoTranslationPossibleException;
import de.fx.spring.exception.NoTranslationValueFoundException;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

/**
 * @version 0.1
 * @author David Baumer 
 * Can translate javaFx Labels and Buttons
 * You only need to specify a translation file with id and value
 * E.g.: label=translation
 *        ^       ^
 *       (id)  (value)
 * 
 */
@Component
public class FXRManager {

	//responsible for reading the files
	private static  TFController controller;

	private static String file;

	//responsible for saving and assigning the IDs and translations
	private static HashMap<String,String>translationMap;

	public static <T> void translateComponents(Class<T> componentClass, Control... objects) {
		try {
			if(checkFile()) {
				controller = new TFController(file);
				setHashMap();
				for(Control c : objects) {
					String key = componentClass.getSimpleName()+"."+c.getId();
					if(translationMap.containsKey(key)) {
						if(c.getClass().isAssignableFrom(Label.class)) {
							Label l = (Label) c;
							l.setText(translationMap.get(key));
						}else if(c.getClass().isAssignableFrom(Button.class)) {
							Button b = (Button)c;
							b.setText(translationMap.get(key));
						}else if(c.getClass().isAssignableFrom(Hyperlink.class)) {
							Hyperlink h = (Hyperlink) c;
							h.setText(translationMap.get(key));
						}else {
							throwNoTranslationPossible(componentClass, c);
						}
					}else {
						throwNoTranslationValueFound(componentClass, c);
					}
				}
			}
		} catch (NoTranslationFileFoundException | EmptyTranslationFileException | 
				NoTranslationValueFoundException | NoTranslationPossibleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//fills HashMap with values from translation-file
	private static void setHashMap() throws EmptyTranslationFileException {
		translationMap = new HashMap<>();
		for(Object o : controller.getList()) {
			String s= (String) o;
			if(s==null) {
				throw new EmptyTranslationFileException();
			}else {
				if(s.equals("")) {
					continue;
				}else {
					String[]values= s.split("=");
					translationMap.put(values[0], values[1]);		
				}
			}
		}	
	}

	//sets the file that the controller can read from it
	public static void setTranslationFile(String translationFile) {
		file = translationFile;
	}

	private static boolean checkFile() throws NoTranslationFileFoundException {
		if(file == null) {
			throw new NoTranslationFileFoundException();
		}
		return true;
	}

	private static <T> void throwNoTranslationValueFound(Class<T> componentClass,Control c) 
			throws NoTranslationValueFoundException {
		throw new NoTranslationValueFoundException(componentClass,c);
	}

	private static <T>void throwNoTranslationPossible(Class<T> componentClass,Control c) 
			throws NoTranslationPossibleException{
		throw new NoTranslationPossibleException(componentClass, c);

	}
}
