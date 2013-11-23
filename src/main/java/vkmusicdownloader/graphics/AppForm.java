package graphics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.VkApi;
import logic.VkAudio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static javafx.application.Application.launch;

/**
 * Created with IntelliJ IDEA.
 * logic.User: St1ch
 * Date: 03.11.13
 * Time: 20:52
 * Package name: graphics.applicationform
 * Project name: VkMusicApplication
 */
public class AppForm
{
    public final String APP_NAME = "Vk Music Application";
    private final int STAGE_WIDTH = 410;
    private final int STAGE_HEIGHT = 600;

    private VkApi api;
    private Stage appStage;
    private BorderPane mainPane;
    private MenuBar menuBar;
    private TabPane tabPane;
    private final List<VkAudio> downloadQueue = new LinkedList<>();
    private TableView<VkAudio> table;
    ObservableList<VkAudio> audioList;

    public AppForm(VkApi api)
    {
        this.api = api;
        //stage
        appStage = new Stage();
        appStage.setTitle(APP_NAME);
        appStage.setWidth(STAGE_WIDTH);
        appStage.setMaxWidth(STAGE_WIDTH);
        appStage.setMinWidth(STAGE_WIDTH);
        appStage.setHeight(STAGE_HEIGHT);

        menuBar = createMenuBar();
        tabPane = createTabsPane();

        mainPane = new BorderPane();

        mainPane.setTop(menuBar);
        mainPane.setCenter(tabPane);

        Scene appScene = new Scene(mainPane);
        appStage.setScene(appScene);
        appStage.show();
        tabPane.lookup(".headers-region").setStyle("-fx-effect: null;");
        tabPane.getStyleClass().add("floating");
    }

    private TabPane createTabsPane()
    {
        //tab mainPane
        TabPane tabs = new TabPane();
        tabs.setId("tabs");
        //method of making the closing tabPane unavailable
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        //tab music
        Tab music = new Tab("Music");

        //        table
        table = new TableView<>();
        table.setEditable(false);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        audioList = FXCollections.observableArrayList(api.getAudioList(200));

        //        columns
        TableColumn artist = new TableColumn("Artist");
        artist.setPrefWidth(150);
        artist.setCellValueFactory(new PropertyValueFactory<VkAudio, String>("artist"));

        TableColumn title = new TableColumn("Title");
        title.setPrefWidth(150);
        title.setCellValueFactory(new PropertyValueFactory<VkAudio, String>("title"));

        TableColumn select = new TableColumn("Select");
        select.setCellFactory(new Callback()
        {
            @Override
            public Object call(Object o)
            {
                return new CheckboxCell();
            }
        });
        table.setItems(audioList);


        table.getColumns().addAll(artist, title, select);

        table.setPrefHeight(STAGE_HEIGHT);

        music.setContent(table);

        //tab photo
        Tab photo = new Tab("Photo");
        //tab video
        Tab video = new Tab("Video");

        tabs.setTabMinWidth(100);
        tabs.getTabs().addAll(music, photo, video);
        return tabs;
    }

    private MenuBar createMenuBar()
    {
        MenuBar menuBar = new MenuBar();

        //menus
        Menu menu = new Menu("Menu");
        MenuItem logout = new MenuItem("Log out");
        MenuItem preferences = new MenuItem("Preferences");
        MenuItem download = new MenuItem("download");
        MenuItem exit = new MenuItem("Exit");
        menu.getItems().addAll(logout, preferences, download, exit);

        logout.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                appStage.close();
                new LoginForm().createGui();
            }
        });

        preferences.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                preferencesDialog();
            }
        });

        exit.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                appStage.close();
            }
        });

        download.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                for(int i = 0; i < downloadQueue.size(); i++)
                {
                    api.downloadFile(downloadQueue.get(i).getUrl(),
                                     "D:/music/" + downloadQueue.get(i).getArtist() + "-" + downloadQueue.get(i)
                                                                                                         .getTitle() + ".mp3");
                }
                System.out.println("complete");
            }
        });


        Menu help = new Menu("Help");
        MenuItem about = new MenuItem("About");
        help.getItems().add(about);

        help.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                helpDialog();
            }
        });

        menuBar.getMenus().addAll(menu, help);

        return menuBar;
    }

    private void helpDialog()
    {


    }

    private void preferencesDialog()
    {

    }

    class CheckboxCell extends TableCell<VkAudio, Boolean>
    {
        private CheckBox checkBox;

        CheckboxCell()
        {
            checkBox = new CheckBox();
            checkBox.setAlignment(Pos.CENTER);
            setListener();
            this.setGraphic(checkBox);
        }

        private void setListener()
        {
            checkBox.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    if(!checkBox.isSelected())
                    {
                    }
                    System.out.println(downloadQueue.toString());
                }
            });
        }
    }

}
