package co.mushu.blogging.services;

import co.mushu.blogging.models.UserResponse;
import co.mushu.blogging.models.Users;
import co.mushu.blogging.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    private UsersRepository usersRepository;

    public Users getUserByUsername(String username){
        return usersRepository.findById(username).orElse(null);
    }

    public UserResponse getUserResponseByUsername(String username){
        Users user = usersRepository.findById(username).orElse(null);
        if(user== null) return null;
        return new UserResponse(user.getUsername(),user.getBlogs(),user.getCreateDate(),user.getDateOfBirth(),user.getEmail(),user.getPhoneNumber());
    }


    public boolean checkIfUsernameExist(String username){
        return usersRepository.existsById(username);
    }

}
