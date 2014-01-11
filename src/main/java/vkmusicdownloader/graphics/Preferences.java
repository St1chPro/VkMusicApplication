package graphics;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * Person: St1ch
 * Date: 06.11.13
 * Time: 8:33
 * Package name: graphics
 * Project name: VkMusicApplication
 */
public class Preferences
{
    private File directory = new File("H:/Music from vk");

    public void init()
    {
        final Stage primaryStage = new Stage();

        primaryStage.setHeight(200);
        primaryStage.setWidth(300);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Preferences");

        Label downloadDirectoryLabel = new Label("Download directory is:");
        downloadDirectoryLabel.setFont(new Font("Monospace", 12));
        downloadDirectoryLabel.setTextFill(Color.valueOf("ECECEC"));

        final TextField downloadDirectoryField = new TextField(directory.getPath());
        downloadDirectoryField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                if(keyEvent.getCode() == KeyCode.ENTER)
                {
                    downloadDirectoryField.setText(downloadDirectoryField.getText());
                    File selectedDirectory = new File(downloadDirectoryField.getText());
                    if(!selectedDirectory.getPath().equals("") && selectedDirectory.exists())
                    {
                        directory = selectedDirectory;
                    }
                }
            }
        });


        Button selectDownloadDirectory = new Button("Select directory");
        selectDownloadDirectory.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setInitialDirectory(new File("D:/"));
                directoryChooser.setTitle("Select directory");

                File selectedDirectory = directoryChooser.showDialog(primaryStage);

                if(selectedDirectory != null)
                {
                    directory = selectedDirectory;
                    downloadDirectoryField.setText(directory.getPath());
                }
                System.out.println(directory.getPath());
            }
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        //        grid.setGridLinesVisible(true);
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setAlignment(Pos.TOP_CENTER);

        grid.add(downloadDirectoryLabel, 0, 0);
        grid.add(downloadDirectoryField, 0, 1);
        grid.add(selectDownloadDirectory, 1, 1);

        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(grid);

        Scene scene = new Scene(mainPane);
        scene.setFill(Color.valueOf("3D3F41"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public File getDirectory()
    {
        return directory;
    }

}
