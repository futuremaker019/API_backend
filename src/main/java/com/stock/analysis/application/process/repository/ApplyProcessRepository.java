package com.stock.analysis.application.process.repository;

import com.stock.analysis.domain.entity.ApplyProcess;
import com.stock.analysis.domain.entity.ApplyProcessComplexIds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyProcessRepository extends JpaRepository<ApplyProcess, ApplyProcessComplexIds> {

    List<ApplyProcess> findAllByIdApplyId(Long applyId);

}
