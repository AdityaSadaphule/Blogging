package co.mushu.blogging.Controllers;


import co.mushu.blogging.entities.UserProfile;
import co.mushu.blogging.repositories.UserProfileRepository;
import co.mushu.blogging.services.UserProfileServices;
import co.mushu.blogging.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserProfileController {

    @Autowired
    private UserProfileServices userProfileServices;


    @RequestMapping(value="/{userId}/blogs")
    public ResponseEntity<?> userBlogs(@PathVariable String userId){
        UserProfile user = userProfileServices.getUserProfileByUsername(userId);
        if(user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        return ResponseEntity.ok().body(user.getBlogs());
    }

    @RequestMapping(value="/{userId}")
    public ResponseEntity<?> userProfileDetails(@PathVariable String userId){
        UserProfile user = userProfileServices.getUserProfileByUsername(userId);
        if(user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        return ResponseEntity.ok().body(user);
    }

}
