package com.vbtn.taskunite.repository.custom;

import com.vbtn.taskunite.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface CustomTaskRepository extends JpaRepository<Task, Long> {
}
