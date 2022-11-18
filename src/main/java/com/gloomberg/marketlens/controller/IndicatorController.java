package com.gloomberg.marketlens.controller;

import com.gloomberg.marketlens.dto.CustomResponse;
import com.gloomberg.marketlens.repository.IndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "indicators")
public class IndicatorController {

    @Autowired
    IndicatorRepository indicatorRepository;

    @GetMapping("/all")
    public CustomResponse<List<String>> getIndicators() {
        List<String> indicators = indicatorRepository.getAllIndicators();

        return CustomResponse.<List<String>>builder()
                .data(indicators)
                .build();
    }

}
