package com.mydomain.currencyexchange.service;


import com.mydomain.currencyexchange.entity.CurrencyExchange;
import com.mydomain.currencyexchange.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeService {

    @Autowired
    Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;


    public CurrencyExchange retrieveExchangeValue(String from, String to){

        CurrencyExchange currencyExchange =currencyExchangeRepository.findByFromAndTo(from, to).orElseThrow(() -> new RuntimeException("Exchange not available from "+from+" to "+to));
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;


    }

}
