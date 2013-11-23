package logic;

/**
 * Created with IntelliJ IDEA.
 * logic.User: St1ch
 * Date: 20.10.13
 * Time: 22:03
 * Package name: PACKAGE_NAME
 * Project name: VkMusicApplication
 */
public class VkAccessToken
{
    private String accessToken;
    private String timeLeft;
    private String userID;

    public VkAccessToken(String accessToken, String timeLeft, String userID)
    {
        this.accessToken = accessToken;
        this.timeLeft = timeLeft;
        this.userID = userID;
    }

    public String getAccessToken()
    {
        return accessToken;
    }

    public String getTimeLeft()
    {
        return timeLeft;
    }

    public String getUserId()
    {
        return userID;
    }
}
