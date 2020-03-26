package J.M.microblogging.projectBlogging.exceptions;


public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {}

    public UserNotFoundException(final String message) {
        super(message);
    }

}

