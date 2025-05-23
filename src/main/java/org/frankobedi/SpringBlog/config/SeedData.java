package org.frankobedi.SpringBlog.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.frankobedi.SpringBlog.models.Account;
import org.frankobedi.SpringBlog.models.Authority;
import org.frankobedi.SpringBlog.models.Post;
import org.frankobedi.SpringBlog.services.AccountService;
import org.frankobedi.SpringBlog.services.AuthorityService;
import org.frankobedi.SpringBlog.services.PostService;
import org.frankobedi.SpringBlog.util.constants.Privillages;
import org.frankobedi.SpringBlog.util.constants.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedData implements CommandLineRunner{

    // Bring in the account and post services
    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityService authorityService;
    
    @Override
    // String... means the function can take any number of args (0 or more)
    public void run(String... args) throws Exception{

        Account account01 = new Account();
        Account account02 = new Account();
        Account account03 = new Account();
        Account account04 = new Account();

        for(Privillages auth : Privillages.values()){
            // add the authority objects
            Authority authority = new Authority();
            authority.setId(auth.getId());
            authority.setName(auth.getPrivillage());
            authorityService.save(authority);
        }

        Set<Authority> authoritySet = new HashSet<>();
         Set<Authority> authoritySet2 = new HashSet<>();
        authorityService.findById(Privillages.RESET_ANY_USER_PASSWORD.getId()).ifPresent(authoritySet::add);
        authorityService.findById(Privillages.ACCESS_ADMIN_PANEL.getId()).ifPresent(authoritySet::add);
        authorityService.findById(Privillages.ACCESS_ADMIN_PANEL.getId()).ifPresent(authoritySet2::add);

        account01.setEmail("frankobedi6@gmail.com");
        account01.setFirstname("Frank");
        account01.setLastname("Obedi");
        account01.setPassword("12345");
        account01.setRole(Roles.ADMIN.getRole());
        account01.setAuthorities(authoritySet);

        // Regular user
        account02.setEmail("user@app.com");
        account02.setFirstname("Kasongo");
        account02.setLastname("Yeye");
        account02.setPassword("2468");

        account03.setEmail("super_editor@app.com");
        account03.setFirstname("Site");
        account03.setLastname("Admin");
        account03.setPassword("111");
        account03.setRole(Roles.EDITOR.getRole());
        account03.setAuthorities(authoritySet2);

        account04.setEmail("editor@app.com");
        account04.setFirstname("Web");
        account04.setLastname("Editor");
        account04.setPassword("333");
        account04.setRole(Roles.EDITOR.getRole());

        
        accountService.save(account01);
        accountService.save(account02);
        accountService.save(account03);
        accountService.save(account04);


        List<Post> posts = postService.getAll();

        // Check if DB has no posts yet, add new posts if so
        if(posts.isEmpty()){

            Post post01 = new Post();
            post01.setTitle("Post 01");
            post01.setBody("This is the body of my first post");
            post01.setAccount(account01);
            postService.save(post01);

            Post post02 = new Post();
            post02.setTitle("Post 02");
            post02.setBody("Second post body text");
            post02.setAccount(account02);
            postService.save(post02);

        }
    }
}
