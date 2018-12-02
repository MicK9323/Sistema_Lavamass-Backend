package com.mca.apps.lavamassapirest.security;

import com.mca.apps.lavamassapirest.models.model.UserDTO;
import com.mca.apps.lavamassapirest.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String dniOrEmail) throws UsernameNotFoundException {
        UserDTO user = userRepository.findByDniOrEmail(dniOrEmail, dniOrEmail)
                .orElseThrow(() ->
                            new UsernameNotFoundException("user not found with dni or email: "+dniOrEmail)
                        );
        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(int id) {
        UserDTO user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return UserPrincipal.create(user);
    }
}
