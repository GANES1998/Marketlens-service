package com.gloomberg.marketlens.controller.variance;

import com.gloomberg.marketlens.dto.CustomResponse;
import com.gloomberg.marketlens.dto.variance.SectorVarianceResult;
import com.gloomberg.marketlens.repository.SectorVarianceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/variance")
public class SectorVarianceController {

    @Autowired
    SectorVarianceRepository sectorVarianceRepository;

    @GetMapping(path = "/result")
    public CustomResponse<List<SectorVarianceResult>> getResult(
        @RequestParam(value = "sectors") List<String> sectors,
        @RequestParam(value = "minYear") int minYear,
        @RequestParam(value = "maxYear") int maxYear,
        @RequestParam(value = "aggBy") String aggBy
    ) {

        List<SectorVarianceResult> sectorVarianceResult = sectorVarianceRepository.getSectorVarianceResult(
                sectors,
                minYear,
                maxYear,
                aggBy
        );

        return CustomResponse.<List<SectorVarianceResult>>builder()
                .data(sectorVarianceResult)
                .build();

    }

}
