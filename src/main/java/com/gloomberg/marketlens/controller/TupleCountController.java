package com.gloomberg.marketlens.controller;

import com.gloomberg.marketlens.dto.CustomResponse;
import com.gloomberg.marketlens.repository.TupleCountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "tuplecount")
public class TupleCountController {

    @Autowired
    TupleCountsRepository tupleCountsRepository;

    @GetMapping(path = "/all")
    public CustomResponse<Map<String, Long>> getTupleCounts() {

        Map<String, Long> tupleCounts = tupleCountsRepository.getTupleCounts();

        return CustomResponse.
                <Map<String, Long>>builder()
                .data(tupleCounts)
                .build();
    }

}
