package com.vbtn.taskunite.repository;
import com.vbtn.taskunite.domain.AdminProfit;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AdminProfit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdminProfitRepository extends JpaRepository<AdminProfit, Long> {

}
