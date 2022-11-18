package co.mushu.blogging.services;

import co.mushu.blogging.entities.Blog;
import co.mushu.blogging.repositories.BlogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServices {

    @Autowired
    private BlogsRepository blogsRepository;

    public List<Blog> getBlogs(){
        return blogsRepository.findAll();
    }

    public boolean blogIdPresent(String blogId){
        return blogsRepository.existsById(blogId);
    }

    public boolean createBlog(Blog blog){
        blogsRepository.save(blog);
        return blogsRepository.existsById(blog.getBlogId());
    }

    public Blog getBlogById(String id){
        return blogsRepository.findById(id).orElse(null);
    }

//    public BlogResponse updateBlog(BlogResponse blogResponse){
//        Blog blog = blogsRepository.findById(blogResponse.getBlogId()).orElse(null);
//
//    }

}
