package co.mushu.blogging.services;

import co.mushu.blogging.models.Blog;
import co.mushu.blogging.models.BlogResponse;
import co.mushu.blogging.repositories.BlogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServices {

    @Autowired
    private BlogsRepository blogsRepository;

    public List<BlogResponse> getBlogs(){
        List<BlogResponse> blogResponses = new ArrayList<>();
        for(Blog blog : blogsRepository.findAll()){
            blogResponses.add(new BlogResponse(blog.getCreatedBy().getUsername(),blog.getBlogId(),blog.getSubject(),blog.getContent(),blog.getLikes()));
        }
        return blogResponses;
    }

    public boolean blogIdPresent(String blogId){
        return blogsRepository.existsById(blogId);
    }

    public boolean createBlog(Blog blog){
        blogsRepository.save(blog);
        return blogsRepository.existsById(blog.getBlogId());
    }

    public BlogResponse getBlogById(String id){
        Blog blog = blogsRepository.findById(id).orElse(null);
        if(blog == null) return null;
        return new BlogResponse(blog.getCreatedBy().getUsername(),blog.getBlogId(),blog.getSubject(),blog.getContent(),blog.getLikes());
    }
}
