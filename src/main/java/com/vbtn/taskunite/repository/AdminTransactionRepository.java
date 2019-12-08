package com.vbtn.taskunite.repository;
import com.vbtn.taskunite.domain.AdminTransaction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AdminTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdminTransactionRepository extends JpaRepository<AdminTransaction, Long> {

}
