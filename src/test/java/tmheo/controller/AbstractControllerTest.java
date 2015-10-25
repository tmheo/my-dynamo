package tmheo.controller;

import com.jayway.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import tmheo.MyDynamoTestApplication;

import static com.jayway.restassured.config.EncoderConfig.encoderConfig;
import static com.jayway.restassured.config.LogConfig.logConfig;
import static com.jayway.restassured.config.RestAssuredConfig.newConfig;

/**
 * Created by taemyung on 2015. 10. 25..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MyDynamoTestApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@Slf4j
public abstract class AbstractControllerTest {

    @Value("${local.server.port}")
    protected int serverPort;

    @Before
    public void setUp() throws Exception {

        RestAssured.port = serverPort;
        RestAssured.config = newConfig()
                .encoderConfig(encoderConfig().defaultContentCharset("UTF-8"))
                .logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails().enablePrettyPrinting(true));

    }

}
