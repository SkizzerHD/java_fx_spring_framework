package de.fx.spring.launch;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;

/**
 * 
 * @author David Baumer
 *
 */
public class ApplicationConfiguration extends Application {
	
	private ConfigurableApplicationContext applicationContext;
	
	private static Class<?> sourceClass;
	
	private static Class<?> sourceController;
	

	@Override
	public void init() {
		String[] args = getParameters().getRaw().toArray(new String[0]);
		this.applicationContext = new SpringApplicationBuilder()
				.sources(sourceClass)
				.run(args);
	}

	@Override
	public void stop() {
		this.applicationContext.close();
		Platform.exit();
	}

	@Override
	public void start(Stage stage) throws Exception {
		FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
		Parent root = fxWeaver.loadView(sourceController);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	public static <T> void setScourceClasses(Class<?> sourceClass2, Class<?> sourceControllerClass) {
		sourceClass = sourceClass2;
		sourceController = sourceControllerClass;
	}
}
