package co.mushu.blogging.services;

import co.mushu.blogging.entities.UserProfile;
import co.mushu.blogging.entities.Users;
import co.mushu.blogging.repositories.UserProfileRepository;
import co.mushu.blogging.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public Users getUserByUsername(String username){
        return usersRepository.findById(username).orElse(null);
    }

    public boolean checkIfUsernameExist(String username){
        return userProfileRepository.existsById(username);
    }

}
