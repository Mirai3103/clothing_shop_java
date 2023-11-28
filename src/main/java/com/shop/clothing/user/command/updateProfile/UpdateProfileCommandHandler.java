package com.shop.clothing.user.command.updateProfile;

import com.shop.clothing.auth.repository.IUserRepository;
import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import com.shop.clothing.config.ICurrentUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UpdateProfileCommandHandler implements IRequestHandler<UpdateProfileCommand, Void> {
    private final IUserRepository IUserRepository;
    private final ICurrentUserService currentUserService;

    @Override
    @Transactional
    public HandleResponse<Void> handle(UpdateProfileCommand updateProfileCommand) throws Exception {
        var userId = currentUserService.getCurrentUserId();
        if (userId.isEmpty()) {
            return HandleResponse.error("Bạn chưa đăng nhập");
        }
        var user = IUserRepository.findById(userId.get());
        if (user.isEmpty()) {
            return HandleResponse.error("Không tìm thấy người dùng");
        }
        var userEntity = user.get();
        userEntity.setFirstName(updateProfileCommand.getFirstName());
        userEntity.setLastName(updateProfileCommand.getLastName());
        userEntity.setEmail(updateProfileCommand.getEmail());
        userEntity.setAddress(updateProfileCommand.getAddress());
        userEntity.setPhoneNumber(updateProfileCommand.getPhoneNumber());
        IUserRepository.save(userEntity);
        return HandleResponse.ok();


    }
}
