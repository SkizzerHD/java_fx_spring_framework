package de.fx.spring.resources;

import javafx.event.Event;

public interface FxSceneMover {
	
	<T> void moveToScene(Event event, Class<T>controllerClass);

}
