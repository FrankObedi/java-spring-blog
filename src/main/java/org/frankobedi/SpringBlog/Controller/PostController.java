package org.frankobedi.SpringBlog.controller;

import java.security.Principal;
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
    

    // We will make use of Principal to determine if the user is the author of a post
    // This then allows them to edit the post
    @GetMapping("/post/{id}")
    public String getPost(@PathVariable Long id, Model model, Principal principal){

        // Just in case the search for a post fails we will use Option here
        Optional<Post> optionalPost = postService.getById(id);
        String authUser = "email";


        if(optionalPost.isPresent()){
            // If the post is found then create an object of Post
            Post post = optionalPost.get();
            model.addAttribute("post", post); // send the data

            if(principal != null){
                authUser = principal.getName(); // get username of current logged in session user
            }

            if(authUser.equals(post.getAccount().getEmail())){
                model.addAttribute("isOwner", true); // marker to indicate current user in post author
            }else{
                model.addAttribute("isOwner", false);
            }

            return "post_views/post";
        }else{
            return "404"; // post not found
        }
    }
}
