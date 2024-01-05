package com.stock.analysis.api.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("카카오 주소 API ")
@ExtendWith(MockitoExtension.class)
class KakaoAddressSearchServiceTest {

    @InjectMocks
    private KakaoAddressSearchService sut;

    @Mock
    private KaKaoURIBuilderService kaKaoURIBuilderService;
    @Mock
    private RestTemplate restTemplate;

    @DisplayName("address 파라미터가 null이면, null 을 return 한다.")
    @Test
    public void givenAddressContainingNull_whenRequestKakaoAddressApi_thenReturnNull() {
        // given
        String address = null;

        // when
        var result = sut.requestAddressSearch(address);

        // then
        assertThat(result).isNull();
    }

    @DisplayName("address를 전달하면 정상적인 document를 반환한다.")
    @Test
    public void given_when_then() {
        // given
        String address = "서울 성북구 종암로 10길";

        // when
        var result = sut.requestAddressSearch(address);

        // then
        assertThat(result.getMetaDto().getTotalCount()).isEqualTo(2);
    }
}