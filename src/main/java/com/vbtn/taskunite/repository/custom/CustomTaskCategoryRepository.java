package com.vbtn.taskunite.repository.custom;

import com.vbtn.taskunite.domain.TaskCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface CustomTaskCategoryRepository extends JpaRepository<TaskCategory, Long> {
}
