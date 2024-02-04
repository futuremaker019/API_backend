package com.stock.analysis.application.code.repository;

import com.stock.analysis.domain.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeRepository extends JpaRepository<Code, Long> {

    List<Code> findAllById(Long id);

    List<Code> findAllByParentIdIsNull();

}
