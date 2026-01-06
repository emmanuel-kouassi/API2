package com.example.sae.Services;

import com.example.sae.Models.Formation;
import com.example.sae.Models.User;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService {

    @Value("sk_test_51SjjXKJ6ZZBcPg42ZPODCKaaqdAQCWOvwIykHMBrrxnvWsv3Awxyqrc0TowUm31DVQbbvIVCAptqAoIvFdifxKqD0021hzRTcr")
    private String secretKey;

    public com.stripe.model.checkout.Session createStripeSession(User user, Formation formation, String successUrl, String cancelUrl) throws StripeException {
        Stripe.apiKey = secretKey;

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(successUrl)
                .setCancelUrl(cancelUrl)
                .setCustomerEmail(user.getMail())
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("eur")
                                .setUnitAmount((long) (formation.getPrix() * 100))
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("Formation : " + formation.getTitre())
                                        .build())
                                .build())
                        .build())
                .build();

        return com.stripe.model.checkout.Session.create(params);
    }
}