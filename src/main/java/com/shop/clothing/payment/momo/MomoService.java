package com.shop.clothing.payment.momo;

import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;


@AllArgsConstructor
@Service
public class MomoService {
    private final MomoConfig momoConfig;

    public MomoCreatePaymentResponse createQRCodePayment(String orderId,int amount, String orderInfo) throws Exception {
        return createPayment(orderId,amount,orderInfo,RequestType.QR_CODE);
    }
    public MomoCreatePaymentResponse createATMPayment(String orderId,int amount, String orderInfo) throws Exception {
        return createPayment(orderId,amount,orderInfo,RequestType.PAY_WITH_ATM);
    }

    public MomoCreatePaymentResponse createPayment(String orderId,int amount, String orderInfo,RequestType requestType) throws Exception {

        String partnerCode = momoConfig.getPartnerCode();
        String accessKey = momoConfig.getAccessKey();
        String secretKey = momoConfig.getSecretKey();
        var extraData = "haha";
        String sb = "accessKey=" + accessKey +
                "&amount=" + amount +
                "&extraData=" + extraData +
                "&ipnUrl=" + momoConfig.getIpnUrl() +
                "&orderId=" + orderId +
                "&orderInfo=" + orderInfo +
                "&partnerCode=" + partnerCode +
                "&redirectUrl=" + momoConfig.getCallbackUrl() +
                "&requestId=" + orderId +
                "&requestType=" + requestType.toString();
        var signature= signHmacSHA256(sb, secretKey);

        JSONObject body = new JSONObject();
        body.put("partnerCode", partnerCode);
        body.put("partnerName", "Ngô Hữu Hoàng");
        body.put("storeId", "CUAHANG_QUANAO");
        body.put("requestId", orderId);
        body.put("amount", amount);
        body.put("orderId", orderId);
        body.put("orderInfo", orderInfo);
        body.put("redirectUrl", momoConfig.getCallbackUrl());
        body.put("ipnUrl", momoConfig.getIpnUrl());
        body.put("lang", "vi");
        body.put("extraData", extraData);
        body.put("requestType", requestType.toString());
        body.put("signature", signature);
        String url = "https://test-payment.momo.vn/v2/gateway/api/create";
        var restTemplate = new org.springframework.web.client.RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "application/json");
        HttpEntity<String> requestParams = new HttpEntity<>(body.toString(), requestHeaders);
        ResponseEntity<MomoCreatePaymentResponse> response = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.POST,
                requestParams,
                MomoCreatePaymentResponse.class
        );
        return response.getBody();
    }
    private  String signHmacSHA256(String data, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);
        byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return toHexString(rawHmac);
    }
    private  String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        Formatter formatter = new Formatter(sb);
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return sb.toString();
    }
}
