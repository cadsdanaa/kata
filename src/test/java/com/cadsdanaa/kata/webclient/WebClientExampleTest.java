package com.cadsdanaa.kata.webclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebClientExampleTest {

    @Autowired
    WebClientExample webClientExample;

    @Test
    void shouldCallSlowApi() throws Exception {
        webClientExample.getSlowResponse();
    }

}