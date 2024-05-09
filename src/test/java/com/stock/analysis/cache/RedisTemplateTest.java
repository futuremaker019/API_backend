package com.stock.analysis.cache;

import com.stock.analysis.application.code.dto.CodeResponseDto;
import com.stock.analysis.config.RedisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

//@Import({JpaTestConfig.class, QuerydslTestConfig.class, RedisTemplateTest.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = RedisConfig.class)
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void redis_test() {
        //given
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String key = "stringKey";
        String value = "hello";

        // when
        valueOperations.set(key, value);

        // then
         String result = (String) valueOperations.get(key);
        assertThat(result).isEqualTo(value);
    }

    @Test
    void redis_map_test() {
        //given
        Map<Long, CodeResponseDto> map = new HashMap<>();
        map.put(1L, CodeResponseDto.builder().id(1L).name("플랫폼").parentId(null).build());
        map.put(2L, CodeResponseDto.builder().id(2L).name("채용전형").parentId(null).build());
        map.put(3L, CodeResponseDto.builder().id(3L).name("잡코리아").parentId(1L).build());
        map.put(6L, CodeResponseDto.builder().id(6L).name("서류전형").parentId(2L).build());
        map.put(9L, CodeResponseDto.builder().id(9L).name("1차 화상면접").parentId(8L).build());

        // when
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(String.valueOf(1L), map);

        // then
        CodeResponseDto codeResponseDto = (CodeResponseDto) redisTemplate.opsForHash().get(String.valueOf(1L), 1L);
        assertThat(codeResponseDto.getId()).isEqualTo(1L);
        assertThat(codeResponseDto.getName()).isEqualTo("플랫폼");
    }




}
