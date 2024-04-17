package com.stock.analysis.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.analysis.application.code.dto.CodeResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CodeRedisTemplateService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    private HashOperations<String, String, String> hashOperations;

    @PostConstruct
    public void initializingHashOperation() {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void save(CodeResponseDto responseDto, Long userId) {
        if (Objects.isNull(responseDto) || Objects.isNull(userId)) {
            log.error("Values not be null");
            return;
        }
        try {
            hashOperations.put("user_%s".formatted(userId),
                    responseDto.getId().toString(),
                    serializePharmacyDto(responseDto));
        } catch (Exception e) {
            log.error("error while saving code data {}", e.getMessage());
        }
    }

    public Map<Long, CodeResponseDto> findAllCodesAsMap(Long userId) {
        Map<Long, CodeResponseDto> resultMap = new HashMap<>();
        try {
            Map<String, String> entries = hashOperations.entries("user_%s".formatted(userId));
            for (Map.Entry<String, String> entry : entries.entrySet()) {
                Long key = Long.parseLong(entry.getKey());
                CodeResponseDto codeResponseDto = deserializePharmacyDto(entry.getValue());
                resultMap.put(key, codeResponseDto);
            }
            return resultMap;
        } catch (Exception e) {
            log.error("error occurred {}", e.getMessage());
            return Collections.emptyMap();
        }
    }

    public void delete(Long codeId, Long userId) {
        try {
            hashOperations.delete("user_%s".formatted(userId), codeId.toString());
            log.info("code deleted from redis catch: userId - {}, codeId - {}", userId, codeId);
        } catch (Exception e) {
            log.error("error occurred {}", e.getMessage());
        }
    }

    private String serializePharmacyDto(CodeResponseDto responseDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(responseDto);
    }

    private CodeResponseDto deserializePharmacyDto(String value) throws JsonProcessingException {
        return objectMapper.readValue(value, CodeResponseDto.class);
    }
}
