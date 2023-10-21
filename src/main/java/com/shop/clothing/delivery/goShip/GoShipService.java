package com.shop.clothing.delivery.goShip;

import com.shop.clothing.delivery.IAddressService;
import com.shop.clothing.delivery.IDeliveryService;
import com.shop.clothing.delivery.dto.CreateShipOrderRequest;
import com.shop.clothing.delivery.dto.CreateShipOrderResponse;
import com.shop.clothing.delivery.dto.GetValidShipServiceRequest;
import com.shop.clothing.delivery.dto.GetValidShipServiceResponse;
import com.shop.clothing.order.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class GoShipService implements IDeliveryService {
    private final IAddressService addressService;
    private final GoShipProperties goShipProperties;


    @Override
    public List<GetValidShipServiceResponse> getValidShipService(GetValidShipServiceRequest request) {
        var fromCityId = addressService.findProvinceId(goShipProperties.getShopCity());
        var toCityId = addressService.findProvinceId(request.getToCity());
        var fromDistrictId = addressService.findDistrictId(goShipProperties.getShopDistrict(), fromCityId);
        var toDistrictId = addressService.findDistrictId(request.getToDistrict(), toCityId);
        var restTemplate = new RestTemplate();
        var accessToken = goShipProperties.getAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-Type", "application/json");
        JSONObject shipment = new JSONObject();
        JSONObject addressFrom = new JSONObject();
        addressFrom.put("city", fromCityId);
        addressFrom.put("district", fromDistrictId);
        JSONObject addressTo = new JSONObject();
        addressTo.put("city", toCityId);
        addressTo.put("district", toDistrictId);
        JSONObject parcel = new JSONObject();
        parcel.put("cod", request.getCod());
        parcel.put("weight", request.getWeightInGram());
        parcel.put("width", request.getWidthInCm());
        parcel.put("height", request.getHeightInCm());
        parcel.put("length", request.getLengthInCm());
        parcel.put("amount", request.getOrderValue());
        shipment.put("address_from", addressFrom);
        shipment.put("address_to", addressTo);
        shipment.put("parcel", parcel);
        JSONObject body = new JSONObject();
        body.put("shipment", shipment);
        HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);
        ResponseEntity<String> response = restTemplate.exchange(
                goShipProperties.getEndpoint() + "/rates",
                HttpMethod.POST,
                entity,
                String.class
        );
        JSONObject obj = new JSONObject(response.getBody());

        List<GetValidShipServiceResponse> result = new ArrayList<>();
        var data = obj.getJSONArray("data");
        for (int i = 0; i < data.length(); i++) {
            JSONObject service = data.getJSONObject(i);
            var serviceId = service.getString("id");
            var carrierName = service.getString("carrier_name");
            var carrierLogo = service.getString("carrier_logo");
            var serviceName = service.getString("service");
            var expected = service.getString("expected");
            var cod = service.getInt("cod_fee");
            var totalFee = service.getInt("total_fee");
            var totalAmount = service.getInt("total_amount");
            var responseService = GetValidShipServiceResponse.builder()
                    .id(serviceId)
                    .carrierName(carrierName)
                    .carrierLogo(carrierLogo)
                    .service(serviceName)
                    .expected(expected)
                    .totalAmount(totalAmount)
                    .totalFree(totalFee)
                    .build();
            result.add(responseService);
        }
        return result;
    }

    @Override
    public CreateShipOrderResponse createOrder(CreateShipOrderRequest request) {
        var a = goShipProperties.getShopCity();
        var fromCityId = addressService.findProvinceId(goShipProperties.getShopCity());
        var toCityId = addressService.findProvinceId(request.getToProvince());
        var fromDistrictId = addressService.findDistrictId(goShipProperties.getShopDistrict(), fromCityId);
        var toDistrictId = addressService.findDistrictId(request.getToDistrict(), toCityId);
        var fromWardId = addressService.findWardId(goShipProperties.getShopWard(), fromDistrictId);
        var toWardId = addressService.findWardId(request.getToWard(), toDistrictId);
        var fromStreetId = goShipProperties.getShopStreet();
        var toStreetId = request.getToDetailAddress();
        var restTemplate = new RestTemplate();
        var accessToken = goShipProperties.getAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-Type", "application/json");
        JSONObject shipment = new JSONObject();
        JSONObject addressFrom = new JSONObject();
        addressFrom.put("name", goShipProperties.getShopName());
        addressFrom.put("phone", goShipProperties.getShopPhone());
        addressFrom.put("street", fromStreetId);
        addressFrom.put("ward", fromWardId);
        addressFrom.put("district", fromDistrictId);
        addressFrom.put("city", fromCityId);
        JSONObject addressTo = new JSONObject();
        addressTo.put("name", request.getToName());
        addressTo.put("phone", request.getToPhone());
        addressTo.put("street", toStreetId);
        addressTo.put("ward", toWardId);
        addressTo.put("district", toDistrictId);
        addressTo.put("city", toCityId);
        JSONObject parcel = new JSONObject();
        parcel.put("cod", request.getCod());
        parcel.put("amount", request.getOrderAmount());
        parcel.put("weight", request.getWeightInGram());
        parcel.put("width", request.getWidthInCm());
        parcel.put("height", request.getHeightInCm());
        parcel.put("length", request.getLengthInCm());
        parcel.put("metadata", "Cho xem hàng");
        shipment.put("address_from", addressFrom);
        shipment.put("address_to", addressTo);
        shipment.put("parcel", parcel);
        shipment.put("rate", request.getRateServiceId());
        shipment.put("order_id", UUID.randomUUID().toString());
        JSONObject body = new JSONObject();
        body.put("shipment", shipment);
        HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);
        ResponseEntity<String> response = restTemplate.exchange(
                goShipProperties.getEndpoint() + "/shipments",
                HttpMethod.POST,
                entity,
                String.class
        );
        JSONObject obj = new JSONObject(response.getBody());
        var id = obj.getString("id");
        var cod = obj.getInt("cod");
        var fee = obj.getInt("fee");
//        var trackingNumber = obj.getString("tracking_number");
//        var carrier = obj.getString("carrier");
//        var createdAt = obj.getString("created_at");
        return CreateShipOrderResponse.builder().fee(fee).id(id).cod(cod).build();

    }

    public OrderStatus toDomainOrderStatus(int status) {
        if (status <= 904) return OrderStatus.PENDING;
        if (status == 905) return OrderStatus.DELIVERED;
        if (status == 914) return OrderStatus.CANCELLED;
        return OrderStatus.PENDING;
    }
}
