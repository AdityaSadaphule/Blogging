package co.mushu.blogging.Controllers;

import co.mushu.blogging.entities.Blog;
import co.mushu.blogging.entities.UserProfile;
import co.mushu.blogging.models.BlogCreation;
import co.mushu.blogging.services.BlogServices;
import co.mushu.blogging.services.UserProfileServices;
import co.mushu.blogging.services.UserServices;
import co.mushu.blogging.utility.ConditionalUtility;
import co.mushu.blogging.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.ZoneId;
import java.util.*;


@RestController
@RequestMapping("/blog")
public class BlogsController {

    @Autowired
    private BlogServices blogServices;
    @Autowired
    private UserServices userServices;
    @Autowired
    private UserProfileServices userProfileServices;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ConditionalUtility conditionalUtility;

    @RequestMapping(method = RequestMethod.GET)
    public List<Blog> getBlogs(){
        return blogServices.getBlogs();
    }

    @RequestMapping(method = RequestMethod.POST, value="/createBlog")
    public ResponseEntity<?> createBlog(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt, @RequestBody BlogCreation blogCreation){
        String username = blogCreation.getUsername();
        UserProfile user = userProfileServices.getUserProfileByUsername(username);
        if(user ==  null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username "+username+" not found.");
        String jwtUser = jwtUtil.extractUserName(jwt.substring(7));
        if(!jwtUser.equals(username)) return ResponseEntity.badRequest().body("Logged in from "+jwtUser+" account, cannot create blogs in "+username+"'s account");
        Date date = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of("Etc/UTC"))).getTime();
        if(user.getLastBlogCreationTime() != null){
            long userTime = user.getLastBlogCreationTime().getTime() + 600000;
            long currentTime = date.getTime();
            long diff = userTime - currentTime;
            System.out.println(diff);
            long min = diff/(1000*60);
            long sec = diff/(1000);
            if(diff>0) return ResponseEntity.status(HttpStatus.LOCKED).body("The user has to wait for "+min+" minutes and "+(sec%60)+" seconds");
        }
        String blogId = conditionalUtility.generateBlogId(username,blogCreation.getSubject());
        while(blogServices.blogIdPresent(blogId)){
            blogId = conditionalUtility.generateBlogId(username,blogCreation.getSubject());
            date = Calendar.getInstance().getTime();
        }
        Blog blog = new Blog(blogId,user,blogCreation.getContent(),blogCreation.getSubject(),date,0L);
        user.setLastBlogCreationTime(date);
        if(blogServices.createBlog(blog)) return ResponseEntity.status(HttpStatus.CREATED).body("The Blog has been created with Id "+blogId);
        return ResponseEntity.badRequest().body("Something went wrong kindly try again");
    }

    @RequestMapping(value="/{blogId}",method = RequestMethod.GET)
    public ResponseEntity<?> getBlogById(@PathVariable String blogId){
        Blog blog = blogServices.getBlogById(blogId);
        return ResponseEntity.ok().body(blog);
    }


}
