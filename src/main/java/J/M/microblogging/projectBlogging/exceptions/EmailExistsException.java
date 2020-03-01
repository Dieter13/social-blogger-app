package J.M.microblogging.projectBlogging.exceptions;

public class EmailExistsException extends Throwable{

    public EmailExistsException(final String message) {
        super(message);
    }
}
