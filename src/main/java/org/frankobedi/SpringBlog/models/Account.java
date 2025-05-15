package org.frankobedi.SpringBlog.models;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    private String email;

    private String password;

    private String firstname;

    private String lastname;

    private String role; // entity with one-to-one relationship -> One role per account

    // We need to create a one-to-many relationship because 
    // each one account can have mnay posts
    @OneToMany(mappedBy = "account")
    private List<Post> posts;

    @ManyToMany
    @JoinTable(
        name="account_authority", // name of table
        joinColumns={@JoinColumn(name="account_id", referencedColumnName="id")},
        inverseJoinColumns={@JoinColumn(name="authority_id", referencedColumnName="id")})
    private Set<Authority> authorities = new HashSet<>();
}
