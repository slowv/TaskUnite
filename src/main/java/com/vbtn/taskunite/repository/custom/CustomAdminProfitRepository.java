package com.vbtn.taskunite.repository.custom;

import com.vbtn.taskunite.domain.AdminProfit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface CustomAdminProfitRepository extends JpaRepository<AdminProfit, Long> {
    List<AdminProfit> findByCreatedAtAfterOrderByCreatedAtAsc(Instant date);
}
