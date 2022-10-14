package co.mushu.blogging.Controllers;


import co.mushu.blogging.models.Blog;
import co.mushu.blogging.models.BlogResponse;
import co.mushu.blogging.models.Users;
import co.mushu.blogging.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/user")
public class UsersController {

    @Autowired
    private UserServices userServices;

    @RequestMapping(value="/{userId}/blogs")
    public ResponseEntity<?> userBlogs(@PathVariable String userId){
        Users users = userServices.getUserByUsername(userId);
        if(users == null) return ResponseEntity.ok().body("Null user");
        List<BlogResponse> blogResponses = new ArrayList<>();
        for(Blog blog : users.getBlogs()){
            blogResponses.add(new BlogResponse(userId,blog.getBlogId(), blog.getSubject(),blog.getContent(), blog.getLikes()));
        }
        return ResponseEntity.ok().body(blogResponses);
    }
}
