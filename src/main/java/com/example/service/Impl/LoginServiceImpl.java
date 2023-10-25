package com.example.service.Impl;

import com.example.dto.JwtResponseDTO;
import com.example.dto.LoginDTO;
import com.example.enity.AppUser;
import com.example.enity.UserRole;
import com.example.repository.AppUserRepository;
import com.example.repository.UserRoleRepository;
import com.example.service.JwtService;
import com.example.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public JwtResponseDTO loginUser(LoginDTO loginDTO)
    {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken
                (loginDTO.getEmail(),
                loginDTO.getPassword());
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        UserDetails user = userDetailsService.loadUserByUsername(loginDTO.getEmail());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),
                        loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = appUserRepository.findByUserName(loginDTO.getEmail());
        List<UserRole> userRoles = userRoleRepository.findByAppUser(appUser);

        String jwt = jwtService.generateToken(user);

        return new JwtResponseDTO(jwt, loginDTO.getEmail(), userRoles.get(0).toString());
    }

    public AppUser createUser(LoginDTO loginDTO) {
        AppUser user = new AppUser();

        user.setUserName(loginDTO.getEmail());
        user.setEncrytedPassword(passwordEncoder.encode(loginDTO.getPassword()));

        return appUserRepository.save(user);
    }
}
