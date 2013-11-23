package exceptions;

/**
 * Created with IntelliJ IDEA.
 * logic.User: St1ch
 * Date: 21.10.13
 * Time: 15:19
 * Package name: exceptions
 * Project name: VkMusicApplication
 */
public class URICreatorException extends ApplicationException
{
    public URICreatorException()
    {
        super();
    }

    public URICreatorException(String message)
    {
        super(message);
    }

    public URICreatorException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
