package com.example.service.Impl;


import com.example.enity.User;
import com.example.enity.UserRole;
import com.example.repository.UserRepository;
import com.example.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        // [ROLE_USER, ROLE_ADMIN,..]
        List<UserRole> userRoles = userRoleRepository.findByUser(user);

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (userRoles != null) {
            for (UserRole userRole : userRoles) {
                GrantedAuthority authority = new SimpleGrantedAuthority(userRole.getRole().getRoleName());
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getEncrytedPassword(), grantList);

        return userDetails;
    }

}