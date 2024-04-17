package com.stock.analysis.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import({RedisTemplateTest.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = CodeRedisTemplateService.class)
class CodeRedisTemplateServiceTest {

    @Autowired
    private CodeRedisTemplateService codeRedisTemplateService;

    @BeforeEach
    public void init() {
        Long userId = 1L;
        codeRedisTemplateService.findAllCodesAsMap(userId).forEach((codeId, codeResponseDto) -> {
            codeRedisTemplateService.delete(codeId, userId);
        });
    }

    @Test
    void save() {
        //given

        // when

        // then
    }

    @Test
    void findAll() {
        //given

        // when

        // then
    }

    @Test
    void delete() {
        //given

        // when

        // then
    }


}