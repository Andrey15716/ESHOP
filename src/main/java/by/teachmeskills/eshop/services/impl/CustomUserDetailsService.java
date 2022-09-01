package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.entities.CustomUserDetails;
import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userEntity = userService.findByLogin(username);
        return userEntity.map(CustomUserDetails::fromUserEntityToCustomUserDetails).orElse(null);
    }
}
