package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.upgrad.services.CategoryService;
import org.upgrad.services.QuestionService;

@Controller
public class CommonController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	QuestionService questionService;

	@GetMapping("/api/categories/all")
	public ResponseEntity<?> getAllCategories() {
		ResponseEntity<?> categoryList = new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);

		return categoryList;
	}

	@GetMapping("/api/questions/all")
	public ResponseEntity<?> getAllQuestions() {
		ResponseEntity<?> questionsList = new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);

		return questionsList;
	}

}
