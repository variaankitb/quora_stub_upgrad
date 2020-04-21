package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Question;
import org.upgrad.repositories.QuestionRepository;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question getQuestionById(int id) {
        return questionRepository.getQuestionById(id);
    }

    @Override
    public List<Question> getAllQuestionsByUser(int userId) {
        return questionRepository.getAllQuestionsByUser(userId);
    }

    @Override
    public void deleteQuestion(int id) {
        questionRepository.deleteQuestionById(id);
    }

	@Override
	public List<Question> getAllQuestions() {
		return (List<Question>) questionRepository.findAll();
	}
}
