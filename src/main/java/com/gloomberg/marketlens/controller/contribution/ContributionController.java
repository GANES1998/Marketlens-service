package com.gloomberg.marketlens.controller.contribution;

import com.gloomberg.marketlens.dto.CustomResponse;
import com.gloomberg.marketlens.dto.contribution.ContributionResult;
import com.gloomberg.marketlens.repository.ContributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/contribution")
public class ContributionController {

    @Autowired
    ContributionRepository contributionRepository;

    @GetMapping(path = "/result")
    public CustomResponse<List<ContributionResult>> getContributionResult(
            @RequestParam("minYear") int minYear,
            @RequestParam("maxYear") int maxYear,
            @RequestParam("sector") String sector,
            @RequestParam(value = "n", defaultValue = "5")  int n
    ) {

        List<ContributionResult> contributionResult = contributionRepository
                .getContributionResult(minYear, maxYear, sector, n);

        return CustomResponse.<List<ContributionResult>>builder()
                .data(contributionResult)
                .build();
    }

}
