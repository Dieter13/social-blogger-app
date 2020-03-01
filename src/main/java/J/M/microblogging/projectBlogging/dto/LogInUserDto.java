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
public class LogInUserDto {

    @NotNull
    @NotBlank
    private String accountName;

    @NotNull
    @NotBlank
    private String accountNickName;

    @NotNull
    @NotBlank
    private String userName;

    @NotNull
    @NotBlank
    private String password;
    private String matchingPassword;

    @ValidEmail
    @NotNull
    @NotBlank
    private String email;


}
