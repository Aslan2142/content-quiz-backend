package com.aslan.contentquiz.quiz;

import com.aslan.contentquiz.result.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    public static QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository)
    {
        this.quizRepository = quizRepository;
    }

    public Quiz getQuiz(String uuid)
    {
        return quizRepository.getQuiz(uuid);
    }

    public List<Quiz> getQuizzes()
    {
        return quizRepository.getQuizzes();
    }

    public String addQuiz(Quiz quiz)
    {
        return quizRepository.addQuiz(quiz);
    }

    public void updateQuiz(String uuid, Quiz updatedQuiz)
    {
        quizRepository.updateQuiz(uuid, updatedQuiz);
    }

    public void removeQuiz(String uuid)
    {
        quizRepository.removeQuiz(uuid);

        ResultService.resultRepository.removeResultsForQuiz(uuid);
    }

    public void clearQuizzes()
    {
        quizRepository.clearQuizzes();

        ResultService.resultRepository.clearResults();
    }

}