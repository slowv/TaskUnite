package com.vbtn.taskunite.repository.custom;

import com.vbtn.taskunite.domain.Tasker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomTaskerRepository extends JpaRepository<Tasker, Long> {
    @Query("select tasker from Tasker tasker left join fetch tasker.taskerCategories where tasker.id =:id")
    Optional<Tasker> findOneWithEagerRelationships(@Param("id") Long id);
}
