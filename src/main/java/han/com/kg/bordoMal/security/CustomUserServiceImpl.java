package han.com.kg.bordoMal.security;

import han.com.kg.bordoMal.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    public CustomUserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username).map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
