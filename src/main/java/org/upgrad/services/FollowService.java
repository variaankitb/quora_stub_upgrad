package org.upgrad.services;

import org.upgrad.models.Follow;

import javax.persistence.Entity;

public interface FollowService {

    Follow getFollow(long userId, int categoryId);
    void addFollow(int userId, int categoryId);
    void unFollow(int userId, int categoryId);
}
