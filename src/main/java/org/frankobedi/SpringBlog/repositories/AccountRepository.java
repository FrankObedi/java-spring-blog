package org.frankobedi.SpringBlog.repositories;

import java.util.Optional;

import org.frankobedi.SpringBlog.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findOneByEmailIgnoreCase(String email);
}

