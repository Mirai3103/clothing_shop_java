package com.shop.clothing.user.command.updatePassword;


import com.shop.clothing.auth.repository.UserRepository;
import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import com.shop.clothing.config.ICurrentUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UpdatePasswordCommandHandler implements IRequestHandler<UpdatePasswordCommand, Void> {
    private final PasswordEncoder passwordEncoder;
    private final ICurrentUserService currentUserService;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public HandleResponse<Void> handle(UpdatePasswordCommand updatePasswordCommand) throws Exception {
        var userId = currentUserService.getCurrentUserId();
        if (userId.isEmpty()) {
            return HandleResponse.error("Bạn chưa đăng nhập");
        }
        var user = userRepository.findById(userId.get());
        if (user.isEmpty()) {
            return HandleResponse.error("Không tìm thấy người dùng");
        }
        var userEntity = user.get();
        if (!passwordEncoder.matches(updatePasswordCommand.getOldPassword(), userEntity.getPasswordHash())) {
            return HandleResponse.error("Mật khẩu cũ không đúng");
        }
        userEntity.setPasswordHash(passwordEncoder.encode(updatePasswordCommand.getNewPassword()));
        userRepository.save(userEntity);
        return HandleResponse.ok();
    }
}
