package graphics.loginform;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created with IntelliJ IDEA.
 * logic.User: St1ch
 * Date: 03.11.13
 * Time: 17:04
 * Package name: graphics.loginform
 * Project name: VkMusicApplication
 */
public class TestGrid extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        GridPane gridPane = new GridPane();
        gridPane.add(new Button("but"), 0, 0);
        gridPane.add(new Button("but"), 1, 2);
        gridPane.add(new Button("but"), 2, 0);
        gridPane.add(new Button("but"), 0, 0);
        gridPane.add(new Button("but"), 1, 3);
        gridPane.add(new Button("but"), 2, 0);
        gridPane.add(new Button("but"), 3, 4);

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setHgap(5);
        gridPane.setVgap(5);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setGridLinesVisible(true);

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }
}
