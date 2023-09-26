package com.shop.clothing.service;

import com.shop.clothing.common.Result;
import com.shop.clothing.entity.User;
import com.shop.clothing.request.auth.LoginRequest;
import com.shop.clothing.request.auth.RegisterRequest;

public interface AuthService {
    Result<User> login(LoginRequest loginRequest);
    Result<User> register(RegisterRequest registerRequest);


}
