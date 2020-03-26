package J.M.microblogging.projectBlogging.service;

import J.M.microblogging.projectBlogging.dto.LikeDto;
import J.M.microblogging.projectBlogging.model.Like;

public interface ILikeService {

    Like newLike(Long postId);

    Long countLikesForPost(Long id);

}
