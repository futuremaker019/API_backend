package com.stock.analysis.application.apply.repository;

import com.stock.analysis.application.apply.repository.custom.ApplyRepositoryCustom;
import com.stock.analysis.domain.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long>, ApplyRepositoryCustom {
}
