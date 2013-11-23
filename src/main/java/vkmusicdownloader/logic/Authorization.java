package logic;

import exceptions.AuthorizationException;
import exceptions.ParserException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.URI;

/**
 * Created with IntelliJ IDEA.
 * logic.User: St1ch
 * Date: 20.10.13
 * Time: 20:47
 * Package name: PACKAGE_NAME
 * Project name: VkMusicApplication
 */

/**
 * Класс Authorization содержит поля и методы, при помощи
 * Которых будет получен accessToken, нужный в будущем для вызова методов vk api
 * Конструктор класса принимает только один параметр - user типа User
 * На основе этого user'a строятся запросы на сервер vk
 */

public class Authorization
{
    // создаётся httpclient для выполнения http запросов
    private CloseableHttpClient httpClient = HttpClients.createDefault();

    //поля, нужные для построения запроса и последующего получения accessToken
    private String ipH;
    private String to;
    private String origin;

    private UriCreator uriCreator;

    private VkAccessToken accessToken;

    private HttpEntity authorizationDialogEntity;
    private String authorizationContent;

    private URI accessTokenUri;

    public Authorization(User user)
    {
        this.uriCreator = new UriCreator();

        this.authorizationDialogEntity = getHttpEntity(uriCreator.buildAuthorizationDialogUri());
        this.authorizationContent = getHttpContent(authorizationDialogEntity);

        this.ipH = getValue("ip_h", authorizationContent);
        this.to = getValue("to", authorizationContent);
        this.origin = getValue("origin", authorizationContent);
        this.accessTokenUri = uriCreator.buildAccessTokenUri(user, ipH, to, origin, user.isPhoneUsed());

        this.accessToken = createAccessToken(accessTokenUri);
    }

    // метод для получения сущностей из которых будет получен контент
    // принимает на вход URI по которому создаёт get запрос
    private HttpEntity getHttpEntity(URI entityUri)
    {
        HttpEntity authEntity = null;
        HttpPost entityQuery = new HttpPost(entityUri);
        try(CloseableHttpResponse authResponse = httpClient.execute(entityQuery))
        {
            authEntity = authResponse.getEntity();

        } catch(ClientProtocolException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch(IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return authEntity;
    }

    // метод, который по сущности возвращает контент(html)
    private String getHttpContent(HttpEntity httpEntity)
    {
        String httpContent = "";
        try(BufferedReader contentReader = new BufferedReader(new InputStreamReader(httpEntity.getContent())))
        {
            String currentLine;
            while((currentLine = contentReader.readLine()) != null)
            {
                httpContent += currentLine;
            }
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        return httpContent;
    }

    //метод, который вытаскивает из контента значения по ключу
    //принимает на вход ключ(то есть, ЧТО искать) и контент(то есть строку, в КОТОРОЙ искать)
    private String getValue(String key, String content)
    {
        String value = null;
        Parser contentParser = new Parser(content);
        try
        {
            switch(key)
            {
                case "ip_h":
                case "to":
                case "origin":
                    value = contentParser.parseAuthorizationContent(key);
                    break;
                case "access_token":
                case "user_id":
                case "expires_in":
                    value = contentParser.parseAccessTokenContent(key);
                    break;
                case "action":
                    value = contentParser.parseActionRedirect(key);
                    break;
            }
        } catch(ParserException e)
        {
            System.out.println("Error occurred while calling method 'getValue");
        }
        return value;
    }

    private VkAccessToken createAccessToken(URI accessTokenURI)
    {
        String accessToken = null;
        String userID = null;
        String timeLeft = null;

        HttpPost accessTokenQuery;
        HttpResponse accessTokenResponse;
        String accessTokenRedirect;

        HttpPost actionQuery;
        HttpResponse actionResponse;
        String actionContent;

        HttpPost acceptForm;
        HttpResponse acceptResponse;
        String acceptRedirect;

        String accessTokenString;

        try
        {
            accessTokenQuery = new HttpPost(accessTokenURI);
            accessTokenResponse = httpClient.execute(accessTokenQuery);
            accessTokenRedirect = accessTokenResponse.getFirstHeader("location").getValue();

            actionQuery = new HttpPost(accessTokenRedirect);
            actionResponse = httpClient.execute(actionQuery);
            actionContent = getHttpContent(actionResponse.getEntity());

            if(actionResponse.containsHeader("location"))
            {
                acceptRedirect = actionResponse.getFirstHeader("location").getValue();
            } else
            {
                acceptRedirect = getValue("action", actionContent);
            }

            acceptForm = new HttpPost(acceptRedirect);
            acceptResponse = httpClient.execute(acceptForm);
            accessTokenString = acceptResponse.getFirstHeader("location").getValue();

            accessToken = getValue("access_token", accessTokenString);
            userID = getValue("user_id", accessTokenString);
            timeLeft = getValue("expires_in", accessTokenString);
        } catch(IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return new VkAccessToken(accessToken, timeLeft, userID);
    }

    public VkAccessToken getAccessToken()
    {
        return accessToken;
    }
}

