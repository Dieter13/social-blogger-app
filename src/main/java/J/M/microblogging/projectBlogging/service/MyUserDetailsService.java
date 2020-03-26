package J.M.microblogging.projectBlogging.service;

import J.M.microblogging.projectBlogging.dao.UserRepository;
import J.M.microblogging.projectBlogging.model.MyUserDetails;
import J.M.microblogging.projectBlogging.model.Role;
import J.M.microblogging.projectBlogging.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {

        User user = userRepository.findByUserName(userName).get();
        if (user == null) {
            throw new UsernameNotFoundException(
                    "No user found with username: " + userName);
        }

        return  new org.springframework.security.core.userdetails.User
                (user.getUserName(),
                        user.getPassword(),
                        getAuthorities(user.getRoles().stream().map(Role::getName).collect(Collectors.toList())));
    }

    private static List<GrantedAuthority> getAuthorities (List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByUserName(userName);
//
//        user.orElseThrow(()->new UsernameNotFoundException("Not found: " + userName));
//        return user.map(MyUserDetails::new).get();
//    }
}
