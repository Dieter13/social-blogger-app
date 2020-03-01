package J.M.microblogging.projectBlogging.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@Getter
@Entity
public final class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    @NotBlank
    @Size(max = 160)
    private String message;

    @Setter
    private LocalDateTime creationDate = LocalDateTime.now();

    @Setter
    private PostStatus postStatus;

    @Setter
    private PostType postType;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private List<Like> likes = new ArrayList<>();



    public Post() {}

    ;

    public Post(String message) {

        this.message = message;
    }

    public Post(String message, PostStatus postStatus, PostType postType, User user) {

        this.message = message;
        this.postStatus = postStatus;
        this.postType = postType;
        this.user = user;

    }
}


