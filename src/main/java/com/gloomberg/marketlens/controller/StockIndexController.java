package com.gloomberg.marketlens.controller;

import com.gloomberg.marketlens.dto.CustomResponse;
import com.gloomberg.marketlens.repository.StockIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "indices")
public class StockIndexController {

    @Autowired
    StockIndexRepository stockIndexRepository;

    @GetMapping(path = "all")
    public CustomResponse<List<String>> getAllStockIndices() {

        List<String> allStockIndices = stockIndexRepository.getAllStockIndices();

        return CustomResponse.
                <List<String>>builder()
                .data(allStockIndices)
                .build();
    }

}
