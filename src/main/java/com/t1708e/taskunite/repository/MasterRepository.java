package com.t1708e.taskunite.repository;
import com.t1708e.taskunite.domain.Master;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Master entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MasterRepository extends JpaRepository<Master, Long> {

}
