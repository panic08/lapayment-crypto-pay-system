package ru.panic.lapayment.template.util;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Component
public class CryptoExchangeUtil {
    public CryptoExchangeUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    RestTemplate restTemplate;
    public Map<String, Map<String, Double>> getCoinsPrice(String currency) {
        String url = "https://api.coingecko.com/api/v3/simple/price?ids=tron,bitcoin,ethereum,matic-network&vs_currencies="+ currency;
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(url, Map.class);
        Map<String, Map<String, Double>> result = responseEntity.getBody();
        return result;
    }
}
