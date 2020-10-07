package com.fisher.todoapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fisher.todoapp.repositories.TodoRepository;
import com.fisher.todoapp.repositories.entities.TodoEntity;

@Controller
public class PageController {
	
	TodoRepository repo;
	
	@Autowired
	public PageController(TodoRepository repo) {
		this.repo = repo;
	}
	
	
	@GetMapping(value = "/activities", produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String activitiesList() {
		StringBuilder returnString = new StringBuilder();
		returnString.append("<html>\n<header><title>Activity List</title></header>\n<body>\n<h1>Activity List</h1><table><tr><th>Activity</th><th>Category</th><th>Date To Complete</th><th>Date Added</th><th>Edit</th>");
		List<TodoEntity> activityList = repo.findAll();
		for (TodoEntity activity : activityList) {
			returnString.append("\n<tr>");
			returnString.append("\n<td>" + activity.getActivity() + "</td>");
			returnString.append("\n<td>" + activity.getCategory() + "</td>");
			returnString.append("\n<td>" + activity.getDateToComplete() + "</td>");
			returnString.append("\n<td>" + activity.getDateAdded() + "</td>");
			returnString.append("\n<td>" + "<a href=\"updateActivity?activity=" + activity.getActivity() + "&category=" + activity.getCategory() + "\">Edit Activity</a> " + "</td>");
			returnString.append("\n</tr>");

		}
		returnString.append("</table>\n<a href=\"addActivity\"/>Add a new activity</a>\n</body>\n</html>");
		return returnString.toString();
	}
	
	@GetMapping(value = "/addActivity", produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String addActivity() {
		StringBuilder returnString = new StringBuilder();
		returnString.append("<html>\n<header><title>Add Activity</title></header>\n<body>\n<h1>Add Activity</h1>\n<form action=\"/todoapp/api/add-activity\" method=\"POST\">");
		returnString.append("\n<label for=\"activity\">Activity:</label>\r\n" + "<input id=\"activity\" type=\"text\" name=\"activity\">");
		returnString.append("\n<label for=\"category\">Category:</label>\r\n" + "<input id=\"category\" type=\"text\" name=\"category\">");
		returnString.append("\n<label for=\"dateToComplete\">Date To Complete:</label>\r\n" + "<input id=\"dateToComplete\" type=\"date\" name=\"dateToComplete\">");
		returnString.append("\n<input id=\"oldActivity\" type=\"hidden\" name=\"oldActivity\">");
		returnString.append("\n<input id=\"oldCategory\" type=\"hidden\" name=\"oldCategory\">");
		returnString.append("\n<input type=\"submit\">");
		returnString.append("</form></body></html>");
		return returnString.toString();
	}
	
	@GetMapping(value = "/updateActivity", produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String editActivity(@RequestParam(name = "activity") String activity, @RequestParam(name="category") String category) {
		TodoEntity todoActivity = repo.findByActivityAndCategory(activity, category);

		StringBuilder returnString = new StringBuilder();
		returnString.append("<html>\n<header><title>Edit Activity</title></header>\n<body>\n<h1>Edit Activity</h1>\n<form action=\"/todoapp/api/update-activity\" method=\"POST\">");
		returnString.append("\n<label for=\"activity\">Activity:</label>\r\n" + "<input id=\"activity\" type=\"text\" name=\"activity\" value=\"" + todoActivity.getActivity() + "\">");
		returnString.append("\n<label for=\"category\">Category:</label>\r\n" + "<input id=\"category\" type=\"text\" name=\"category\" value=\"" + todoActivity.getCategory() + "\">");
		returnString.append("\n<label for=\"dateToComplete\">Date To Complete:</label>\r\n" + "<input id=\"dateToComplete\" type=\"date\" name=\"dateToComplete\" value=\"" + todoActivity.getDateToComplete().toString() + "\">");
		returnString.append("\n<input id=\"oldActivity\" type=\"hidden\" name=\"oldActivity\" value=\"" + todoActivity.getActivity() + "\">");
		returnString.append("\n<input id=\"oldCategory\" type=\"hidden\" name=\"oldCategory\" value=\"" + todoActivity.getCategory() + "\">");
		returnString.append("\n<input type=\"submit\">");
		returnString.append("</form></body></html>");
		return returnString.toString();
	}
}