package org.frankobedi.SpringBlog.repositories;

import org.frankobedi.SpringBlog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // tells spring that this class interacts with a db
public interface PostRepository extends JpaRepository<Post, Long> {
    
}
