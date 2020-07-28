package com.mxz.service.plan.dao;

import com.mxz.service.plan.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
