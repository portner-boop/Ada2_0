package org.example.springboot.ada2_0.Services;

import org.example.springboot.ada2_0.Dao.UserRepository;
import org.example.springboot.ada2_0.Entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
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
        if(user.isPresent()){
            var myUser = user.get();
            return User.builder().username(myUser.getPassword())
                    .password(myUser.getPassword())
                    .build();
        }else{
            throw new UsernameNotFoundException(username);
        }
    }
    public void saveUser(MyUser myUser){
        userRepository.save(myUser);
    }
}
