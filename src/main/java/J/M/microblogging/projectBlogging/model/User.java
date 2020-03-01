package J.M.microblogging.projectBlogging.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;


@Getter
@Setter
@Entity
public final class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 3, max = 200)
    private String userName;

//    private String firstName;
//
//    private String lastName;

    private String email;

    private String password;

    private String matchingPasssword;


    private boolean active;

    @Size(min= 3, max= 20)
    private String accountName;


    @Size(min= 3, max= 20)
    private String accountNickName;

    private LocalDateTime acccountCreationDate = LocalDateTime.now();


    private AccountStatus accountStatus;

    private AccountType accountType;



    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "following", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "following_id")
    private Set<Long> following = new LinkedHashSet<>();

    @ElementCollection
    @CollectionTable(name = "followers", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "follower_id")
    private Set<Long> followers = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.ALL})
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.ALL})
    private List<Post> posts = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.ALL})
    private List<Like> likes= new ArrayList<>();

    public User(){};
    public User(String email, String password, String accountName,
                String accountNickName, AccountStatus accountStatus, AccountType accountType){
        this.userName = userName;
        this.password = password;
        this.accountName = accountName;
        this.accountNickName = accountNickName;
        this.accountStatus = accountStatus;
        this.accountType = accountType;

    }

}
