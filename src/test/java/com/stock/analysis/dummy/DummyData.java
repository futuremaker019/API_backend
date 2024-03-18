package com.stock.analysis.dummy;

import com.stock.analysis.domain.contant.RoleType;
import com.stock.analysis.domain.entity.Code;
import com.stock.analysis.domain.entity.UserAccount;

import java.util.List;
import java.util.Set;

public class DummyData {

    public static Code createCode() {
        return Code.of(
                1L,
                "채용전형",
                null,
                createChildren()
        );
    }

    public static List<Code> createChildren() {
        return List.of(
                Code.of(2L, "1차 화상면접", 1L, List.of()),
                Code.of(3L, "2차 화상면접", 1L, List.of()),
                Code.of(4L, "3차 화상면접", 1L, List.of())
        );
    }

    public static UserAccount createUserAccount() {
        return UserAccount.of(
                1L,
                "noah",
                "1122",
                "noah@gmail.com",
                "noah00o",
                Set.of(RoleType.USER)
        );
    }

}
