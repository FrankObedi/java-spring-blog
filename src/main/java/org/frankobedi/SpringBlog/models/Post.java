package org.frankobedi.SpringBlog.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post{

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    // By default private vars are columns in the db table
    // if the var datatype and column datatype are same then you don't need to 
    // use any annotaions
    private String title;

    @Column(columnDefinition="TEXT")
    private String body;

    private LocalDateTime dateCreated; // spring will know its a data-time entry!!


    // We need to create a many-to-one relatuon ship
    // each post can be associated with only one account
    @ManyToOne
    @JoinColumn(name="account_id", referencedColumnName="id", nullable=false) // nullable so no post has no user
    private Account account;



}