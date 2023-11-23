package com.shop.clothing.user.query.getAllUsers;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.shop.clothing.auth.repository.UserRepository;
import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import com.shop.clothing.common.dto.Paginated;
import com.shop.clothing.stockReceipt.dto.SupplierDto;
import com.shop.clothing.user.UserBriefDto;
import com.shop.clothing.user.UserDto;
import com.shop.clothing.user.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class GetAllUsersQueryHandler implements IRequestHandler<GetAllUsersQuery, Paginated<UserBriefDto>>{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final EntityManager entityManager;
    
    @Override
    @Transactional
    public HandleResponse<Paginated<UserBriefDto>> handle(GetAllUsersQuery request) throws Exception {
       var users = userRepository.findAll(
                request.getPageable("userId"));
        return HandleResponse.ok(Paginated.of(users.map(user -> modelMapper.map(user, UserBriefDto.class))));
    }
    
}
