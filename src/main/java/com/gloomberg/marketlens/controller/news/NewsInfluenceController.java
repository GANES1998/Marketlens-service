package com.gloomberg.marketlens.controller.news;

import com.gloomberg.marketlens.dto.CustomResponse;
import com.gloomberg.marketlens.dto.news.NewsInfluenceResult;
import com.gloomberg.marketlens.entity.NewsEvent;
import com.gloomberg.marketlens.repository.NewsInfluenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsInfluenceController {

    @Autowired
    NewsInfluenceRepository newsInfluenceRepository;

    @GetMapping(path = "/all")
    public CustomResponse<List<NewsEvent>> getAllNewsEvents() {

        List<NewsEvent> allNewsEvents = newsInfluenceRepository.getAllNewsEvents();

        return CustomResponse.<List<NewsEvent>>builder()
                .data(allNewsEvents)
                .build();
    }

    @GetMapping(path = "/influence")
    public CustomResponse<List<NewsInfluenceResult>> getInfluenceResult(
            @RequestParam("event") String event
    ) {

        List<NewsInfluenceResult> newsInfluenceResult = newsInfluenceRepository.getNewsInfluenceResult(event);

        return CustomResponse.<List<NewsInfluenceResult>>builder()
                .data(newsInfluenceResult)
                .build();
    }

    @GetMapping(path = "/links")
    public CustomResponse<List<String>> getNewsInfluenceLinks(
            @RequestParam("event") String event
    ) {

        List<String> newsLinks = newsInfluenceRepository.getNewsInfluenceLinks(event);

        return CustomResponse.<List<String>>builder()
                .data(newsLinks)
                .build();

    }


}
