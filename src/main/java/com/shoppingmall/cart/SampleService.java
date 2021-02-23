package com.shoppingmall.cart;

import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Slf4j
@Service
public class SampleService {

  @Autowired
  public RestTemplateBuilder restTemplateBuilder;

  public ZonedDateTime getUpdatedTime() {

    return restTemplate().getForObject("http://localhost:8088/vodqa",ZonedDateTime.class);
  }

  public RestTemplate restTemplate() {
    return restTemplateBuilder.build();
  }

}
