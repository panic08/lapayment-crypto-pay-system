package ru.panic.lapayment.template.util;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.panic.lapayment.template.dto.factory.PaymentResponseDto;
import ru.panic.lapayment.template.entity.UserFactory;
import ru.panic.lapayment.template.repository.impl.UserFactoryRepositoryImpl;
import java.time.Instant;

@Component
@Slf4j
public class WebHookUtil {
    public WebHookUtil(UserFactoryRepositoryImpl userFactoryRepository, RestTemplate restTemplate) {
        this.userFactoryRepository = userFactoryRepository;
        this.restTemplate = restTemplate;
    }

    UserFactoryRepositoryImpl userFactoryRepository;
    RestTemplate restTemplate;
    public void sendRequest(PaymentResponseDto dto){
        UserFactory userFactory = userFactoryRepository.findByMerchantId(dto.getMerchantId());

       if(userFactory == null){
           return;
       }
        switch (userFactory.getRequestMethod()) {
            case "HTTP" -> {
                log.info("Sending a webhook notification to URL: {} by method: {}", userFactory.getUrlBack(), dto.getCurrency());
                Gson gson = new Gson();
                Instant instant = Instant.now();
                long unixTime = instant.getEpochSecond();
                dto.setTime(unixTime);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<String> request = new HttpEntity<>(gson.toJson(dto), headers);
                try {
                    restTemplate.postForEntity(userFactory.getUrlBack(), request, String.class);
                }catch (Exception e){
                    log.warn("Failed attempt to send a webhook notification to a URL: {} by method: {}", userFactory.getUrlBack(), dto.getCurrency());
                }
            }
            case "GRPC" -> {
                System.out.println("soap");
            }



       }
    }
}
