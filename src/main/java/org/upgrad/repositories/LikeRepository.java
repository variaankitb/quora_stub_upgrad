package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Likes;

import javax.transaction.Transactional;

@Repository
public interface LikeRepository extends CrudRepository<Likes, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM likes WHERE user_id = ?1 and answer_id = ?2")
    Likes getLikes(long userId, int answerId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO likes(user_id, answer_id) VALUES (?1, ?2)")
    void giveLikes(int userId, int answerId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM likes WHERE user_id = ?1 and answer_id = ?2")
    void unlike(int userId, int answerId);

}
