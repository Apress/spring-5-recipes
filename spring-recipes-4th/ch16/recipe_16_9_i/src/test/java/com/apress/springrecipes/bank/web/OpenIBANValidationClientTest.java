package com.apress.springrecipes.bank.web;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import com.apress.springrecipes.bank.config.BankConfiguration;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= { BankConfiguration.class})
public class OpenIBANValidationClientTest {

    @Autowired
    private OpenIBANValidationClient client;

    private MockRestServiceServer mockRestServiceServer;

    @Before
    public void init() {
        mockRestServiceServer = MockRestServiceServer.createServer(client);
    }

    @Test
    public void validIban() {

        mockRestServiceServer
                .expect(requestTo("https://openiban.com/validate/NL87TRIO0396451440?getBIC=true&validateBankCode=true"))
                .andRespond(withSuccess(new ClassPathResource("NL87TRIO0396451440-result.json"), MediaType.APPLICATION_JSON));

        IBANValidationResult result = client.validate("NL87TRIO0396451440");
        assertTrue(result.isValid());
    }

    @Test
    public void invalidIban() {

        mockRestServiceServer
                .expect(requestTo("https://openiban.com/validate/NL28XXXX389242218?getBIC=true&validateBankCode=true"))
                .andRespond(withSuccess(new ClassPathResource("NL28XXXX389242218-result.json"), MediaType.APPLICATION_JSON));

        IBANValidationResult result = client.validate("NL28XXXX389242218");
        assertFalse(result.isValid());
    }


}