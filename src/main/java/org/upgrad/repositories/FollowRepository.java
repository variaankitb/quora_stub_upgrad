package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Follow;

import javax.transaction.Transactional;

@Repository
public interface FollowRepository extends CrudRepository<Follow, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM likes WHERE user_id = ?1 and category_id = ?2")
    Follow getFollows(long userId, int categoryId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO follow(user_id, category_id) VALUES (?1, ?2)")
    void addFollowCategory(int userId, int categoryId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM likes WHERE user_id = ?1 and answer_id = ?2")
    void unFollow(int userId, int categoryId);
}
