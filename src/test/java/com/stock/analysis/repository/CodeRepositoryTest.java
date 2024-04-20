package com.stock.analysis.repository;

import com.stock.analysis.application.code.dto.CodeResponseDto;
import com.stock.analysis.application.code.repository.CodeRepository;
import com.stock.analysis.cache.CodeRedisTemplateService;
import com.stock.analysis.config.AppConfig;
import com.stock.analysis.config.JpaTestConfig;
import com.stock.analysis.config.QuerydslTestConfig;
import com.stock.analysis.config.RedisConfig;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.dummy.DummyData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Code Repository 테스트")
@Import({JpaTestConfig.class, QuerydslTestConfig.class, RedisConfig.class,
        AppConfig.class, CodeRedisTemplateService.class})
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CodeRepositoryTest {

    private final CodeRepository codeRepository;
    private final CodeRedisTemplateService codeRedisTemplateService;
    private final RedisTemplate redisTemplate;
    private List<CodeResponseDto> codes;


    public CodeRepositoryTest(@Autowired CodeRepository codeRepository,
                              @Autowired CodeRedisTemplateService codeRedisTemplateService,
                              @Autowired RedisTemplate redisTemplate
    ) {
        this.codeRepository = codeRepository;
        this.codeRedisTemplateService = codeRedisTemplateService;
        this.redisTemplate = redisTemplate;
    }

    @BeforeEach
    void init() {
        Objects.requireNonNull(redisTemplate.getConnectionFactory()).getConnection().flushAll();
        UserAccount userAccount = DummyData.createUserAccount();
        codes = codeRepository.findAllByUserAccount(userAccount)
                .stream().map(CodeResponseDto::dtoWithoutChildren).toList();
        codes.forEach(code -> {
            codeRedisTemplateService.save(code, userAccount.getId());
        });
    }

    @DisplayName("redis 저장 및 조회")
    @Test
    void redisSaveAndFind() {
        //given
        UserAccount userAccount = DummyData.createUserAccount();

        // when
        Map<Long, CodeResponseDto> map = codeRedisTemplateService.findAllCodesAsMap(userAccount.getId());

        // then
        assertThat(map.get(1L)).isNotNull();
        assertThat(map.get(1L).getName()).isEqualTo(codes.get(0).getName());
    }

    @DisplayName("redis 삭제")
    @Test
    void redisDelete() {
        UserAccount userAccount = DummyData.createUserAccount();
        Long codeId = codes.get(0).getId();
        codeRedisTemplateService.delete(codes.get(0).getId(), userAccount.getId());
        Map<Long, CodeResponseDto> map = codeRedisTemplateService.findAllCodesAsMap(userAccount.getId());

        assertThat(map.get(codeId)).isNull();
        assertThat(map.get(2L)).isNotNull();
        assertThat(map.get(2L).getName()).isEqualTo(codes.get(1).getName());
    }
}
