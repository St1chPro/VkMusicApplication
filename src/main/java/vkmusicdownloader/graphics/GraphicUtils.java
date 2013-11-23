package graphics;

import javafx.stage.Stage;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * logic.User: St1ch
 * Date: 02.11.13
 * Time: 21:39
 * Package name: graphics
 * Project name: VkMusicApplication
 */
public class GraphicUtils
{
    public static void setCentralPosition(Stage stage)
    {
        Dimension sises = Toolkit.getDefaultToolkit().getScreenSize();
        double width = sises.getWidth();
        double height = sises.getHeight();

        double xPosition = (width / 2) - (stage.getWidth() / 2);
        double yPosition = (height / 2) - (stage.getHeight() / 2);

        stage.setX(xPosition);
        stage.setY(yPosition);
    }
}
