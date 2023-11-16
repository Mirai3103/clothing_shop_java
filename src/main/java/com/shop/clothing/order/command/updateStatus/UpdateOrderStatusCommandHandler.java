package com.shop.clothing.order.command.updateStatus;

import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import com.shop.clothing.order.entity.enums.OrderStatus;
import com.shop.clothing.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public class UpdateOrderStatusCommandHandler implements IRequestHandler<UpdateOrderStatusCommand, Void> {
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public HandleResponse<Void> handle(UpdateOrderStatusCommand updateOrderStatusCommand) throws Exception {
        var order = orderRepository.findById(updateOrderStatusCommand.orderId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy đơn hàng"));
        order.setStatus(updateOrderStatusCommand.status());
        if(order.getStatus()== OrderStatus.DELIVERED || order.getStatus()== OrderStatus.CANCELLED){
            order.setCompletedDate(java.time.LocalDateTime.now());
        }
        orderRepository.save(order);
        return HandleResponse.ok();
    }
}
