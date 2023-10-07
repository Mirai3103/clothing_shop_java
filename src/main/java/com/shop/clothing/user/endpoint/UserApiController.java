package com.shop.clothing.user.endpoint;

import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.user.UserDto;
import com.shop.clothing.user.query.getMyProfile.GetMyProfileQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserApiController {
    private final ISender sender;

    @GetMapping("/my-profile")
    public ResponseEntity<UserDto> getMyProfile() {
        var query = new GetMyProfileQuery();
        var result = sender.send(query);
        return ResponseEntity.ok(result.orThrow());
    }
}
