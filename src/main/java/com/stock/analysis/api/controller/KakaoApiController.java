package com.stock.analysis.api.controller;

import com.stock.analysis.api.kakao.KakaoApiResponseDto;
import com.stock.analysis.api.service.KakaoAddressSearchService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kakao")
@RequiredArgsConstructor
public class KakaoApiController {

    private final KakaoAddressSearchService kakaoAddressSearchService;

    @GetMapping("/get")
    public ResponseEntity<KakaoApiResponseDto> getData(String address) {
        System.out.println("address = " + address);
        return ResponseEntity.ok().body(kakaoAddressSearchService.requestAddressSearch(address));
    }
}
