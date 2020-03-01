package J.M.microblogging.projectBlogging.dao;

import J.M.microblogging.projectBlogging.dto.PostDto;
import J.M.microblogging.projectBlogging.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    List<PostDto> findAllPostsOfLoggedUser(Long id);
    Long deleteByAccountName(String accountName);
    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);

}
