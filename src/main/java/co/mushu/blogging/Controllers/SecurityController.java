package co.mushu.blogging.Controllers;
import co.mushu.blogging.entities.UserProfile;
import co.mushu.blogging.entities.Users;
import co.mushu.blogging.models.*;
import co.mushu.blogging.repositories.UserProfileRepository;
import co.mushu.blogging.repositories.UsersRepository;
import co.mushu.blogging.utility.ConditionalUtility;
import co.mushu.blogging.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@RestController
@CrossOrigin(origins = {"https://**" , "http://**" , "http://localhost:4200"} , methods = {RequestMethod.GET, RequestMethod.POST})
public class SecurityController {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ConditionalUtility conditionalUtility;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        System.out.println("The login request has came for the user "+authenticationRequest.getUsername()+" with password "+authenticationRequest.getPassword());
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
        }catch(BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credentials Invalid Please Try Again.");
        }catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong please try again");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @RequestMapping("/kuchbhi")
    public ResponseEntity<?> kuchbhi(@RequestParam(defaultValue = "metha") String name){
        return ResponseEntity.ok("Bhai Kuch Bhiii "+name);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> signIn(@RequestBody UserCreation userCreation){
        final String username = userCreation.getUsername();
        final String password = userCreation.getPassword();
        final Date dateOfBirth = userCreation.getDateOfBirth();
        final String email = userCreation.getEmail();
        final String phone = userCreation.getPhoneNumber();
        if(username.toLowerCase().equals("null") || !conditionalUtility.isValidUsername(username)) return ResponseEntity.badRequest().body("Invalid Username");
        if(usersRepository.existsById(username)) return ResponseEntity.badRequest().body("Username Already Taken");
        String validPW = conditionalUtility.isPasswordValid(password);
        if(!validPW.equals("valid")) return ResponseEntity.badRequest().body(validPW);
        final Users user = new Users(username,password, Calendar.getInstance(TimeZone.getDefault()).getTime(),dateOfBirth,true,"USER",email,phone);
        final UserProfile userProfile = new UserProfile(username,Calendar.getInstance(TimeZone.getDefault()).getTime(),dateOfBirth,email,phone);
        usersRepository.save(user);
        userProfileRepository.save(userProfile);
        if(!usersRepository.existsById(username)) return ResponseEntity.badRequest().body("Kindly try again after sometime");
        return ResponseEntity.ok().body("User Has Been Created Kindly Login");
    }

    @RequestMapping(value = "/register/")
    public ResponseEntity<?> checkUserName(@RequestParam String username){
        if(usersRepository.existsById(username)) return ResponseEntity.badRequest().body("Username Already Exist Try Another Name");
        return ResponseEntity.ok().body("Username is Valid");
    }



}
