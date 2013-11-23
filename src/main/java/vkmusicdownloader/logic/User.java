package logic;

/**
 * Created with IntelliJ IDEA.
 * logic.User: St1ch
 * Date: 21.10.13
 * Time: 16:10
 * Package name: PACKAGE_NAME
 * Project name: VkMusicApplication
 */
public class User
{
    private String email;
    private String phone;
    private String password;

    private boolean isPhoneUsed;

    public User()
    {
    }

    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public void createByPhone(String phone, String password)
    {
        this.phone = phone;
        this.password = password;
        this.isPhoneUsed = true;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getPassword()
    {
        return password;
    }

    public boolean isPhoneUsed()
    {
        return isPhoneUsed;
    }
}
