package graphics;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Created with IntelliJ IDEA.
 * Person: St1ch
 * Date: 06.11.13
 * Time: 8:35
 * Package name: graphics
 * Project name: VkMusicApplication
 */
public class About extends Application
{
    private static final String TITLE = "About";
    private static final String APPLICATION_NAME = "VK Music Downloader";
    private static final String VERSION = "1.0";
    private static final String AUTHOR = "Alexander Koval";
    private static final String EMAIL = "alextracerkoval@gmail.com";
    private static final String ESTABLISHED_YEAR = "2014";
    private static final double HEIGHT = 150;
    private static final double WIDTH = 290;


    @Override
    public void start(final Stage primaryStage) throws Exception
    {
        primaryStage.setTitle(TITLE);
        primaryStage.setHeight(HEIGHT);
        primaryStage.setWidth(WIDTH);
        primaryStage.setResizable(false);

        Button ok = new Button("OK");
        ok.setAlignment(Pos.TOP_RIGHT);
        ok.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                primaryStage.close();
            }
        });


        Label applicationName = new Label(APPLICATION_NAME);
        applicationName.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        Font informationFont = Font.font("Tahoma", FontWeight.BOLD, 10);

        Label applicationVersion = new Label("v. " + VERSION);
        applicationVersion.setFont(informationFont);

        Label authorEmail = new Label("E-mail: " + EMAIL);
        authorEmail.setFont(informationFont);

        Label author = new Label("Author: " + AUTHOR);
        author.setFont(informationFont);

        Label establishedYear = new Label(ESTABLISHED_YEAR);
        establishedYear.setFont(informationFont);

        VBox centerBox = new VBox(5);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(applicationName, applicationVersion);

        BorderPane mainPane = new BorderPane();
        mainPane.setPadding(new Insets(5));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(50);
        gridPane.add(author, 0, 0);
        gridPane.add(authorEmail, 0, 1);
        gridPane.add(establishedYear, 0, 2);
        gridPane.add(ok, 1, 2);

        mainPane.setCenter(centerBox);
        mainPane.setBottom(gridPane);

        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
         launch(args);
    }
}
