package com.isep.acme.services;

import com.isep.acme.model.user.BaseUser;
import com.isep.acme.repositories.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.isep.acme.model.UserView;
import com.isep.acme.model.UserViewMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private UserViewMapper userViewMapper;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final var user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with username - %s, not found", username));
        }

        return user;
    }

    public UserView getUser(final Long userId){
        return userViewMapper.toUserView(userRepo.getById(userId));
    }

    public Optional<BaseUser> getUserId(Long user) {
        return userRepo.findById(user);
    }
}
