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
          HOST_NAME, PORT, this);

  @Pact(consumer = "cart")
  public RequestResponsePact callCartClient(PactDslWithProvider builder) throws JsonProcessingException {
    String expectedString = "contract test for second consumer";
    String expectedStringJson = objectMapper.writeValueAsString(expectedString);

    PactDslRootValue pactDslResponse = new PactDslRootValue();
    pactDslResponse.setValue(expectedStringJson);

    Map<String,String> headers = new HashMap();
    headers.put("Content-Type","text/plain");

    return builder
            .given("product list")
            .uponReceiving("test-account-service")
            .path("/9001")
            .method(HttpMethod.GET.name())
            .willRespondWith()
            .status(HttpStatus.OK.value())
            .headers(headers)
            .body(pactDslResponse)
            .toPact();
  }

  @Test
  @PactVerification(value = "product", fragment = "createPactForGetString")
  public void testConsumerGetRequestToString() {
    String expectedString = "{\"id\":9001,\"total_price\":0,\"products\":[]}";

    String resultString = sampleService.getString();
    System.out.println(resultString);

    assertEquals(expectedString, resultString);

  }
}
