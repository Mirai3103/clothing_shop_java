package com.shop.clothing.auth.commands.resetPassword;

import com.shop.clothing.common.Cqrs.IRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordCommand implements IRequest<Void> {
    private String token;
    private String newPassword;

}
