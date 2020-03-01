package J.M.microblogging.projectBlogging.dao;

import J.M.microblogging.projectBlogging.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findAllByPostId(Long postId);

}
