package com.stock.analysis.application.code.repository;

import com.stock.analysis.application.code.repository.custom.CodeRepositoryCustom;
import com.stock.analysis.domain.entity.Code;
import com.stock.analysis.domain.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code, Long>, CodeRepositoryCustom {

    Optional<Code> findById(Long id);
    Optional<Code> findByPrimeCodeNameAndUserAccount(String primeCodeName, UserAccount userAccount);

    List<Code> findAllByParentIdIsNullOrderByCreatedAtAsc();

    Optional<Code> findByNameAndParentId(String name, Long parentId);
}
