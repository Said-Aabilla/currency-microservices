package com.mydomain.currencyexchange.controller;


import com.mydomain.currencyexchange.entity.CurrencyExchange;
import com.mydomain.currencyexchange.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeCntroller {



    @Autowired
    CurrencyExchangeService exchangeService;

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to
    ) {
       return exchangeService.retrieveExchangeValue(from, to);
    }

}
