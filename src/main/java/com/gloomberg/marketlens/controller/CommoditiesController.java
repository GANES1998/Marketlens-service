package com.gloomberg.marketlens.controller;

import com.gloomberg.marketlens.dto.CustomResponse;
import com.gloomberg.marketlens.repository.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/commodities")
public class CommoditiesController {

    @Autowired
    CommodityRepository commodityRepository;

    @GetMapping(path = "/all")
    public CustomResponse<List<String>> getAllCommodities() {

        List<String> allCommodities = commodityRepository.getAllCommodities();

        return CustomResponse.<List<String>>builder()
                .data(allCommodities)
                .build();
    }

}
