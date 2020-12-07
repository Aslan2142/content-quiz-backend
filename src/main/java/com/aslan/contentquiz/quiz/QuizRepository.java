package com.aslan.contentquiz.quiz;

import com.aslan.contentquiz.MongoController;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class QuizRepository {

    private List<Quiz> quizzes = new ArrayList<>();

    public QuizRepository()
    {
        MongoController.quizCollection.find().forEach(doc -> {
            List<Document> questionsDoc = (List<Document>)doc.get("questions");
            List<QuizQuestion> questions = new ArrayList<>();
            for (Document questionDoc : questionsDoc)
            {
                questions.add(new QuizQuestion(questionDoc.getString("text"), questionDoc.getString("link"), questionDoc.getList("answers", String.class)));
            }

            Quiz quiz = new Quiz(doc.getString("name"), questions);
            quiz.setUuid(doc.getString("uuid"));
            quizzes.add(quiz);
        });
    }

    public Quiz getQuiz(String uuid)
    {
        for (Quiz quiz : quizzes)
        {
            if (quiz.getUuid().equals(uuid)) return quiz;
        }

        return null;
    }

    public List<Quiz> getQuizzes()
    {
        return quizzes;
    }

    public String addQuiz(Quiz quiz)
    {
        quizzes.add(quiz);

        List<Document> questions = new ArrayList<>();
        for (QuizQuestion question : quiz.getQuestions())
        {
            Map<String, Object> stringObjectMapQuestion = new HashMap<>();
            stringObjectMapQuestion.put("text", question.getText());
            stringObjectMapQuestion.put("link", question.getLink());
            stringObjectMapQuestion.put("answers", question.getAnswers());
            questions.add(new Document(stringObjectMapQuestion));
        }

        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("uuid", quiz.getUuid());
        stringObjectMap.put("name", quiz.getName());
        stringObjectMap.put("questions", questions);
        MongoController.quizCollection.insertOne(new Document(stringObjectMap));

        return quiz.getUuid();
    }

    public void updateQuiz(String uuid, Quiz updatedQuiz)
    {
        updatedQuiz.setUuid(uuid);

        for (int i = 0; i < quizzes.size(); i++)
        {
            if (quizzes.get(i).getUuid().equals(uuid))
            {
                quizzes.set(i, updatedQuiz);
            }
        }

        List<Document> questions = new ArrayList<>();
        for (QuizQuestion question : updatedQuiz.getQuestions())
        {
            Map<String, Object> stringObjectMapQuestion = new HashMap<>();
            stringObjectMapQuestion.put("text", question.getText());
            stringObjectMapQuestion.put("link", question.getLink());
            stringObjectMapQuestion.put("answers", question.getAnswers());
            questions.add(new Document(stringObjectMapQuestion));
        }

        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("uuid", updatedQuiz.getUuid());
        stringObjectMap.put("name", updatedQuiz.getName());
        stringObjectMap.put("questions", questions);
        MongoController.quizCollection.updateOne(new Document("uuid", uuid), new Document("$set", new Document(stringObjectMap)));
    }

    public void removeQuiz(String uuid)
    {
        for (int i = 0; i < quizzes.size(); i++)
        {
            if (quizzes.get(i).getUuid().equals(uuid))
            {
                quizzes.remove(i);
                break;
            }
        }

        MongoController.quizCollection.deleteOne(new Document("uuid", uuid));
    }

    public void clearQuizzes()
    {
        quizzes.clear();

        MongoController.quizCollection.deleteMany(new Document());
    }

}