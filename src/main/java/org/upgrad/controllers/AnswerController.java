package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.Answer;
import org.upgrad.models.Question;
import org.upgrad.models.User;
import org.upgrad.services.AnswerService;
import org.upgrad.services.QuestionService;
import org.upgrad.services.UserService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionService questionService;

    @Autowired
    UserService userService;

    @PostMapping("/api/answer")
    public ResponseEntity<?> createAnswer(@RequestParam(name = "questionId") int questionId, @RequestParam(name = "answer") String answer, HttpSession httpSession) {
        if (httpSession.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        User currentUser  = (User) httpSession.getAttribute("currUser");
        answerService.createAnswer(answer, new Date(), (int) currentUser.getId(), questionId);

        return new ResponseEntity<>("Answer to questionId "+ questionId+" added successfully.", HttpStatus.OK);
    }

    @PutMapping("/api/answer/{answerId}")
    public ResponseEntity<?> editAnswer(@PathVariable(name = "answerId") int answerId, @RequestParam(name = "answer") String answer, HttpSession httpSession) {
        if (httpSession.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        Answer ans = answerService.getAnswerById(answerId);
        if(ans == null) {
            return new ResponseEntity<>("Answer with answerId " + answerId + " not found.", HttpStatus.NOT_FOUND);
        }

        User currentUser  = (User) httpSession.getAttribute("currUser");
        if (!currentUser.getRole().equalsIgnoreCase("admin")
                && !ans.getUser().getUserName().equalsIgnoreCase(currentUser.getUserName())) {
            return new ResponseEntity<>("You do not have rights to edit this answer.", HttpStatus.FORBIDDEN);
        }

        answerService.updateAnswer(answerId, answer, new Date());

        return new ResponseEntity<>("Answer with answerId "+ answerId+" edited successfully.", HttpStatus.OK);
    }

    @GetMapping("/api/answer/all/{questionId}")
    public ResponseEntity<?> getAllAnswersToQuestion(@PathVariable(name = "questionId") int questionId, HttpSession httpSession) {
        if (httpSession.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        Question question = questionService.getQuestionById(questionId);
        if(question == null) {
            return new ResponseEntity<>("Question with questionId " + questionId + " not found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(answerService.getAllAnswersToQuestion(questionId), HttpStatus.OK);
    }

    @GetMapping("/api/answer/all")
    public ResponseEntity<?> getAllAnswersByUser(HttpSession httpSession) {
        if (httpSession.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        User currentUser  = (User) httpSession.getAttribute("currUser");
        List<Answer> answerList = answerService.getAllAnswersByUser((int) currentUser.getId());
        return new ResponseEntity<>(answerList, HttpStatus.OK);
    }

    @DeleteMapping("/api/answer/{answerId}")
    public ResponseEntity<?> deleteAnswer(@PathVariable(name = "answerId") int answerId, HttpSession httpSession) {
        if (httpSession.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        Answer answer = answerService.getAnswerById(answerId);
        if(answer == null) {
            return new ResponseEntity<>("Answer with answerId " + answerId + " not found.", HttpStatus.NOT_FOUND);
        }

        User currentUser  = (User) httpSession.getAttribute("currUser");
        if (!currentUser.getRole().equalsIgnoreCase("admin")
                && !answer.getUser().getUserName().equalsIgnoreCase(currentUser.getUserName())) {
            return new ResponseEntity<>("You do not have rights to delete this answer!", HttpStatus.FORBIDDEN);
        }
        answerService.deleteAnswer(answerId);
        return new ResponseEntity<>("Answer with answerId " + answerId + " deleted successfully.", HttpStatus.OK);
    }

    @GetMapping("/api/answer/likes/{questionId}")
    public ResponseEntity<?> getAllAnswersByLikes(@PathVariable(name = "questionId") String questionId, HttpSession httpSession) {
        if (httpSession.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        // TODO: Please complete this endpoint

        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }
}
