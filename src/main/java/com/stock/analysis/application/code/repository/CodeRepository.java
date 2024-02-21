package com.stock.analysis.application.code.repository;

import com.stock.analysis.domain.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code, Long> {

    List<Code> findAllById(Long id);
    Optional<Code> findById(Long id);

    List<Code> findAllByParentIdIsNull();

    Optional<Code> findByNameAndParentId(String name, Long parentId);

    List<Code> findAllByParentId(Long parentId);
}
