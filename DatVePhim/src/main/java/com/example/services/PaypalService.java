package com.example.services;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaypalService {
    @Autowired
    private APIContext apiContext;

    public Payment createPayment(
            Double total, String currency,
            String description, String cancelURL, String successURL) throws PayPalRESTException {

        Amount amount = new Amount(currency,Double.toString(total));

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment("sale", payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();

        redirectUrls.setCancelUrl(cancelURL);
        redirectUrls.setReturnUrl(successURL);

        payment.setRedirectUrls(redirectUrls);
        apiContext.setMaskRequestId(true);
        return payment.create(apiContext);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecution);
    }

}
