package org.example.springboot.ada2_0.Services;

import org.example.springboot.ada2_0.Dao.UserRepository;
import org.example.springboot.ada2_0.Dto.RegistryUser;
import org.example.springboot.ada2_0.Entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistryUserHandler {
    @Autowired
    GroupHandler groupHandler;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;



    public MyUser userHandlerProc(RegistryUser userreg) {
        MyUser user = new MyUser();
        if (userRepository.findByUsername(userreg.getUsername()).isEmpty()) {
            Optional<Integer> groupIdOptional = groupHandler.getGroupId(userreg);
            if (groupIdOptional.isPresent()) {
                user.setPassword(bCryptPasswordEncoder.encode(userreg.getPassword()));
                user.setUsername(userreg.getUsername());
                user.setGroup_id(groupIdOptional.get()); // Используем полученный groupId
                return user;
            } else {
                throw new GroupNotFoundException("Группа с именем '" + userreg.getPg_password() + "' не найдена.");
            }
        } else {
            throw new UsernameAlreadyExistsException("Пользователь с таким именем уже существует.");
        }
    }
    public class GroupNotFoundException extends RuntimeException {
        public GroupNotFoundException(String message) {
            super(message);
        }
    }

    public class UsernameAlreadyExistsException extends RuntimeException{
        public UsernameAlreadyExistsException(String message){
            super(message);
        }
    }


}
