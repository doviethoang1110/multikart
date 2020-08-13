package com.hoang.security.service;

import com.hoang.entities.Users;
import com.hoang.repository.users.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IUsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users user = usersRepository.findByName(s).orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản: " + s));
        UserDetailsImpl userDetails = new UserDetailsImpl();
        if (null != user) {
            Set<GrantedAuthority> authorities = new HashSet<>();
            if (null != user.getRoles()) user.getRoles().forEach(r -> {
                authorities.add(new SimpleGrantedAuthority(r.getName()));
                r.getPermissions().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getName())));
            });
            userDetails.setUsername(user.getName());
            userDetails.setPassword(user.getPassword());
            userDetails.setEmail(user.getEmail());
            userDetails.setId(user.getId());
            userDetails.setAuthorities(authorities);
        }
        return userDetails;
    }
}
