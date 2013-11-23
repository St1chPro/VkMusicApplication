package graphics;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


/**
 * Created with IntelliJ IDEA.
 * User: St1ch
 * Date: 20.11.13
 * Time: 15:20
 * Package name: graphics
 * Project name: VkMusicApplication
 */
public class Test extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setWidth(400);
        stage.setHeight(600);

        BorderPane bp = new BorderPane();
        GridPane gp = new GridPane();

        gp.setHgap(10);
        gp.setVgap(10);
        gp.setPadding(new Insets(5));

        Label downloadDirectory = new Label("Select directory, \nwhere files will be download");
        downloadDirectory.setFont(new Font("Times New Roman", 14));

        Button setDirectory = new Button("Select directory");
        setDirectory.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {

            }
        });

        gp.add(downloadDirectory, 0, 0);
        gp.add(setDirectory, 1, 0);

        gp.setGridLinesVisible(true);


        Scene sc = new Scene(gp);
        stage.setScene(sc);
        stage.show();
    }
}
