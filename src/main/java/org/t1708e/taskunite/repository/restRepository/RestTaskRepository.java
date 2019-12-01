package org.t1708e.taskunite.repository.restRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.t1708e.taskunite.domain.Task;

public interface RestTaskRepository extends JpaRepository<Task, Long> {

}
