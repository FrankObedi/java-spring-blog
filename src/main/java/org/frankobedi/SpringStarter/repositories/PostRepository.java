package org.frankobedi.SpringStarter.repositories;

import org.frankobedi.SpringStarter.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // tells spring that this class interacts with a db
public interface PostRepository extends JpaRepository<Post, Long> {
    
}
