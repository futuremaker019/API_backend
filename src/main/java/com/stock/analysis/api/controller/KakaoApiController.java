package com.stock.analysis.api.controller;

import com.stock.analysis.api.kakao.KakaoApiResponseDto;
import com.stock.analysis.api.service.KakaoAddressSearchService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kakao")
@RequiredArgsConstructor
public class KakaoApiController {

    private final KakaoAddressSearchService kakaoAddressSearchService;

    @GetMapping("/get")
    public ResponseEntity<KakaoApiResponseDto> getData(@RequestParam String address) {
        System.out.println("address = " + address);
        return ResponseEntity.ok().body(kakaoAddressSearchService.requestAddressSearch(address));
    }
}
