package logic;

import com.sun.corba.se.impl.logging.UtilSystemException;
import exceptions.UtilException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
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
    public String getContent(HttpEntity httpEntity) throws UtilException
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

    public boolean isPhone(String unknown)
    {
        Pattern pattern = Pattern.compile("^\\+[0-9]*");
        return pattern.matcher(unknown).matches();
    }
}
