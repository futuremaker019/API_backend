package com.stock.analysis.repository;

import com.stock.analysis.domain.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
}
