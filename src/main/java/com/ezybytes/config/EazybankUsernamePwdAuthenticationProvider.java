package com.ezybytes.config;

import com.ezybytes.model.Authority;
import com.ezybytes.model.Customer;
import com.ezybytes.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class EazybankUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("EazybankUsernamePwdAuthenticationProvider -> Authentication authenticate");

        String userName = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        List<Customer> customerList = customerRepository.findByEmail(userName);

        System.out.println("customer list: " + customerList);

        if(customerList.size() > 0){
            if(passwordEncoder.matches(pwd, customerList.get(0).getPwd())){
                return new UsernamePasswordAuthenticationToken(userName, pwd, getGrantedAuthorities(customerList.get(0).getAuthorities()));
            }
            else{
                throw new BadCredentialsException("Invalid password");
            }
        }
        else{
            throw new BadCredentialsException("No user details with this details");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities){
        System.out.println("EazybankUsernamePwdAuthenticationProvider -> getGrantedAuthorities: " + authorities);

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(Authority authority : authorities){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        System.out.println("grantedAuthorities: " + grantedAuthorities);
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        boolean f = (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));

        System.out.println("EazybankUsernamePwdAuthenticationProvider -> supports: " + f);

        return f;
    }
}
