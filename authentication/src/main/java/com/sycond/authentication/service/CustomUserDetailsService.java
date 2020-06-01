package com.sycond.authentication.service;

import com.sycond.authentication.entity.security.User;
import com.sycond.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service(value = "userDetailsService")
@Transactional(rollbackFor = Exception.class)
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    Optional<User> get(Long id){
        return userRepository.findById(id);
    }

    Optional<User> getByName(String name){
        return userRepository.findByUsername(name);
    }


    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
        /** Aqui se manejan los filtros de seguridad**/
        User user = getByName(input).orElse(null);
        if(user == null){
            throw new BadCredentialsException("Bad Credentials");
        }
        new AccountStatusUserDetailsChecker().check(user);
        return user;
    }

    public void saveToken(User user, String token){
        userRepository.save(user);
    }
}
