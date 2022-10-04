package co.mushu.blogging.Controllers;

import co.mushu.blogging.models.AuthenticationRequest;
import co.mushu.blogging.models.AuthenticationResponse;
import co.mushu.blogging.models.Users;
import co.mushu.blogging.repositories.UsersRepository;
import co.mushu.blogging.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@CrossOrigin(origins = {"https://**" , "http://**" , "http://localhost:4200"} , methods = {RequestMethod.GET, RequestMethod.POST})
public class SecurityController {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/login", method = RequestMethod.GET)

    private ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        System.out.println("The login request has came for the user "+authenticationRequest.getUsername()+" with password "+authenticationRequest.getPassword());
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
        }catch(BadCredentialsException e){
            throw new Exception("Incorrect Username or Password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


    @RequestMapping("/kuchbhi")
    public ResponseEntity<?> kuchbhi(@RequestParam(defaultValue = "metha") String name){
        return ResponseEntity.ok("Bhai Kuch Bhiii "+name);
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ResponseEntity<?> signIn(@RequestBody Users users){
        final String username = users.getUsername();
        usersRepository.save(users);
        if(!usersRepository.existsById(username)) return ResponseEntity.badRequest().body("Please Try Again");
        return ResponseEntity.ok().body("User Has Been Created Kindly Login");
    }

    @RequestMapping(value = "/signIn/")
    public ResponseEntity<?> checkUserName(@RequestParam String username){
        if(usersRepository.existsById(username)) return ResponseEntity.badRequest().body("Username Already Exist Try Another Name");
        return ResponseEntity.ok().body("Username is Valid");
    }



}
