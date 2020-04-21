package org.upgrad.services;

import java.util.List;

import org.upgrad.models.Comment;

public interface CommentService {

	public void giveComment(String comment, long answerId, long userId);
	public void editComment(String comment, long id);
	public Comment getCommentById(long id);
	public void deleteComment(long id);
	public List<Comment> getAllCommentsByAnswerId(long answerId);
}
