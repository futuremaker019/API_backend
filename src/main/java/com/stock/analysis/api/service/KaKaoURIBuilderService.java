package com.stock.analysis.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class KaKaoURIBuilderService {

    private static final String KAKAO_LOCAL_SEARCH_ADDRESS_URL = "https://dapi.kakao.com/v2/local/search/address.json";

    public URI buildUriByAddressSearch(String address) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromHttpUrl(KAKAO_LOCAL_SEARCH_ADDRESS_URL)
                .queryParam("query", address);

        URI uri = uriBuilder.build().encode().toUri();      // 인코딩된 uri를 반환
        log.info("address : {}, uri : {}", address, uri);

        return uri;
    }
}
