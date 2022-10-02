package co.mushu.blogging.services;

import co.mushu.blogging.models.Users;
import co.mushu.blogging.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    private UsersRepository usersRepository;

    public Optional<Users> getUserByUsername(String username){
        return usersRepository.findById(username);
    }

}
