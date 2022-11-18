package com.gloomberg.marketlens.controller;

import com.gloomberg.marketlens.dto.CustomResponse;
import com.gloomberg.marketlens.entity.GrowthResult;
import com.gloomberg.marketlens.repository.GrowthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/growth")
public class GrowthController {

    @Autowired
    GrowthRepository growthRepository;

    @GetMapping("")
    public CustomResponse<List<GrowthResult>> getGrowthResult(
            @RequestParam("symbol") List<String> symbol,
            @RequestParam("minYear") int minYear,
            @RequestParam("maxYear") int toYear,
            @RequestParam(value = "aggBy", defaultValue = "MONTH") String aggBy
    ) {
        List<GrowthResult> growthResults = growthRepository.getGrowth(
                symbol,
                minYear,
                toYear,
                aggBy
        );

        return CustomResponse.<List<GrowthResult>>builder()
                .data(growthResults)
                .build();
    }

}
