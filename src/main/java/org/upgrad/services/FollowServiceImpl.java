package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Follow;
import org.upgrad.repositories.FollowRepository;

@Service
public class FollowServiceImpl implements FollowService {

    private FollowRepository followRepository;

    public FollowServiceImpl(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @Override
    public Follow getFollow(long userId, int categoryId) {
        return followRepository.getFollows(userId, categoryId);
    }

    @Override
    public void addFollow(int userId, int categoryId) {
        followRepository.getFollows(userId, categoryId);
    }

    @Override
    public void unFollow(int userId, int categoryId) {
        followRepository.getFollows(userId, categoryId);
    }
}
