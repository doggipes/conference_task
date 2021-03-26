package ru.waveaccess.conference.security.details;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.waveaccess.conference.model.entity.User;
import ru.waveaccess.conference.repository.UserRepository;

import java.util.Optional;

@Service(value = "customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionUser = userRepository.findUserByEmail(email);
        if (optionUser.isPresent()) {
            return new UserDetailsImpl(optionUser.get());
        }
        throw new UsernameNotFoundException("User not found");
    }
}
