package org.upgrad.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.upgrad.models.Answer;
import org.upgrad.models.Comment;
import org.upgrad.models.User;
import org.upgrad.services.AnswerService;
import org.upgrad.services.CommentService;
import org.upgrad.services.NotificationService;
import org.upgrad.services.UserService;

@Controller
public class CommentController {

	@Autowired
	CommentService commentService;

	@Autowired
	UserService userService;

	@Autowired
	NotificationService notificationService;

	@Autowired
	AnswerService answerService;

	@PostMapping("/api/comment")
	public ResponseEntity<?> giveComment(@RequestParam String content, @RequestParam long answerId,
			HttpSession httpSession) {

		if (httpSession.getAttribute("currUser") == null) {
			return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
		} else {
			User currentUser = userService.findUserByUsername((String) httpSession.getAttribute("username"));
			commentService.giveComment(content, answerId, currentUser.getId());
			Answer answer = answerService.getAnswerById((int) answerId);
			notificationService.createNotification(answer.getUser().getId(), "User with userId " + currentUser.getId()
					+ " has commented on your answer with answerId  " + answerId);
			return new ResponseEntity<>("AnswerId " + answerId + " commented successfully.", HttpStatus.OK);
		}

	}

	@PutMapping("/api/comment/{commentId}")
	public ResponseEntity<?> editComment(@PathVariable(name = "commentId") long commentId, @RequestParam String content,
			HttpSession httpSession) {

		if (httpSession.getAttribute("currUser") == null) {
			return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
		} else {
			User currentUser = userService.findUserByUsername((String) httpSession.getAttribute("username"));
			Comment comment = commentService.getCommentById(commentId);
			if (currentUser.getRole().equals("admin") || currentUser.getId() == comment.getUserId()) {
				commentService.editComment(content, commentId);
				return new ResponseEntity<>("Comment with commentId " + commentId + " edited successfully.",
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>("You do not have rights to edit this comment.", HttpStatus.UNAUTHORIZED);
			}
		}

	}

	@DeleteMapping("/api/comment/{commentId}")
	public ResponseEntity<?> deleteComment(@PathVariable(name = "commentId") long commentId, HttpSession httpSession) {

		if (httpSession.getAttribute("currUser") == null) {
			return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
		} else {
			User currentUser = userService.findUserByUsername((String) httpSession.getAttribute("username"));
			Comment comment = commentService.getCommentById(commentId);
			if (currentUser.getRole().equals("admin") || currentUser.getId() == comment.getUserId()) {
				commentService.deleteComment(commentId);
				return new ResponseEntity<>("Comment with commentId " + commentId + " deleted successfully.",
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>("You do not have rights to edit this comment.", HttpStatus.UNAUTHORIZED);
			}
		}
	}

	@GetMapping("/api/comment/all/{answerId}")
	public ResponseEntity<?> getAllCommentsByAnswer(@PathVariable(name = "answerId") long answerId,
			HttpSession httpSession) {

		if (httpSession.getAttribute("currUser") == null) {
			return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<>(commentService.getAllCommentsByAnswerId(answerId), HttpStatus.OK);
		}
	}
}
