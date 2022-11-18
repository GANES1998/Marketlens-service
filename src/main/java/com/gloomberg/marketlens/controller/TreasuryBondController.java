package com.gloomberg.marketlens.controller;

import com.gloomberg.marketlens.dto.CustomResponse;
import com.gloomberg.marketlens.repository.TreasuryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/bonds")
public class TreasuryBondController {

    @Autowired
    TreasuryRepository treasuryRepository;

    @GetMapping(path = "/all")
    public CustomResponse<List<Integer>> getAllTenures() {

        List<Integer> tenures = treasuryRepository.getAllTreasuries();

        return CustomResponse.<List<Integer>>builder()
                .data(tenures)
                .build();
    }

}
