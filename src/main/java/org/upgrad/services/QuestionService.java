package org.upgrad.services;


import java.util.List;

import org.upgrad.models.Question;

public interface QuestionService {

    Question getQuestionById(int id);
    List<Question> getAllQuestionsByUser(int userId);
    void deleteQuestion(int id);
    List<Question> getAllQuestions();
}
