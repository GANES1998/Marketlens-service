package com.gloomberg.marketlens.controller;

import com.gloomberg.marketlens.dto.CustomResponse;
import com.gloomberg.marketlens.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "sectors")
public class SectorController {

//    @GetMapping(path = "", produces = "application/json")
//    public List<String> getAllSectors() {
//        return ;
//    }

    @Autowired
    SectorRepository sectorRepository;

    @GetMapping(path = "all")
    public CustomResponse<List<String>> getAllSectors() {
        List<String> allSectors = sectorRepository.getAllSectors();

        return CustomResponse.<List<String>>builder()
                .data(allSectors)
                .build();
    }

}
