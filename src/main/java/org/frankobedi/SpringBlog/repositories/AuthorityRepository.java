package org.frankobedi.SpringBlog.repositories;

import org.frankobedi.SpringBlog.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    
}
