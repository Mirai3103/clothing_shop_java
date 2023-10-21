package com.shop.clothing.auth;

import com.shop.clothing.user.entity.User;
import com.shop.clothing.auth.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service("userDetailsService")
@Transactional
public class UserDetailServiceImpl implements UserDetailsService, UserDetailsPasswordService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        var useByEmail = userRepository.findByEmail(username);
        if (useByEmail.isPresent()) {
            return useByEmail.get();
        }
        var userByPhoneNumber = userRepository.findByPhoneNumber(username);
        if (userByPhoneNumber.isPresent()) {
            return userByPhoneNumber.get();
        }
        var userById = userRepository.findById(username);
        if (userById.isPresent()) {
            return userById.get();
        }

        throw new UsernameNotFoundException("User not found");
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        var userEntity = userRepository.findByEmail(user.getUsername());
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        userEntity.get().setPasswordHash(passwordEncoder.encode(newPassword));
        userRepository.save(userEntity.get());
        return userEntity.get();
    }
}
