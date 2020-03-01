package J.M.microblogging.projectBlogging.dto;


import J.M.microblogging.projectBlogging.model.Post;
import J.M.microblogging.projectBlogging.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikeDto {

    private Long id;

    private Post post;

    private User user;


}
