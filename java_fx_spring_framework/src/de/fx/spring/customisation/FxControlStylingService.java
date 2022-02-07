package de.fx.spring.customisation;

import javafx.event.Event;
import javafx.scene.control.Control;

/**
 * 
 * @author David Baumer
 * When the class gets extended buttons can be Hovered
 * or style can be switched
 *
 */
public abstract class FxControlStylingService{
	
	private Control controlObject;
	private String currentStyle;
	private Control currentActiveControlObject;
	private String currentActiveStyle;
	private String style;

	/*
	 * TODO set button, style and color
	 * Button style and color get initialized
	 */
	public abstract void setHoveringStyle();
	
	public abstract void setControlActiveStyle();
	
	public void hovering(Event event) {	
		setHoveringStyle();
		controlObject = (Control)event.getSource();
		if(controlObject != currentActiveControlObject) {
			currentStyle = controlObject.getStyle();
			controlObject.setStyle(currentStyle+style);
		}
	}
	
	public void hoveringEnded(Event event) {
		controlObject = (Control)event.getSource();
		if(controlObject != currentActiveControlObject) {
			controlObject.setStyle(currentStyle);
		}
	}
	
	
	/**
	 * 
	 * @param event
	 * control-object gets initalized with event
	 */
	public void activateControlObject(Event event) {
		setControlActiveStyle();
		controlObject = (Control)event.getSource();
		currentActiveStyle = controlObject.getStyle();
		currentActiveControlObject = controlObject;
		controlObject.setStyle(currentActiveStyle+style);
	}
	
	/**
	 * 
	 * @param control
	 * control-object gets initalized with a control-object
	 */
	public void activateControlObject(Control control) {
		setControlActiveStyle();
		controlObject = control;
		currentActiveStyle = controlObject.getStyle();
		currentActiveControlObject = controlObject;
		control.setStyle(currentActiveStyle+style);
	}
	
	/**
	 * 
	 * @param event
	 * control-object gets reseted by a event
	 */
	public void deactivateControlObject(Event event) {
		if(currentActiveControlObject != null) {
			currentActiveControlObject.setStyle(currentActiveStyle);
			currentActiveControlObject = null;
			currentActiveStyle = null;
		}
	}
	
	/**
	 * 
	 * @param event
	 * control-object gets reseted by another control-object
	 */
	public void deactivateControlObject(Control control) {
		if(currentActiveControlObject != null) {
			currentActiveControlObject.setStyle(currentActiveStyle);
			currentActiveControlObject = null;
			currentActiveStyle = null;
		}
	}

	public void setControlObject(Control controlObject) {
		this.controlObject = controlObject;
	}
	
	public void setStyle(String style) {
		this.style = style;
	}
}
