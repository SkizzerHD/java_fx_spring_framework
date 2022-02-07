package de.fx.spring.launch;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import de.fx.spring.exception.NoSourceClassFoundException;
import de.fx.spring.exception.NoSourceControllerClassFoundException;
import de.fx.spring.resources.FxSceneMover;
import de.fx.spring.resources.FxSceneMoverService;
import javafx.application.Application;

/**
 * 
 * @author David Baumer
 *
 */
@Configuration
@SpringBootApplication
public class JavaFxApplicationLauncher {
	/**
	 * 
	 * @param configClass
	 * @param args
	 * Needs a configClass which extends the Application class from JavaFx
	 * and the args-array from main Mthod
	 * @throws NoScourceClassFoundException 
	 * @throws NoSourceControllerClassFoundException 
	 */
	public static void launch(Class<? extends Application> configClass,String[] args) 
			throws NoSourceClassFoundException, NoSourceControllerClassFoundException  {
		if(ApplicationConfiguration.getSourceClass() == null) {
			throw new NoSourceClassFoundException();
		}else if(ApplicationConfiguration.getSourceController() == null) {
			throw new NoSourceControllerClassFoundException();
		}else {
			Application.launch(configClass,args);	
		}
	}

	@Bean 
	public FxSceneMover getFxSceneMover() {
		return new FxSceneMoverService();
	}
	
	public static <T> void setSourceClass(Class<T>scourceClass) {
		ApplicationConfiguration.setScourceClass(scourceClass); 
	}
	
	public static <T> void setSourceController(Class<T>sourceController) {
		ApplicationConfiguration.setSourceController(sourceController);
	}

}
