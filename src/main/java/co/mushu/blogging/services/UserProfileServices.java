package co.mushu.blogging.services;

import co.mushu.blogging.entities.UserProfile;
import co.mushu.blogging.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServices {

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile getUserProfileByUsername(String username){
        return userProfileRepository.findById(username).orElse(null);
    }



}
