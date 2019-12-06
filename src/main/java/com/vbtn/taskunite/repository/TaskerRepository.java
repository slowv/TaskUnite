package com.vbtn.taskunite.repository;
import com.vbtn.taskunite.domain.Tasker;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Tasker entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskerRepository extends JpaRepository<Tasker, Long> {

}
