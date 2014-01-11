package logic;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;
import exceptions.UtilException;
import org.apache.http.HttpEntity;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * logic.User: St1ch
 * Date: 31.10.13
 * Time: 17:48
 * Package name: PACKAGE_NAME
 * Project name: VkMusicApplication
 */
public class Utils
{
    // метод, который по сущности возвращает контент(html)
    public static String getContent(HttpEntity httpEntity) throws UtilException
    {
        if(httpEntity == null)
        {
            throw new UtilException("Something wrong in getContent method, httpEntity == null");
        }
        StringBuilder httpContent = new StringBuilder();
        try(BufferedReader contentReader = new BufferedReader(new InputStreamReader(httpEntity.getContent())))
        {
            String currentLine;
            while((currentLine = contentReader.readLine()) != null)
            {
                httpContent.append(currentLine);
            }
        } catch(IOException e)
        {
            throw new UtilException("Something wrong in getContent method", e.getCause());
        }
        return httpContent.toString();
    }

    public static boolean isPhone(String unknown)
    {
        Pattern pattern = Pattern.compile("^\\+[0-9]*");
        return pattern.matcher(unknown).matches();
    }

    public static void removeTagsFromSong(String pathTofile)
    {
        String newPathToFile = pathTofile + " ";
        try
        {
            Mp3File mp3file = new Mp3File(pathTofile);

            if(mp3file.hasId3v1Tag())
            {
                mp3file.removeId3v1Tag();
            }
            if(mp3file.hasId3v2Tag())
            {
                mp3file.removeId3v2Tag();
            }
            if(mp3file.hasCustomTag())
            {
                mp3file.removeCustomTag();
            }
            mp3file.save(newPathToFile/*pathTofile.substring(0, pathTofile.length() - 4) + "(without tags).mp3"*/);

        } catch(IOException | UnsupportedTagException | InvalidDataException | NotSupportedException e)
        {
            e.printStackTrace();
        }
//        song.delete();

    }
}
