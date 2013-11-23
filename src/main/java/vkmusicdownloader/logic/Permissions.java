package logic;

/**
 * Created with IntelliJ IDEA.
 * logic.User: St1ch
 * Date: 20.10.13
 * Time: 20:57
 * Package name: graphics
 * Project name: VkMusicApplication
 */
public enum Permissions
{
    FRIENDS("friends"), PHOTOS("photos"), AUDIO("audio"), VIDEO("video"), GROUPS("groups");

    private String permission;

    private Permissions(String permission)
    {
        this.permission = permission;
    }

    public String getPermission()
    {
        return permission;
    }
}
