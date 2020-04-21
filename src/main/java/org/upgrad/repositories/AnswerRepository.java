package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Answer;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM answer WHERE id = ?1")
    Answer getAnswerById(int id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO answer(ans, date, user_id, question_id) VALUES (?1, ?2, ?3, ?4)")
    void createAnswer(String content, Date date, int userId, int questionId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE answer SET ans = ?2, modifiedon = ?3 WHERE id = ?1")
    void updateAnswer(int id, String content, Date modifiedDate);

    @Query(nativeQuery = true, value = "SELECT * FROM answer WHERE question_id = ?1 ")
    List<Answer> getAllAnswersToQuestion(int questionId);

    @Query(nativeQuery = true, value = "SELECT * FROM answer WHERE user_id = ?1")
    List<Answer> getAllAnswersByUser(int userId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM answer WHERE id = ?1")
    void deleteAnswerById(int id);


}
