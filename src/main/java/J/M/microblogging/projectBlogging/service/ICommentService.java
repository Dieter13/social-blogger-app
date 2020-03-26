package J.M.microblogging.projectBlogging.service;
import J.M.microblogging.projectBlogging.dto.CommentDto;
import J.M.microblogging.projectBlogging.model.Comment;

import java.util.List;


public interface ICommentService {

    Comment registerNewComment(Long id, CommentDto commentDto);

    List<CommentDto> findAllCommentsFromPost(Long id);

}
