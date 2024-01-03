package com.stock.analysis.api.service;

import com.stock.analysis.api.kakao.KakaoApiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoAddressSearchService {

    private final RestTemplate restTemplate;
    private final KaKaoURIBuilderService kaKaoURIBuilderService;

    @Value("${kakao.rest.api.key}")
    private String kakaoRestApiKey;

    public KakaoApiResponseDto requestAddressSearch(String address) {
        if (StringUtils.isBlank(address)) {
            return null;
        }

        URI uri = kaKaoURIBuilderService.buildUriByAddressSearch(address);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApiKey);
        HttpEntity httpEntity = new HttpEntity(headers);

        return restTemplate
                .exchange(uri, HttpMethod.GET, httpEntity, KakaoApiResponseDto.class)
                .getBody();
    }
}
