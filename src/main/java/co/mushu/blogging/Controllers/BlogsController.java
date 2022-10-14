package co.mushu.blogging.Controllers;

import co.mushu.blogging.models.Blog;
import co.mushu.blogging.models.BlogCreation;
import co.mushu.blogging.models.BlogResponse;
import co.mushu.blogging.models.Users;
import co.mushu.blogging.services.BlogServices;
import co.mushu.blogging.services.UserServices;
import co.mushu.blogging.utility.ConditionalUtility;
import co.mushu.blogging.utility.JwtUtil;
import org.hibernate.annotations.LazyCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.ZoneId;
import java.util.*;


@RestController
@RequestMapping(value="/blog")
public class BlogsController {

    @Autowired
    private BlogServices blogServices;
    @Autowired
    private UserServices userServices;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ConditionalUtility conditionalUtility;

    @RequestMapping(method = RequestMethod.GET, value="/all")
    public List<BlogResponse> getBlogs(){
        return blogServices.getBlogs();
    }

    @RequestMapping(method = RequestMethod.POST, value="/createBlog")
    public ResponseEntity<?> createBlog(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt, @RequestBody BlogCreation blogCreation){
        Users users = userServices.getUserByUsername(blogCreation.getUsername());
        String jwtUser = jwtUtil.extractUserName(jwt.substring(7));
        if(!jwtUser.equals(users.getUsername())) return ResponseEntity.badRequest().body("Logged in from "+jwtUser+" account, cannot create blogs in "+blogCreation.getUsername()+"'s account");
        Date date = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of("Etc/UTC"))).getTime();
        if(users.getLastBlogCreationTime() != null){
            long userTime = users.getLastBlogCreationTime().getTime() + 600000;
            long currentTime = date.getTime();
            long diff = userTime - currentTime;
            System.out.println(diff);
            long min = diff/(1000*60);
            long sec = diff/(1000);
            if(diff>0) return ResponseEntity.badRequest().body("The user has to wait for "+min+" minutes and "+(sec%60)+" seconds");
        }
        String blogId = conditionalUtility.generateBlogId(blogCreation.getUsername(),blogCreation.getSubject());
        while(blogServices.blogIdPresent(blogId)){
            blogId = conditionalUtility.generateBlogId(blogCreation.getUsername(),blogCreation.getSubject());
            date = Calendar.getInstance().getTime();
        }
        Blog blog = new Blog(blogId,users,blogCreation.getContent(),blogCreation.getSubject(),date,0L);
        users.setLastBlogCreationTime(date);
        if(blogServices.createBlog(blog)) return ResponseEntity.ok().body("The Blog has been created with Id "+blogId);
        return ResponseEntity.badRequest().body("Something went wrong kindly try again");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getBlogById(@RequestParam String blogId){
        BlogResponse blog = blogServices.getBlogById(blogId);
        return ResponseEntity.ok().body(blog);
    }
}
