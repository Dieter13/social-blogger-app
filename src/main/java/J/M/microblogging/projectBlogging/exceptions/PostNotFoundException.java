package J.M.microblogging.projectBlogging.exceptions;

public class PostNotFoundException extends RuntimeException{

    public PostNotFoundException() { }

    public PostNotFoundException(final String message) { super(message); }

}
