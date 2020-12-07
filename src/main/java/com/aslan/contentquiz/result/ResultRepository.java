package com.aslan.contentquiz.result;

import com.aslan.contentquiz.MongoController;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ResultRepository {

    private List<Result> results = new ArrayList<>();

    public ResultRepository()
    {
        MongoController.resultCollection.find().forEach(doc -> {
            Result result = new Result(doc.getString("quizUuid"), doc.getString("username"), doc.getList("answers", Integer.class));
            result.setUuid(doc.getString("uuid"));
            results.add(result);
        });
    }

    public Result getResult(String uuid)
    {
        for (Result result : results)
        {
            if (result.getUuid().equals(uuid)) return result;
        }

        return null;
    }

    public Result getResult(String quizUuid, String username)
    {
        for (Result result : results)
        {
            if (result.getQuizUuid().equals(quizUuid) && result.getUsername().equalsIgnoreCase(username)) return result;
        }

        return null;
    }

    public List<Result> getResults()
    {
        return results;
    }

    public void addResult(Result result)
    {
        for (int i = 0; i < results.size(); i++)
        {
            Result item = results.get(i);
            if (item.getUsername().equalsIgnoreCase(result.getUsername()) && item.getQuizUuid().equals(result.getQuizUuid()))
            {
                results.remove(i);
                i--;

                Map<String, Object> stringObjectMap = new HashMap<>();
                stringObjectMap.put("username", result.getUsername());
                stringObjectMap.put("quizUuid", result.getQuizUuid());
                MongoController.resultCollection.deleteOne(new Document(stringObjectMap));
            }
        }

        results.add(result);

        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("uuid", result.getUuid());
        stringObjectMap.put("quizUuid", result.getQuizUuid());
        stringObjectMap.put("username", result.getUsername());
        stringObjectMap.put("answers", result.getAnswers());
        MongoController.resultCollection.insertOne(new Document(stringObjectMap));
    }

    public void removeResultsForQuiz(String uuid)
    {
        for (int i = 0; i < results.size(); i++)
        {
            if (results.get(i).getQuizUuid().equals(uuid))
            {
                results.remove(i);
                break;
            }
        }

        MongoController.resultCollection.deleteMany(new Document("quizUuid", uuid));
    }

    public void clearResults()
    {
        results.clear();

        MongoController.resultCollection.deleteMany(new Document());
    }

}