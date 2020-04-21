package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.upgrad.models.*;
import org.upgrad.services.*;

import javax.servlet.http.HttpSession;

@Controller
public class LikeFollowController {

    @Autowired
    LikeService likeService;

    @Autowired
    UserService userService;

    @Autowired
    AnswerService answerService;

    @Autowired
    FollowService followService;

    @Autowired
    NotificationService notificationService;

    @PostMapping("/api/like/{answerId}")
    public ResponseEntity<?> giveLikes(@RequestParam(name = "answerId") int answerId, HttpSession httpSession) {
        if (httpSession.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        Answer ans = answerService.getAnswerById(answerId);
        if (ans == null) {
            return new ResponseEntity<>("Answer with answerId " + answerId + " not found.", HttpStatus.NOT_FOUND);
        }

        User currentUser  = (User) httpSession.getAttribute("currUser");
        Likes likes = likeService.getLikes(currentUser.getId(), answerId);
        if (likes != null) {
            return new ResponseEntity<>("You have already liked this answer!", HttpStatus.FORBIDDEN);
        }

        likeService.giveLikes((int) currentUser.getId(), answerId);
        notificationService.createNotification(currentUser.getId(), "User with userId " + currentUser.getId() + " has liked your answer with answerId " + answerId);

        return new ResponseEntity<>("answerId " + answerId + " liked successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/api/unlike/{answerId}")
    public ResponseEntity<?> unlike(@PathVariable(name = "answerId") int answerId, HttpSession httpSession) {
        if (httpSession.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        Answer answer = answerService.getAnswerById(answerId);
        if (answer == null) {
            return new ResponseEntity<>("Answer with answerId " + answerId + " not found.", HttpStatus.NOT_FOUND);
        }

        User currentUser  = (User) httpSession.getAttribute("currUser");
        Likes likes = likeService.getLikes(currentUser.getId(), answerId);
        if (likes == null) {
            return new ResponseEntity<>("You have not liked this answer.", HttpStatus.FORBIDDEN);
        }

        likeService.unlike((int) currentUser.getId(), answerId);
        return new ResponseEntity<>("You have unliked answer with answerId " + answerId + " successfully.", HttpStatus.OK);
    }

    @PostMapping("/api/follow/{categoryId}")
    public  ResponseEntity<?> addFollowCategory(@PathVariable(name = "categoryId") int categoryId, HttpSession httpSession) {
        if (httpSession.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        User currentUser  = (User) httpSession.getAttribute("currUser");
        Follow follow = followService.getFollow(currentUser.getId(), categoryId);
        if (follow != null) {
            return new ResponseEntity<>("You have already followed this category!", HttpStatus.FORBIDDEN);
        }

        followService.addFollow((int) currentUser.getId(), categoryId);
        return new ResponseEntity<>("categoryId " + categoryId + " followed successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/api/unfollow/{categoryId}")
    public ResponseEntity<?> unFollow(@PathVariable(name = "categoryId") int categoryId, HttpSession httpSession) {
        if (httpSession.getAttribute("currUser") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        User currentUser  = (User) httpSession.getAttribute("currUser");
        Follow follow = followService.getFollow(currentUser.getId(), categoryId);
        if (follow == null) {
            return new ResponseEntity<>("You are currently not following this category", HttpStatus.FORBIDDEN);
        }

        followService.unFollow((int) currentUser.getId(), categoryId);
        return new ResponseEntity<>("You have unfollowed the category with categoryId " + categoryId + " successfully.", HttpStatus.OK);
    }

}
