package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserDetails userDetails;
        Optional<User> user = userRepository.getUserByName(name);
        if (user.isPresent()) {
            Set<GrantedAuthority> roles = new HashSet<>();
            String str = user.get().getRole().getName();
            roles.add(new SimpleGrantedAuthority(str));
            userDetails = new org.springframework.security.core.userdetails
                    .User(user.get().getName(), user.get().getPassword(), roles);
        } else {
            throw new UsernameNotFoundException("User wasn't found");
        }
        return userDetails;
    }
}
