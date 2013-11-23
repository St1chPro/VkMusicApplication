package logic;

import graphics.LoginForm;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created with IntelliJ IDEA.
 * logic.User: St1ch
 * Date: 02.11.13
 * Time: 19:07
 * Package name: PACKAGE_NAME
 * Project name: VkMusicApplication
 */
public class MyApplication extends Application
{
    public static void main(String[] args) throws Exception
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        LoginForm loginForm = new LoginForm();
        loginForm.createGui();
    }
}
