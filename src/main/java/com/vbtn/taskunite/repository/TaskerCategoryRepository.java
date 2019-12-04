package com.vbtn.taskunite.repository;
import com.vbtn.taskunite.domain.TaskerCategory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TaskerCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskerCategoryRepository extends JpaRepository<TaskerCategory, Long> {

}
