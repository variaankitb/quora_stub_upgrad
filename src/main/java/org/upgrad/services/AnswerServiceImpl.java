package org.upgrad.services;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation;
import org.springframework.stereotype.Service;
import org.upgrad.models.Answer;
import org.upgrad.repositories.AnswerRepository;

import java.util.Date;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public void createAnswer(String content, Date createdDate, int userId, int questionId) {
        answerRepository.createAnswer(content, createdDate, userId, questionId);
    }

    @Override
    public void updateAnswer(int id, String content, Date modifiedDate) {
        answerRepository.updateAnswer(id, content, modifiedDate);
    }

    @Override
    public List<Answer> getAllAnswersToQuestion(int questionId) {
        return answerRepository.getAllAnswersToQuestion(questionId);
    }

    @Override
    public List<Answer> getAllAnswersByUser(int userId) {
        return answerRepository.getAllAnswersByUser(userId);
    }

    @Override
    public void deleteAnswer(int answerId) {
        answerRepository.deleteAnswerById(answerId);
    }

    @Override
    public Answer getAnswerById(int id) {
        return answerRepository.getAnswerById(id);
    }

    @Override
    public int findUserIdfromAnswer(int id) {
        return (int) getAnswerById(id).getUser().getId();
    }
}
