package com.cadsdanaa.kata.mockrestserver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder.encode;

@SpringBootTest
public class RestTemplateExampleTest {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SimpleService simpleService;

    @Value("${simpleRestCallUrl}")
    String expectedUrl;

    @Value("${resttemplate.username}")
    String username;

    @Value("${resttemplate.password}")
    String password;

    MockRestServiceServer mockRestServiceServer;

    @BeforeEach
    void setUp() {
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void shouldGetTestResponse() {
        String expectedResponse = "{\"key\": \"value\"}";
        mockRestServiceServer
                .expect(once(), requestTo(expectedUrl))
                .andExpect(method(GET))
                .andExpect(header("Authorization", "Basic " + new String(encode((username + ":" + password).getBytes()))))
                .andRespond(withSuccess(expectedResponse, APPLICATION_JSON));

        String actualResponse = simpleService.makeSimpleRestCall();

        assertThat(expectedResponse).isEqualTo(actualResponse);
    }

}
