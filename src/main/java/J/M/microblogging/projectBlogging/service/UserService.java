package J.M.microblogging.projectBlogging.service;

import J.M.microblogging.projectBlogging.dao.UserRepository;
import J.M.microblogging.projectBlogging.dto.LogInUserDto;
import J.M.microblogging.projectBlogging.dto.UserDto;
import J.M.microblogging.projectBlogging.exceptions.UserNotFoundException;
import J.M.microblogging.projectBlogging.model.*;
import J.M.microblogging.projectBlogging.exceptions.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public User registerNewUserAccount(LogInUserDto accountDto)
            throws EmailExistsException {

        if (emailExists(accountDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email address:"  + accountDto.getEmail());
        }
        User user = new User();
        user.setAccountName(accountDto.getAccountName());
        user.setAccountNickName(accountDto.getAccountNickName());
        user.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));
        user.setMatchingPasssword(bCryptPasswordEncoder.encode(accountDto.getMatchingPassword()));
        user.setEmail(accountDto.getEmail());
        user.setUserName(accountDto.getUserName());
        user.setAccountStatus(AccountStatus.ACTIVE);
        user.setAccountType(AccountType.PUBLIC);
        List<Role> role = new ArrayList<>();
        role.add(new Role("ROLE_USER"));
        user.setRoles(role);
        return userRepository.save(user);
    }

    @Override
    public User authenticateLoginUser(LogInUserDto loginDto) {
        return null;
    }


    @Override
    public User addFollower(Long userId) {

        User loggedUser = currentUser();
        User followUser = (userRepository.findById(userId)).get();
        loggedUser.getFollowing().add(followUser.getId());
        followUser.getFollowers().add(loggedUser.getId());
        userRepository.save(loggedUser);
        userRepository.save(followUser);
        return loggedUser;

    }
    @Override
    public User removeFollower(Long userId){

        User loggedUser = currentUser();
        User followUser = (userRepository.findById(userId)).get();
        loggedUser.getFollowing().remove(followUser.getId());
        followUser.getFollowers().remove(loggedUser.getId());
        userRepository.save(loggedUser);
        userRepository.save(followUser);
        return loggedUser;
    }
    @Override
    public List<UserDto> getAllUsers() {
            User loggedUser = currentUser();

            return userRepository
                    .findAll()
                    .stream()
                    .filter(u -> !u.getId().equals(loggedUser.getId()))
                    .map(u -> {
                        boolean isFollowing = loggedUser.getFollowing().contains(u.getId());
                        UserDto userDto = new UserDto(u.getId(), u.getUserName(), u.getEmail(), isFollowing);
                        return userDto;
                    })
                    .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
            User user = userRepository.findById(id)
                    .orElseThrow(()-> new UserNotFoundException("Could not find user with "+ id));

            return new UserDto(user.getId(), user.getUserName(), user.getEmail());

        }

    @Override
    public User currentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String userName = ((UserDetails) principal).getUsername();

            if ((userRepository.findByUserName(userName)).isPresent()) {

                return (userRepository.findByUserName(userName)).get();

            }
        }
        return null;
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
