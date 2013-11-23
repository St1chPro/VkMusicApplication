package logic;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;

import org.apache.http.client.utils.URIBuilder;

/**
 * Created with IntelliJ IDEA.
 * logic.User: St1ch
 * Date: 20.10.13
 * Time: 14:55
 * Package name: graphics
 * Project name: VkMusicApplication
 */
public class UriCreator
{
    private static final String APP_ID = "3942460";
    private static final String REDIRECT_URI = "https://oauth.vk.com/blank.html";
    private static final String API_VERSION = "5.2";
    private static final String RESPONSE_TYPE = "token";

    private static final String METHOD_CALL_SCHEME = "https";
    private static final String METHOD_CALL_HOST = "api.vk.com";
    private static final String METHOD_CALL_PATH = "/method/audio.get";

    private String accessToken;

    public UriCreator()
    {
    }

    public UriCreator(String accessToken)
    {
        this.accessToken = accessToken;
    }


    public URI buildAuthorizationDialogUri()
    {
        URI oAuthURI = null;
        try
        {
            oAuthURI = new URIBuilder().setScheme("https")
                                       .setHost("oauth.vk.com")
                                       .setPath("/authorize")
                                       .setParameter("client_id", APP_ID)
                                       .setParameter("scope", Permissions.AUDIO.getPermission())
                                       .setParameter("redirect_uri", REDIRECT_URI)
                                       .setParameter("v", API_VERSION)
                                       .setParameter("response_type", RESPONSE_TYPE)
                                       .build();
        } catch(URISyntaxException e)
        {
            System.out
                  .println("Problems with building authorizationDialog URI." + e.getMessage() + " " + e.getReason());
            e.printStackTrace();
        }
        return oAuthURI;
    }

    public URI buildAccessTokenUri(User user, String ipH, String to, String origin, boolean usePhone)
    {
        URI authTokenUri = null;
        String login = user.getEmail();
        String password = user.getPassword();

        if(usePhone)
        {
            login = user.getPhone();
        }

        try
        {
            authTokenUri = new URIBuilder().setScheme("https")
                                           .setHost("login.vk.com/")
                                           .setParameter("act", "login")
                                           .setParameter("soft", "1")
                                           .setParameter("utf8", "1")
                                           .setParameter("q", "1")
                                           .setParameter("ip_h", ipH)
                                           .setParameter("_origin", origin)
                                           .setParameter("to", to)
                                           .setParameter("email", login)
                                           .setParameter("pass", password)
                                           .build();

            authTokenUri = new URI(URLDecoder.decode(authTokenUri.toString(), "UTF-8"));
        } catch(URISyntaxException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return authTokenUri;
    }

    /**
     * @param ownerId vk user id
     * @param count   number of audio objects obtaining
     */
    public URI buildAudioGetUri(String ownerId, int count)
    {

        //ex    https://api.vk.com/method/users.get?user_id=66748&v=5.2&access_token=533bacf01e11f55b536a565b57531ac114461ae8736d6506a3
        //      https://api.vk.com/method/'''METHOD_NAME'''?'''PARAMETERS'''&access_token='''ACCESS_TOKEN'''
        URI audioGetUri = null;

        try
        {
            audioGetUri = new URIBuilder().setScheme(METHOD_CALL_SCHEME)
                                          .setHost(METHOD_CALL_HOST)
                                          .setPath(METHOD_CALL_PATH)
                                          .setParameter("owner_id", ownerId)
                                          .setParameter("count", String.valueOf(count))
                                          .setParameter("access_token", accessToken)
                                          .build();
        } catch(URISyntaxException e)
        {
            System.out.println("Problems with building audioGet URI." + e.getMessage() + " " + e.getReason());
            e.printStackTrace();
        }
        System.out.println(audioGetUri.toString());
        return audioGetUri;
    }


}
