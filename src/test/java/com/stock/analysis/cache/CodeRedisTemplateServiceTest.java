package com.stock.analysis.cache;

import com.stock.analysis.application.code.repository.CodeRepository;
import com.stock.analysis.config.AppConfig;
import com.stock.analysis.config.RedisConfig;
import com.stock.analysis.domain.entity.Code;
import com.stock.analysis.dummy.DummyData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

/**
 * codeRepository에서 실제 데이터를 가져와 codeRedisTemplateService를 이용하여 cache에 저장, 조회, 삭제가 잘되는지
 *  확인하는 작업을 하려고 했으나, CodeRepository의 bean이 없다고 나온다...
 * 해당 테스트는 codeRepositoryTest로 옮겼고, codeRepositoryTest 에서 CodeRedisTemplateService를
 * @Import에 추가하여 진행했다.
 */
@Import({RedisConfig.class, AppConfig.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {CodeRedisTemplateService.class, CodeRepository.class})
class CodeRedisTemplateServiceTest {

    private final CodeRedisTemplateService codeRedisTemplateService;
    private final CodeRepository codeRepository;

    public CodeRedisTemplateServiceTest(
            @Autowired CodeRedisTemplateService codeRedisTemplateService,
            @Autowired CodeRepository codeRepository) {
        this.codeRedisTemplateService = codeRedisTemplateService;
        this.codeRepository = codeRepository;
    }

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
        Long userId = 1L;
//        CodeResponseDto code = CodeResponseDto.builder()
//                .build();
        List<Code> allByUserAccount = codeRepository.findAllByUserAccount(DummyData.createUserAccount());
        allByUserAccount.forEach(code -> System.out.println("code = " + code));

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