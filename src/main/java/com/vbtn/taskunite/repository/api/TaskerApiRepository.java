package com.vbtn.taskunite.repository.api;

import com.vbtn.taskunite.domain.Tasker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskerApiRepository extends JpaRepository<Tasker, Long>, JpaSpecificationExecutor<Tasker> {
}
