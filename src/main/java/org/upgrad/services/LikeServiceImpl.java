package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Likes;
import org.upgrad.repositories.LikeRepository;

@Service
public class LikeServiceImpl implements LikeService {

    private LikeRepository likeRepository;

    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public Likes getLikes(long userId, int answerId) {
        return likeRepository.getLikes(userId, answerId);
    }

    @Override
    public void giveLikes(int userId, int answerId) {
        likeRepository.giveLikes(userId, answerId);
    }

    @Override
    public void unlike(int userId, int answerId) {
        likeRepository.unlike(userId, answerId);
    }
}
