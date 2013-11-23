package exceptions;

/**
 * Created with IntelliJ IDEA.
 * logic.User: St1ch
 * Date: 31.10.13
 * Time: 17:58
 * Package name: exceptions
 * Project name: VkMusicApplication
 */
public class UtilException extends ApplicationException
{
    public UtilException()
    {
        super();
    }

    public UtilException(String message)
    {
        super(message);
    }

    public UtilException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
