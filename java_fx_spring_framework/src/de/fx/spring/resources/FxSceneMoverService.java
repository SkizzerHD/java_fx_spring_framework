package de.fx.spring.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;

@Component
public class FxSceneMoverService implements FxSceneMover{
	
	@Autowired
	private FxWeaver fxWeaver;
	
	public <T> void moveToScene(Event event, Class<T>controllerClass) {
		Parent root = fxWeaver.loadView(controllerClass);
		Scene scene = new Scene(root);
		Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

}
