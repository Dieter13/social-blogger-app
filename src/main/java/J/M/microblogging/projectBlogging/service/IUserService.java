package J.M.microblogging.projectBlogging.service;

import J.M.microblogging.projectBlogging.dto.LogInUserDto;
import J.M.microblogging.projectBlogging.dto.UserDto;
import J.M.microblogging.projectBlogging.model.User;
import J.M.microblogging.projectBlogging.exceptions.EmailExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {

    User registerNewUserAccount(LogInUserDto accountDto) throws EmailExistsException;

    User authenticateLoginUser(LogInUserDto loginDto);

    User addFollower(Long userId);

    User removeFollower(Long userId);

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    User currentUser();

}
