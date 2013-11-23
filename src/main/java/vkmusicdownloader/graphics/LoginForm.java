package graphics;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.*;


/**
 * Created with IntelliJ IDEA.
 * logic.User: St1ch
 * Date: 28.10.13
 * Time: 11:19
 * Package name: graphics
 * Project name: VkMusicApplication
 */
public class LoginForm
{
    public final String APP_NAME = "Vk Music Application";
    private final int STAGE_WIDTH = 285;
    private final int STAGE_HEIGHT = 200;
    private Stage primaryStage = new Stage();

    public void createGui()
    {
        //stage config
        primaryStage.setTitle(APP_NAME);
        primaryStage.setWidth(STAGE_WIDTH);
        primaryStage.setHeight(STAGE_HEIGHT);
        GraphicUtils.setCentralPosition(primaryStage);

        //header
        Text appHeader = new Text("Welcome to Music Downloader");
        appHeader.setId("app-header");

        //labels
        Label login = new Label("E-mail or phone:");
        login.setId("login");

        final Label password = new Label("Password:");
        password.setId("password");

        //fields
        final TextField loginTextField = new TextField(/*"Enter login/phone"*/"alextracerkoval@gmail.com");
        final PasswordField passwordField = new PasswordField();

        //buttons
        Button signIn = new Button("Sign in");
        Button exit = new Button("Exit");

        //hbox for buttons
        HBox buttonsBox = new HBox();
        buttonsBox.setPadding(new Insets(0, 10, 5, 5));
        buttonsBox.setSpacing(5);
        buttonsBox.getChildren().add(signIn);
        buttonsBox.getChildren().add(exit);

        //panes
        BorderPane mainPane = new BorderPane();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        //        gridPane.setGridLinesVisible(true);
        gridPane.setPadding(new Insets(20, 10, 10, 10));
        gridPane.setHgap(10);
        gridPane.setVgap(7);

        // first digit is column number, second - row
        gridPane.add(login, 0, 1);
        gridPane.add(password, 0, 2);

        gridPane.add(loginTextField, 1, 1);
        gridPane.add(passwordField, 1, 2);

        gridPane.add(buttonsBox, 1, 5);

        mainPane.setTop(appHeader);
        mainPane.setCenter(gridPane);
        BorderPane.setAlignment(appHeader, Pos.TOP_CENTER);

        Scene loginFormScene = new Scene(mainPane);
        loginFormScene.setFill(Paint.valueOf("27408B"));
        loginFormScene.getStylesheets().add(LoginForm.class.getResource("loginform/login.css").toExternalForm());

        primaryStage.setResizable(false);
        primaryStage.setScene(loginFormScene);
        primaryStage.show();

        signIn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                Utils utils = new Utils();

                User user;
                String login = loginTextField.getText();
                boolean isPhoneEntered = utils.isPhone(login);
                String pass = passwordField.getText();

                if(isPhoneEntered)
                {
                    user = new User();
                    user.createByPhone(login, pass);
                }

                user = new User(login, pass);
                Authorization connection = new Authorization(user);
                VkAccessToken accessToken = connection.getAccessToken();
                VkApi api = new VkApi(accessToken);
                System.out.println("access granted");
                new AppForm(api);
                primaryStage.close();
            }
        });

        exit.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                primaryStage.close();
            }
        });
    }

}