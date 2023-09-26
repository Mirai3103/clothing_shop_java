package com.shop.clothing.service.Impl;

import com.shop.clothing.common.Result;
import com.shop.clothing.entity.User;
import com.shop.clothing.repository.UserRepository;
import com.shop.clothing.request.auth.LoginRequest;
import com.shop.clothing.request.auth.RegisterRequest;
import com.shop.clothing.security.MyUserDetail;
import com.shop.clothing.service.AuthService;
import com.shop.clothing.service.RoleService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service("userDetailsService")
@Transactional
public class AuthServiceImpl implements AuthService, UserDetailsService, UserDetailsPasswordService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;

    @Override
    public Result<User> login(@Valid LoginRequest loginRequest) {
        var user = userRepository.findByEmail(loginRequest.getUsername())
                .orElse(userRepository.findByPhoneNumber(loginRequest.getUsername())
                        .orElse(null));
        if (user == null) {
            return Result.error("Email không tồn tại");
        }
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())) {
            return Result.error("Mật khẩu không chính xác");
        }
        return Result.success(user);
    }

    @Override
    public Result<User> register(@Valid RegisterRequest registerRequest) {
        var existedUser = userRepository.findByEmail(registerRequest.getEmail());
        if (existedUser.isPresent()) {
            return Result.error("Email đã tồn tại");
        }
        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .isCustomer(true)
                .isEmailVerified(false)
                .isAccountEnabled(true)
                .passwordHash(passwordEncoder.encode(registerRequest.getRawPassword()))
                .roles(List.of(roleService.getRoleByName("ROLE_CUSTOMER").orElseThrow()))
                .build();

        userRepository.save(user);
        return Result.success(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var useByEmail = userRepository.findByEmail(username);
        if (useByEmail.isPresent()) {
            return new MyUserDetail(useByEmail.get());
        }
        var userByPhoneNumber = userRepository.findByPhoneNumber(username);
        if (userByPhoneNumber.isPresent()) {
            return new MyUserDetail(userByPhoneNumber.get());
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
        return new MyUserDetail(userEntity.get());
    }
}
