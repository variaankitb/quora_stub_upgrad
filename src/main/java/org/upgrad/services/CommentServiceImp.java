package org.upgrad.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Comment;
import org.upgrad.repositories.CommentRepository;

@Service
public class CommentServiceImp implements CommentService{
	
	@Autowired
	CommentRepository commentRepository;

	@Override
	public void giveComment(String comment, long answerId, long userId) {
		commentRepository.giveComment(comment, userId, answerId, new Date(), new Date());
	}

	@Override
	public void editComment(String comment, long id) {
		commentRepository.editComment(comment, id, new Date());
	}

	@Override
	public Comment getCommentById(long id) {
		return commentRepository.getCommentById(id);
	}

	@Override
	public void deleteComment(long id) {
		commentRepository.deleteById(id);
	}

	@Override
	public List<Comment> getAllCommentsByAnswerId(long answerId) {
		return commentRepository.getAllCommentsByAnswer(answerId);
	}

}
