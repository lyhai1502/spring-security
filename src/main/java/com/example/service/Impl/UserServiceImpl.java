package com.example.service.Impl;

import com.example.common.ERole;
import com.example.dto.JwtResponseDTO;
import com.example.dto.RequestAuthenticationDTO;
import com.example.enity.Role;
import com.example.enity.Token;
import com.example.enity.User;
import com.example.enity.UserRole;
import com.example.repository.RoleRepository;
import com.example.repository.TokenRepository;
import com.example.repository.UserRepository;
import com.example.repository.UserRoleRepository;
import com.example.service.JwtService;
import com.example.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public JwtResponseDTO loginUser(RequestAuthenticationDTO requestAuthenticationDTO)
    {
        UserDetails userDetails = userDetailsService.loadUserByUsername(requestAuthenticationDTO.getEmail());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestAuthenticationDTO.getEmail(),
                        requestAuthenticationDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByUserName(requestAuthenticationDTO.getEmail());
        List<UserRole> userRoles = userRoleRepository.findByUser(user);

        String jwt = jwtService.generateToken(userDetails);

        Token token = new Token();
        token.setToken(jwt);
        token.setExpired(jwtService.isTokenExpired(jwt));
        token.setDate_expired(jwtService.extractExpiration(jwt));
        token.setUser(user);
        Object o = jwtService.extractRole(jwt);
        tokenRepository.save(token);

        return new JwtResponseDTO(jwt, requestAuthenticationDTO.getEmail(), userRoles.get(0).toString());
    }

    @Transactional
    public User createUser(RequestAuthenticationDTO requestAuthenticationDTO) {
        User user = new User();

        user.setUserName(requestAuthenticationDTO.getEmail());
        user.setEncrytedPassword(passwordEncoder.encode(requestAuthenticationDTO.getPassword()));

        User savedUser = userRepository.save(user);

        UserRole userRole = new UserRole();
        userRole.setUser(savedUser);
        userRole.setRole(roleRepository.findByRoleName(requestAuthenticationDTO.getRoleName()));
        userRoleRepository.save(userRole);

        return savedUser;
    }


    @PostConstruct
    @Transactional
    public void init() {
        // Init Role
        List<Role> rolesToSave = new ArrayList<>();
        for (ERole eRole : ERole.values()) {
            if (!roleRepository.existsByRoleName(eRole.toString())) {
                Role role = new Role();
                role.setRoleName(eRole.toString());
                rolesToSave.add(role);
            }
        }
        if (!rolesToSave.isEmpty()) {
            roleRepository.saveAll(rolesToSave);
        }


        /////////////////////////////////////////////////////////////
        // Init ADMIN
        if(userRepository.findByUserName("admin@gmail.com") == null){
        User admin = new User();
        admin.setUserName("admin@gmail.com");
        admin.setEncrytedPassword("$2a$10$Y/TuqjI2mNpnWtJ48kn6bujXmjxnC9dhNhAlB/uBbwBW1iIPGbRRm");
        User saveAdmin = userRepository.save(admin);

        UserRole userRole = new UserRole();
        userRole.setUser(saveAdmin);
        userRole.setRole(roleRepository.findByRoleName(ERole.Admin.toString()));
        userRoleRepository.save(userRole);
        }
    }
}
