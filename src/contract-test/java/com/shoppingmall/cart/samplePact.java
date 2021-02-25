package com.shoppingmall.cart;

import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.model.RequestResponsePact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslRootValue;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;

import java.util.HashMap;
import java.util.Map;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpMethod;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Disabled
public class samplePact {

  private static final String HOST_NAME = "localhost";
  private static final int PORT = 9292;

  @Autowired
  private SampleService sampleService;

  @Autowired
  private ObjectMapper objectMapper;

  @Rule
  public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("product",
          HOST_NAME, PORT, this); //PactProviderRuleMk2 로 provider, port 주소 등을 setup

  @Pact(provider = "product", consumer = "cart") //  pact 호출
  public RequestResponsePact callCartClient(PactDslWithProvider builder) throws JsonProcessingException {
    String expectedString = "{\"contract test success\"}";
    String expectedStringJson = objectMapper.writeValueAsString(expectedString);

    PactDslRootValue pactDslResponse = new PactDslRootValue();
    pactDslResponse.setValue(expectedStringJson); // pactDslResponse 에 "contract test success" 저장

    Map<String,String> headers = new HashMap();
    headers.put("Content-Type","text/plain"); //headers라는 map에 key ="Content-Type", value = "text/plain" 저장

    return builder // 이 부분에서 pact 규약 생성
            .given("product list")// ".given" 에서는 state 라는 텍스트를 제공. 동일한 state 텍스를 사용하면 나중에 확인하기 위한 용도.
            .uponReceiving("test-cart-service")
            .path("/a")
            .method(HttpMethod.GET.name())
            .willRespondWith() // 이 아래로 provider가 respond with 할 내용 존재
            .status(HttpStatus.OK.value())
            .headers(headers)
            .body(pactDslResponse)
            .toPact();
  }
  //Given state "product list", upon receiving "test-cart-service" from cart(consumer 이름), with path("/a") ,method(HttpMethod.GET.name())에 대해 status(HttpStatus.OK.value()), headers(headers), body(pactDslResponse)를 provider가 respond 할 것이라는 규약 생성

  @Test
  @PactVerification(value = "product", fragment = "callCartClient") // product, callcartclient에 대해 pactverification 실행
  public void testConsumerGetRequestToString() {
    String expectedString = "{\"contract test success\"}";

    String resultString = sampleService.getString();

    assertEquals(expectedString, resultString); // expectedstring(예상값) 과 resultstring (실제 값) 같으면 테스트 성공

  }
}
