package org.t1708e.taskunite.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.t1708e.taskunite.domain.Tasker;

import java.util.List;

@Repository
public interface TaskerApiRepository extends JpaRepository<Tasker, Long>, JpaSpecificationExecutor<Tasker> {
}
