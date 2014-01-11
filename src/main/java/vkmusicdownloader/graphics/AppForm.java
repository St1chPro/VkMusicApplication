package graphics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.VkApi;
import logic.VkAudio;

import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * logic.Person: St1ch
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
    private List<VkAudio> downloadQueue = new ArrayList<>();
    private TableView<VkAudio> table;
    private ObservableList<VkAudio> audioList;
    private Preferences preferencesDialog;

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

        preferencesDialog = new Preferences();
    }

    private TabPane createTabsPane()
    {
        //tab mainPane
        //todo: extract every tab in external class
        TabPane tabs = new TabPane();
        tabs.setId("tabs");
        //method making the closing tabPane unavailable
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        //tab music
        Tab music = new Tab("Music");

        //table
        table = new TableView<>();
        table.setEditable(false);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        audioList = FXCollections.observableArrayList(api.getAudioList(6000));

        //columns
        TableColumn<VkAudio, String> artist = new TableColumn("Artist");
        artist.setPrefWidth(150);
        artist.setCellValueFactory(new PropertyValueFactory<VkAudio, String>("artist"));

        TableColumn<VkAudio, String> title = new TableColumn("Title");
        title.setPrefWidth(150);
        title.setCellValueFactory(new PropertyValueFactory<VkAudio, String>("title"));

        TableColumn<VkAudio, Boolean> select = new TableColumn("Select");

        Callback<TableColumn<VkAudio, Boolean>, TableCell<VkAudio, Boolean>> booleanCellFactory = new Callback<TableColumn<VkAudio, Boolean>, TableCell<VkAudio, Boolean>>()
        {
            @Override
            public TableCell<VkAudio, Boolean> call(TableColumn<VkAudio, Boolean> p)
            {
                return new CheckBoxCell();
            }
        };
        select.setCellValueFactory(new PropertyValueFactory<VkAudio, Boolean>("select"));
        select.setCellFactory(booleanCellFactory);

        select.setEditable(false);
        select.setSortable(false);
        table.setItems(audioList);
        table.getColumns().addAll(artist, title, select);
        table.setPrefHeight(STAGE_HEIGHT);

        music.setContent(table);

        //tab photo
        //        Tab photo = new Tab("Photo");
        //tab video
        //        Tab video = new Tab("Video");

        tabs.setTabMinWidth(100);
        tabs.getTabs().add(music);
        //        tabs.getTabs().addAll(music, photo, video);
        return tabs;
    }

    private MenuBar createMenuBar()
    {
        MenuBar menuBar = new MenuBar();

        //menus
        Menu menu = new Menu("Menu");
        MenuItem logout = new MenuItem("Log out");
        MenuItem preferences = new MenuItem("Preferences");
        MenuItem download = new MenuItem("Download");
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
                preferencesDialog.init();

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
                String path = preferencesDialog.getDirectory().toString();
                System.out.println(path);
                downloadSong(path);
            }
        });


        Menu help = new Menu("Help");
        MenuItem about = new MenuItem("About");
        help.getItems().add(about);

        about.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                new About();
            }
        });

        menuBar.getMenus().addAll(menu, help);

        return menuBar;
    }

    private void downloadSong(String path)
    {
        System.out.println("Download started...");
        for(int i = 0; i < downloadQueue.size(); i++)
        {
            api.downloadFile(downloadQueue.get(i).getUrl(), new StringBuilder().append(path)
                                                                               .append("/")
                                                                               .append(downloadQueue.get(i).getArtist())
                                                                               .append("-")
                                                                               .append(downloadQueue.get(i).getTitle())
                                                                               .append(".mp3")
                                                                               .toString());
        }
        System.out.println("Complete!");
    }

    class CheckBoxCell extends TableCell<VkAudio, Boolean>
    {
        private CheckBox checkbox = new CheckBox();

        public CheckBoxCell()
        {
            checkbox.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    if(!checkbox.isSelected())
                    {
                        downloadQueue.remove(table.getItems().get(getTableRow().getIndex()));
                        VkAudio audio = getTableView().getItems().get(getTableRow().getIndex());
                        audio.setSelect(false);
                    } else
                    {
                        downloadQueue.add(table.getItems().get(getTableRow().getIndex()));
                        VkAudio audio = getTableView().getItems().get(getTableRow().getIndex());
                        audio.setSelect(true);
                    }
                    System.out.println(downloadQueue);
                }
            });
        }

        @Override
        protected void updateItem(Boolean item, boolean empty)
        {
            super.updateItem(item, empty);
            if(!empty && item != null)
            {
                checkbox.setAlignment(Pos.CENTER);
                checkbox.setSelected(item);
                setAlignment(Pos.CENTER);
                setGraphic(checkbox);
            }
        }
    }
}




