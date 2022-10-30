package com.gloomberg.marketlens.controller;

import com.gloomberg.marketlens.dto.CustomResponse;
import com.gloomberg.marketlens.entity.Stock;
import com.gloomberg.marketlens.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping(path = "/stock")
@Slf4j
public class StockController {

    @Autowired
    StockRepository stockRepository;

    @GetMapping(path = "", produces = "application/json", consumes = "application/json")
    public CustomResponse<List<Stock>> getStock(
            @RequestParam(name = "symbol", required = true, defaultValue = "IBM") String symbol,
            @RequestParam(name = "from", required = true)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestParam(name = "to", required = true)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate
            ) {

        List<Stock> stockBetween = stockRepository.getStockBetween(symbol, fromDate, toDate);

        log.info("Stock Fetched for [ {} ] between [ {} ] and [ {} ]", symbol, fromDate, toDate);

        return CustomResponse.<List<Stock>>builder()
                .data(stockBetween)
                .error(false)
                .build();
    }


}
