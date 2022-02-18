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
	 * @param <T>
	 * @param configClass
	 * @param args
	 * Needs a configClass which extends the Application class from JavaFx
	 * and the args-array from main Mthod
	 * @throws NoScourceClassFoundException 
	 * @throws NoSourceControllerClassFoundException 
	 */
	public static <T> void launch(Class<?>sourceClass,Class<?>sourceControllerClass,String[] args) 
			throws NoSourceClassFoundException, NoSourceControllerClassFoundException  {
		if(sourceClass== null) {
			throw new NoSourceClassFoundException();
		}else if(sourceControllerClass == null) {
			throw new NoSourceControllerClassFoundException();
		}else {
			ApplicationConfiguration.setScourceClasses(sourceClass,sourceControllerClass);
			Application.launch(ApplicationConfiguration.class,args);	
		}
	}

	@Bean 
	public FxSceneMover getFxSceneMover() {
		return new FxSceneMoverService();
	}
}
