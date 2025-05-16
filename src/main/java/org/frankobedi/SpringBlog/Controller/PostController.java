package org.frankobedi.SpringBlog.controller;

import java.util.Optional;

import org.frankobedi.SpringBlog.models.Post;
import org.frankobedi.SpringBlog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PostController {

    @Autowired
    private PostService postService;
    
    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model){

        // Just in case the search for a post fails we will use Option here
        Optional<Post> optionalPost = postService.getById(id);

        if(optionalPost.isPresent()){
            // If the post is found then create an object of Post
            Post post = optionalPost.get();
            model.addAttribute("post", post); // send the data
            return "post_views/post";
        }else{
            return "404"; // post not found
        }
    }
}
