package co.mushu.blogging;

import co.mushu.blogging.entities.Users;
import co.mushu.blogging.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsServices implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = usersRepository.findById(username);
        if(user != null){
            MyUserDetails userDetails = user.map(MyUserDetails::new).get();
            System.out.println("The User is Found "+userDetails.getUsername()+" and "+userDetails.getPassword()+" and "+userDetails.isEnabled());
        }
        else System.out.println("No User Found");
        user.orElseThrow(()->new UsernameNotFoundException("Not Found User "+username));
        return user.map(MyUserDetails::new).get();
    }
}
