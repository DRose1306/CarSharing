package com.example.carsharing.security;

import com.example.carsharing.entity.Role;
import com.example.carsharing.entity.UserInfo;
import com.example.carsharing.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Optional<UserInfo> userInfo = userInfoRepository.findByLogin(login);

        if (userInfo.isEmpty()) {
            throw new UsernameNotFoundException(login);
        }
//        else {
//            String password = userInfo.get().getPassword();
//            user=userRepository.findByUserInfo(userInfo.get());
//        }

        return User.withUsername(login)
                .username(userInfo.get().getLogin())
                .password(userInfo.get().getPassword())
                .authorities(getAuthorities(userInfo.get().getRoles()))
                .build();
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName().name()));
            role.getAuthorities()
                    .forEach(authority -> authorities.add(new SimpleGrantedAuthority(authority.getAuthority().name())));
        }

        return authorities;
    }
}
