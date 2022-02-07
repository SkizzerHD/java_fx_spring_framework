package de.fx.spring.customisation;

import javafx.scene.control.Alert;

/**
 * 
 * @author David Baumer
 *
 */
public class FxAlert extends Alert {

	public FxAlert(AlertType type, String content) {
		super(type);
		setHeaderText(content);
		showAndWait();
		setResizable(false);
	}
	
	public static void setAlert(AlertType alertType, String content) {
		new FxAlert(alertType, content);
	}

}
