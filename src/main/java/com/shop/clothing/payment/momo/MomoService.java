package com.shop.clothing.payment.momo;

import com.shop.clothing.config.RestExceptionHandler;
import com.shop.clothing.payment.entity.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.UUID;


@AllArgsConstructor
@Service
public class MomoService {
    private final MomoConfig momoConfig;
    private final Logger logger = LoggerFactory.getLogger(MomoService.class);
    private final com.shop.clothing.payment.repository.PaymentRepository paymentRepository;


    public MomoCreatePaymentResponse createQRCodePayment(String paymentId, int amount, String orderInfo) throws Exception {
        return createPayment(paymentId, amount, orderInfo, RequestType.QR_CODE);
    }

    public MomoCreatePaymentResponse createATMPayment(String paymentId, int amount, String orderInfo) throws Exception {
        return createPayment(paymentId, amount, orderInfo, RequestType.PAY_WITH_ATM);
    }

    public MomoCreatePaymentResponse createPayment(String paymentId, int amount, String orderInfo, RequestType requestType) throws Exception {

        String partnerCode = momoConfig.getPartnerCode();
        String accessKey = momoConfig.getAccessKey();
        String secretKey = momoConfig.getSecretKey();
        var extraData = "haha";
        var orderId = UUID.randomUUID().toString();
        String sb = "accessKey=" + accessKey +
                "&amount=" + amount +
                "&extraData=" + extraData +
                "&ipnUrl=" + momoConfig.getIpnUrl() +
                "&orderId=" + orderId +
                "&orderInfo=" + orderInfo +
                "&partnerCode=" + partnerCode +
                "&redirectUrl=" + momoConfig.getCallbackUrl() +
                "&requestId=" + paymentId +
                "&requestType=" + requestType.toString();
        var signature = signHmacSHA256(sb, secretKey);

        JSONObject body = new JSONObject();
        body.put("partnerCode", partnerCode);
        body.put("partnerName", "Ngô Hữu Hoàng");
        body.put("storeId", "CUAHANG_QUANAO");
        body.put("requestId", paymentId);
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
        logger.info(response.getBody().toString());
        return response.getBody();
    }

    private String signHmacSHA256(String data, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);
        byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return toHexString(rawHmac);
    }

    private String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        Formatter formatter = new Formatter(sb);
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return sb.toString();
    }


    public JSONObject checkPaymentStatus(String paymentId, String orderId) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        String partnerCode = momoConfig.getPartnerCode();
        String accessKey = momoConfig.getAccessKey();
        String secretKey = momoConfig.getSecretKey();
        String sb = "accessKey=" + accessKey +
                "&orderId=" + orderId +
                "&partnerCode=" + partnerCode +
                "&requestId=" + paymentId;
        var signature = signHmacSHA256(sb, secretKey);

        JSONObject body = new JSONObject();
        body.put("partnerCode", partnerCode);
        body.put("requestId", paymentId);
        body.put("orderId", orderId);
        body.put("signature", signature);
        body.put("lang", "vi");
        String url = "https://test-payment.momo.vn/v2/gateway/api/query";
        var restTemplate = new org.springframework.web.client.RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "application/json");
        HttpEntity<String> requestParams = new HttpEntity<>(body.toString(), requestHeaders);
        ResponseEntity<JSONObject> response = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.POST,
                requestParams,
                JSONObject.class
        );
        return response.getBody();
    }

    public PaymentStatus handleCallback(MomoCallbackParam momoCallbackParam) {
        var payment = paymentRepository.findById(momoCallbackParam.getRequestId()).orElseThrow();
            var message = momoCallbackParam.getMessage();
        payment.setPaymentResponse(message);
        var signature = momoCallbackParam.getSignature();// toDo: check signature
        switch (momoCallbackParam.getResultCode()) {
            case 0:
                payment.setStatus(PaymentStatus.PAID);
                break;
            case 8000:
                payment.setStatus(PaymentStatus.PENDING);
            case 7000:
                payment.setStatus(PaymentStatus.PENDING);
            case 1000:
                payment.setStatus(PaymentStatus.PENDING);
            case 4300:
                payment.setStatus(PaymentStatus.FAILED);
            case 1001:
                payment.setStatus(PaymentStatus.FAILED);
            case 1003:
                payment.setStatus(PaymentStatus.CANCELLED);
            case 1004:
                payment.setStatus(PaymentStatus.FAILED);
            case 1006:
                payment.setStatus(PaymentStatus.CANCELLED);
            case 4010:
                payment.setStatus(PaymentStatus.FAILED);
            case 4011:
                payment.setStatus(PaymentStatus.FAILED);
            default:
                payment.setStatus(PaymentStatus.FAILED);
        }
        paymentRepository.save(payment);
        return payment.getStatus();
    }


}
