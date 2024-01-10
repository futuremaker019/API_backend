package com.stock.analysis.application.apply.controller;

import com.stock.analysis.application.apply.repository.ApplyRepository;
import com.stock.analysis.dto.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/applies")
public class ApplyController {

    private final ApplyRepository applyRepository;

//    @GetMapping
//    public Response<> list() {
//
//    }

}
