package org.example.springboot.ada2_0.Services;

import org.example.springboot.ada2_0.Dao.UserRepository;
import org.example.springboot.ada2_0.Entity.MyUser;
import org.example.springboot.ada2_0.Configuration.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserHandler implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            MyUser myUser = user.get();
            return new CustomUserDetails(myUser.getUsername(), myUser.getPassword(), myUser.getGroup());
        } else {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }
    public void saveUser(MyUser myUser){
        userRepository.save(myUser);
    }
}
