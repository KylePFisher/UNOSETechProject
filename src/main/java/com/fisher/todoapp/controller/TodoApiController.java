package com.fisher.todoapp.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fisher.todoapp.repositories.TodoRepository;
import com.fisher.todoapp.repositories.entities.TodoEntity;

@Controller
public class TodoApiController {
	
	TodoRepository todoRepository;
	
	@Autowired
	public TodoApiController(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	@PostMapping(value = "/api/add-activity", produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public ModelAndView addActivity(@RequestParam(name = "activity") String activity, @RequestParam(name = "category") String category,
			@RequestParam(name="dateToComplete") String dateToComplete) {
		TodoEntity addTodo = new TodoEntity();
		addTodo.setActivity(activity);
		addTodo.setCategory(category);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/mm/dd");
		addTodo.setDateToComplete(LocalDate.parse(dateToComplete));
		addTodo.setDateAdded(LocalDate.now());
		todoRepository.save(addTodo);
		return new ModelAndView("redirect:/activities");
	}
	
	@PostMapping(value = "/api/update-activity", produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public ModelAndView updateActivity(@RequestParam(name = "activity") String activity, @RequestParam(name = "category") String category,
			@RequestParam(name="dateToComplete") String dateToComplete,
			@RequestParam(name = "oldActivity") String oldActivity,
			@RequestParam(name = "oldCategory") String oldCategory) {
		TodoEntity updateTodo = todoRepository.findByActivityAndCategory(oldActivity, oldCategory);
		updateTodo.setActivity(activity);
		updateTodo.setCategory(category);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/mm/dd");
		updateTodo.setDateToComplete(LocalDate.parse(dateToComplete));
		todoRepository.save(updateTodo);
		return new ModelAndView("redirect:/activities");
	}
}