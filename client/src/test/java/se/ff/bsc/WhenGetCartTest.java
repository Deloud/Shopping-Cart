package se.ff.bsc;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslJsonRootValue;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.lang.Integer;

public class WhenGetCartTest {
    @Rule
    public PactProviderRuleMk2 provider = new PactProviderRuleMk2("productservice", "localhost", 8088, this);

    // pact 정의
    @Pact(consumer = "cartservice")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap();
        headers.put("Content-Type", "application/json");

        // 우리가 원하는 최종 결과 = response 의 body 부분
        DslPart userResults = new PactDslJsonBody()
                .integerType("price",1000)
                .stringValue("name","Apple") // string value까지 확인
                .asBody();

        return builder
                .given("Request cart information has id 1") // interactions - providerStates - name
                .uponReceiving("A request for product information") // interactions - description
                .path("/1") // interactions - request - path
                .method("GET") // interactions - request - method
                .willRespondWith()
                .status(200)  // interactions - response - status
                .headers(headers) // interactions - response - headers - content-Type
                .body(userResults).toPact(); // interactions - response - body

    }

    // pact 테스트
    @Test
    @PactVerification()
    public void doTest() {
        System.setProperty("pact.rootDir","../pacts");  // Change output dir for generated pact-files
        Integer eta = new WhenGetCart(provider.getPort()).checkUser("1");

    }

}

