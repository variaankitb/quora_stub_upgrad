package org.upgrad.services;

import org.upgrad.models.Follow;
import org.upgrad.models.Likes;

public interface LikeService {

    Likes getLikes(long userId, int answerId);
    void giveLikes(int userId, int answerId);
    void unlike(int userId, int answerId);



}
