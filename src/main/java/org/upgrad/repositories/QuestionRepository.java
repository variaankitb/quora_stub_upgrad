package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Question;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM question WHERE id = ?1")
    Question getQuestionById(int id);

    @Query(nativeQuery = true, value = "select * FROM question WHERE user_id = ?1")
    List<Question> getAllQuestionsByUser(int userId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM question WHERE id = ?1")
    void deleteQuestionById(int id);

}
