package com.stock.analysis.fileUpload;

import com.stock.analysis.dto.UploadResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileUploadTest extends WebIntegrationTest{


    @DisplayName("1. 파일을 한개 업로드 한다.")
    @Test
    public void test_1() {
        MultiValueMap form = new LinkedMultiValueMap();
        form.add("file", new ClassPathResource("test1.txt"));

        HttpEntity req = new HttpEntity(form, null);
        ResponseEntity<UploadResult> response = client.postForEntity(uri("/upload"), req, UploadResult.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("/files/test1.txt", response.getBody().getPath());
    }

    @DisplayName("2. 파일을 2개 업로드 한다.")
    @Test
    public void test_2() {
        // given

        // when

        // then
    }
}
