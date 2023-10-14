package com.shop.clothing.delivery;

import com.shop.clothing.delivery.dto.CreateShipOrderRequest;
import com.shop.clothing.delivery.dto.CreateShipOrderResponse;
import com.shop.clothing.delivery.dto.GetValidShipServiceRequest;
import com.shop.clothing.delivery.dto.GetValidShipServiceResponse;
import lombok.AllArgsConstructor;

import java.util.List;

public interface IDeliveryService {
    public List<GetValidShipServiceResponse> getValidShipService(GetValidShipServiceRequest request);
    public CreateShipOrderResponse createOrder(CreateShipOrderRequest request);
}
