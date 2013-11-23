package exceptions;

/**
 * Created with IntelliJ IDEA.
 * logic.User: St1ch
 * Date: 21.10.13
 * Time: 15:15
 * Package name: exceptions
 * Project name: VkMusicApplication
 */
public class AuthorizationException extends ApplicationException
{
    public AuthorizationException()
    {
        super();
    }

    public AuthorizationException(String message)
    {
        super(message);
    }

    public AuthorizationException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
