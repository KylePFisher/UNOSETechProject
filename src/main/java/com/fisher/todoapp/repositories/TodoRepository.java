package com.fisher.todoapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fisher.todoapp.repositories.entities.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
	TodoEntity findByActivityAndCategory(String acitvity, String category);
	
	List<TodoEntity> findByCategoryOrderByDateToComplete(String category);
}