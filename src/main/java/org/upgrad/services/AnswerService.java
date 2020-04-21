package org.upgrad.services;

import org.upgrad.models.Answer;

import java.util.Date;
import java.util.List;

public interface AnswerService {
    Answer getAnswerById(int id);
    void createAnswer(String content, Date createdDate, int userId, int questionId);
    void updateAnswer(int id, String content, Date modifiedDate);
    List<Answer> getAllAnswersToQuestion(int questionId);
    List<Answer> getAllAnswersByUser(int userId);
    void deleteAnswer(int answerId);
    int findUserIdfromAnswer(int id);

}
