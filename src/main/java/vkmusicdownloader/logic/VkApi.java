package logic;

import com.google.gson.*;
import exceptions.UtilException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * logic.User: St1ch
 * Date: 20.10.13
 * Time: 20:48
 * Package name: PACKAGE_NAME
 * Project name: VkMusicApplication
 */

/**
 * Class contains API methods vk.com
 */
public class VkApi
{

    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private VkAccessToken accessToken;
    private UriCreator uriCreator;
    private String userId;
    private Utils utils;
    private Gson gson;

    public VkApi(VkAccessToken accessToken)
    {
        this.accessToken = accessToken;
        this.uriCreator = new UriCreator(accessToken.getAccessToken());
        this.userId = accessToken.getUserId();
        this.utils = new Utils();
        this.gson = new Gson();
    }

    /**
     * This method returns List of objects VkAudio
     *
     * @param numberOfRecords how many records will be received
     */
    public List<VkAudio> getAudioList(int numberOfRecords)
    {
        // имя массива в json объекте
        String responseArrayName = "response";

        List<VkAudio> audioList = null;

        URI audioListUri = uriCreator.buildAudioGetUri(userId, numberOfRecords);
        HttpPost audioPost = new HttpPost(audioListUri);
        HttpEntity audioEntity;
        String audioContent;

        JsonObject responseJsonObject;
        JsonArray jsonArrayOfVkAudio;

        try(CloseableHttpResponse audioResponse = httpClient.execute(audioPost))
        {
            // получение сущности и контента из respons'a
            audioEntity = audioResponse.getEntity();
            audioContent = utils.getContent(audioEntity);

            // получаем json объект из контента, а затем из объекта получаем json массив(name == "response"), содержащий vkAudio
            responseJsonObject = gson.fromJson(audioContent, JsonObject.class);
            jsonArrayOfVkAudio = responseJsonObject.getAsJsonArray(responseArrayName);

            // заполняем list объектами VkAudio, вытягивая их из json массива
            // начинаем с 1 т.к. в 0-м элементе кол-во аудиозаписей user'a(int)
            audioList = new ArrayList<>(jsonArrayOfVkAudio.get(0).getAsInt());
            VkAudio record;
            for(int i = 1; i < jsonArrayOfVkAudio.size(); i++)
            {
                record = gson.fromJson(jsonArrayOfVkAudio.get(i), VkAudio.class);
                audioList.add(record);
            }
        } catch(IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch(UtilException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
//        ArrayList<VkAudio> audioArrayList = new ArrayList<>(audioList);
        return audioList;
    }



    public void downloadFile(String url, String path)
    {
        HttpGet fileQuery = new HttpGet(url);
        HttpResponse response = null;
        try
        {
            response = httpClient.execute(fileQuery);
        } catch(IOException e)
        {
            System.out.println("Error occured when query executes");
        }
        HttpEntity fileEntity = response.getEntity();

        try(InputStream fileInput = new BufferedInputStream(fileEntity.getContent());
                OutputStream fileOutput = new BufferedOutputStream(new FileOutputStream(path)))
        {
            int currentByte;
            while((currentByte = fileInput.read()) != -1)
            {
                fileOutput.write(currentByte);
            }
            fileOutput.flush();
        } catch(IOException e)
        {
            System.out.println("Something wrong in 'downloadFile' method");
        }
    }
}
