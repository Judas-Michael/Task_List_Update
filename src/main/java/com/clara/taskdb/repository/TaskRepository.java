package com.clara.taskdb.repository;

import com.clara.taskdb.model.Task;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


    @Repository
    public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
        List<Task> findAllByOrderByUrgentDesc();

    }
