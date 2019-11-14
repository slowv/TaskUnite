package org.t1708e.taskunite.repository;
import org.t1708e.taskunite.domain.TaskCategory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TaskCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Long> {

}
