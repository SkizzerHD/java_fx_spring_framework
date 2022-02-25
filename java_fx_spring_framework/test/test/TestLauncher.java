package test;

import de.fx.spring.exception.NoSourceClassFoundException;
import de.fx.spring.exception.NoSourceControllerClassFoundException;
import de.fx.spring.launch.JavaFxApplicationLauncher;

public class TestLauncher extends JavaFxApplicationLauncher {
	
	public static void main(String[] args) {
		try {
			launch(TestLauncher.class, TestController.class, args);
		} catch (NoSourceClassFoundException | NoSourceControllerClassFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
