package org.frankobedi.SpringBlog.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.frankobedi.SpringBlog.models.Post;
import org.frankobedi.SpringBlog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;


// The services as the name implies will perfom most of the
// functionality of the app.
@Service
public class PostService {
    // Inversion of control means giving spring control of object creation
    // instead of doing it ourselves. autowire is used for this.
    @Autowired // Now PostRepository is automatically injected by Spring
    private PostRepository postRepository;

    public Optional<Post> getById(Long id){
        return postRepository.findById(id);
    }

    public List<Post> getAll(){
        return postRepository.findAll();
    }

    public void delete(Post post){
        postRepository.delete(post);
    }

    public Post save(Post post){
        if(post.getId() == null){
            post.setDateCreated(LocalDateTime.now());
        }
        return postRepository.save(post);
    }
    
}
