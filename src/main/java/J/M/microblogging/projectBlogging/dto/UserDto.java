package J.M.microblogging.projectBlogging.dto;

import J.M.microblogging.projectBlogging.validation.PasswordMatches;
import J.M.microblogging.projectBlogging.validation.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@PasswordMatches
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    @NotNull
    @NotBlank
    private Long id;


    @NotNull
    @NotBlank
    private String userName;


    @ValidEmail
    @NotNull
    @NotBlank
    private String email;

    private boolean followed;

    public UserDto(Long id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }
}
