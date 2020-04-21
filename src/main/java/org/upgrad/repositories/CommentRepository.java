package org.upgrad.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO comment(content, user_id, answer_id, date, modified_on) values(?1,?2,?3,?4,?5)")
	public void giveComment(String content, long userId, long answerId, Date date, Date modifiedOn);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE comment SET content=?1 and modified_on=?3 where id=?2")
	public void editComment(String content, long id, Date modifiedOn);
	
	@Query(nativeQuery=true, value = "SELECT * from comment where id=?1")
	public Comment getCommentById(long id);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM comment where id=?1")
	public void deleteComment(long id);
	
	@Query(nativeQuery=true, value = "SELECT * from comment where answer_id=?1")
	public List<Comment> getAllCommentsByAnswer(long answerId);
}
