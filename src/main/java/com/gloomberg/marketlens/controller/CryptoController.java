package com.gloomberg.marketlens.controller;

import com.gloomberg.marketlens.dto.CustomResponse;
import com.gloomberg.marketlens.repository.CryptoCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "cryptos")
public class CryptoController {

    @Autowired
    CryptoCurrencyRepository cryptoCurrencyRepository;

    @GetMapping(path = "all")
    public CustomResponse<List<String>> getAllCryptoCurrencies() {

        List<String> allCryptoCurrencies = cryptoCurrencyRepository.getAllCryptoCurrencies();

        return CustomResponse.<List<String>>builder()
                .data(allCryptoCurrencies)
                .build();
    }

}
