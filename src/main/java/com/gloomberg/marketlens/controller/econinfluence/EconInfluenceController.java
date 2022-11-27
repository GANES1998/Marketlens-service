package com.gloomberg.marketlens.controller.econinfluence;

import com.gloomberg.marketlens.dto.CustomResponse;
import com.gloomberg.marketlens.dto.econinfluence.EconInfluenceResult;
import com.gloomberg.marketlens.repository.EconInfluenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "econinfluence")
public class EconInfluenceController {

    @Autowired
    EconInfluenceRepository econInfluenceRepository;

    @GetMapping(path = "result")
    public CustomResponse<List<EconInfluenceResult>> getResult(
            @RequestParam("indicators") List<String> indicators,
            @RequestParam("minYear") int minYear,
            @RequestParam("maxYear") int maxYear,
            @RequestParam("sectors") List<String> sectors
    ) {

        List<EconInfluenceResult> result = econInfluenceRepository.getResult(
                indicators,
                sectors,
                minYear,
                maxYear
        );

        return CustomResponse.
                <List<EconInfluenceResult>>builder()
                .data(result)
                .build();

    }

}
