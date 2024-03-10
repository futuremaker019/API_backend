package com.stock.analysis.application.code.service;

import com.stock.analysis.application.code.repository.CodeRepository;
import com.stock.analysis.domain.contant.RoleType;
import com.stock.analysis.domain.entity.Code;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.dto.CodeDto;
import com.stock.analysis.dto.response.CodeResponseDto;
import com.stock.analysis.dummy.DummyData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("비즈니스 로직 - 코드")
@ExtendWith(MockitoExtension.class)
/**
 *   application.yml에 명시한 test 코드의 profile에 의해 동작된다.
 *      AnalysisApplicationTests 에 명시한 ActiveProfile에 따라 동작한다.
  */

//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CodeServiceImplTest {

    @InjectMocks
    private CodeServiceImpl sut;
    @Mock
    private CodeRepository codeRepository;

    @DisplayName("유저마다 서로다른 코드 리스트를 리턴한다. 하위코드는 아이디 리스트로 가진다.")
    @Test
    public void given_and_then() {
        // given
        UserAccount userAccount = DummyData.createUserAccount();
        Code code = DummyData.createCode();
        given(codeRepository.selectCodesByUser(userAccount)).willReturn(List.of(code));

        //when
        List<CodeDto> codeDtos = sut.selectFlatCodes(userAccount);

        //then
        assertThat(codeDtos).extracting("name").contains("채용전형");
        assertThat(codeDtos).extracting("children").contains(List.of(2L, 3L, 4L));

    }

    @DisplayName("코드 아이디로 코드를 조회한다.")
    @Test
    void givenCodeId_whenSearchCode_thenReturnCode() {
        //given
        Long codeId = 1L;
        given(codeRepository.findById(codeId)).willReturn(Optional.of(DummyData.createCode()));

        // when
        CodeResponseDto dto = sut.getCode(codeId);

        // then
        assertThat(dto.getId()).isEqualTo(codeId);
    }
}