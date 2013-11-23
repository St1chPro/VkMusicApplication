package exceptions;

/**
 * Created with IntelliJ IDEA.
 * logic.User: St1ch
 * Date: 21.10.13
 * Time: 15:16
 * Package name: exceptions
 * Project name: VkMusicApplication
 */
public class ParserException extends ApplicationException
{
    public ParserException()
    {
        super();
    }

    public ParserException(String message)
    {
        super(message);
    }

    public ParserException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
