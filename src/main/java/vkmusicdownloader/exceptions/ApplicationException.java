package exceptions;

/**
 * Created with IntelliJ IDEA.
 * logic.User: St1ch
 * Date: 21.10.13
 * Time: 15:05
 * Package name: exceptions
 * Project name: VkMusicApplication
 */
public class ApplicationException extends Exception
{
    public ApplicationException()
    {
        super();
    }

    public ApplicationException(String message)
    {
        super(message);
    }

    public ApplicationException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
