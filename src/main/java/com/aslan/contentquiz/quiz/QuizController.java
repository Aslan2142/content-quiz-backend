package com.aslan.contentquiz.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/quiz")
@RestController
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService)
    {
        this.quizService = quizService;
    }

    @GetMapping(path = "{uuid}")
    public Quiz quiz(@PathVariable("uuid") String uuid)
    {
        return quizService.getQuiz(uuid);
    }

    @GetMapping
    public List<Quiz> quizzes()
    {
        return quizService.getQuizzes();
    }

    @PostMapping
    public String quizAdd(@RequestBody Quiz quiz)
    {
        return quizService.addQuiz(quiz);
    }

    @PutMapping(path = "{uuid}")
    public void quizUpdate(@PathVariable("uuid") String uuid, @RequestBody Quiz updatedQuiz)
    {
        quizService.updateQuiz(uuid, updatedQuiz);
    }

    @DeleteMapping(path = "{uuid}")
    public void quizRemove(@PathVariable("uuid") String uuid)
    {
        quizService.removeQuiz(uuid);
    }

    @DeleteMapping
    public void quizzesClear()
    {
        quizService.clearQuizzes();
    }

}
