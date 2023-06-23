package com.example.config;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
public class PayPalConfigTest {

    @Value("${paypal.client.app}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;

    @Test
    public void testPaypalSdkConfig() {
        PayPalConfig payPalConfig = new PayPalConfig();
        Map<String, String> sdkConfig = payPalConfig.paypalSdkConfig();
        assertEquals(sdkConfig.get("mode"), mode);
    }
}