package com.shop.clothing.order.query.getOrderById;

import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import com.shop.clothing.config.CurrentUserService;
import com.shop.clothing.order.dto.OrderDto;
import com.shop.clothing.order.repository.OrderRepository;
import com.shop.clothing.payment.dto.PaymentDto;
import com.shop.clothing.payment.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@Service
public class GetOrderByIdQueryHandler implements IRequestHandler<GetOrderByIdQuery, OrderDto> {
    private final ModelMapper _mapper;
    private final OrderRepository _orderRepository;
    private final PaymentRepository _paymentRepository;


    @Override
    public HandleResponse<OrderDto> handle(GetOrderByIdQuery getOrderByIdQuery) throws Exception {
        var order = _orderRepository.findById(getOrderByIdQuery.id()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        var orderDto = _mapper.map(order, OrderDto.class);
        var payment = _paymentRepository.findFirstByOrderIdSortedByCreatedDateDesc(order.getOrderId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found"));
        var paymentDto = _mapper.map(payment, PaymentDto.class);
        orderDto.setLatestPayment(paymentDto);
        return HandleResponse.ok(orderDto);
    }
}