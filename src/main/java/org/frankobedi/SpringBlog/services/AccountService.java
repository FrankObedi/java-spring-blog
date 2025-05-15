package org.frankobedi.SpringBlog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.frankobedi.SpringBlog.models.Account;
import org.frankobedi.SpringBlog.models.Authority;
import org.frankobedi.SpringBlog.repositories.AccountRepository;
import org.frankobedi.SpringBlog.util.constants.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class AccountService implements UserDetailsService{
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account save(Account account){
        // encode the password
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        if(account.getRole() == null){
            account.setRole(Roles.USER.getRole());
        }
        return accountRepository.save(account);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Account> optionalAccount = accountRepository.findOneByEmailIgnoreCase(email);

        if (!optionalAccount.isPresent()) {
            System.out.println("UsernameNotFoundException triggered: " + email);
            throw new UsernameNotFoundException("Account not found for email: " + email);
        }

        Account account = optionalAccount.get();

        List<GrantedAuthority> grantedAuthority = new ArrayList<>();
        grantedAuthority.add(new SimpleGrantedAuthority(account.getRole()));

        for(Authority _auth : account.getAuthorities()){
            grantedAuthority.add(new SimpleGrantedAuthority(_auth.getName()));            
        }

        return new org.springframework.security.core.userdetails.User(
        account.getEmail(), account.getPassword(), grantedAuthority);  
    }
}
