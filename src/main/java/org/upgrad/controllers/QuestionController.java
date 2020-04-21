package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.Question;
import org.upgrad.models.User;
import org.upgrad.models.UserProfile;
import org.upgrad.services.QuestionService;
import org.upgrad.services.UserService;

import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
public class QuestionController {

    @Autowired
    UserService userService;
    @Autowired
    private QuestionService questionService;

    @PostMapping("/api/question")
    public ResponseEntity<?> createQuestion(@RequestParam(value = "categoryId") Set<Integer> categoryTitle,
                                            @RequestParam(value = "question") String question,
                                            HttpSession httpSession) {
        if (httpSession.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else if (userService.getRoleByUsername((String) httpSession.getAttribute("currUser")).equalsIgnoreCase("admin")) {
            //categoryService.addCategory(categoryTitle, question);
            return new ResponseEntity<>(categoryTitle + " Question added successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("You do not have rights to add question.", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/api/question/all/{categoryId}")
    public ResponseEntity<?> getAllQuestionsByCategory(@PathVariable(value = "categoryId") int categoryId, HttpSession httpSession) {
        if (httpSession.getAttribute("currUser") == null) {
            return new ResponseEntity<>(" Please Login first to access this endpoint", HttpStatus.UNAUTHORIZED);
        } else {
            UserProfile userProfile = userService.getUserProfile(categoryId);
            if (userProfile == null) {
                return new ResponseEntity<>("User Profile not found! ", HttpStatus.NOT_FOUND);
            } else {
                //retrieve all the questions
                return new ResponseEntity<>(userProfile, HttpStatus.OK);
            }
        }
    }

    @GetMapping("/api/question/all")
    public ResponseEntity<?> getAllQuestionsByUser(HttpSession httpSession) {
        if (httpSession.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        User currentUser = (User) httpSession.getAttribute("currUser");
        return new ResponseEntity<>(questionService.getAllQuestionsByUser((int) currentUser.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/api/question/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable(name = "questionId") int questionId, HttpSession httpSession) {
        if (httpSession.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        Question question = questionService.getQuestionById(questionId);
        if (question == null) {
            return new ResponseEntity<>("Question with questionId " + questionId + " not found.", HttpStatus.NOT_FOUND);
        }

        User currentUser = (User) httpSession.getAttribute("currUser");
        if (!currentUser.getRole().equalsIgnoreCase("admin")
                && !question.getUser().getUserName().equalsIgnoreCase(currentUser.getUserName())) {
            return new ResponseEntity<>("You do not have rights to delete this question!", HttpStatus.FORBIDDEN);
        }

        questionService.deleteQuestion(questionId);
        return new ResponseEntity<>("Question with questionId " + questionId + " deleted successfully.", HttpStatus.OK);
    }

}
