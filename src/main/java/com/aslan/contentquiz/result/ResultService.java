package com.aslan.contentquiz.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    public static ResultRepository resultRepository;

    @Autowired
    public ResultService(ResultRepository resultRepository)
    {
        this.resultRepository = resultRepository;
    }

    public Result getResult(String uuid)
    {
        return resultRepository.getResult(uuid);
    }

    public Result getResult(String quizUuid, String username)
    {
        return resultRepository.getResult(quizUuid, username);
    }

    public List<Result> getResults()
    {
        return resultRepository.getResults();
    }

    public void addResult(Result result)
    {
        resultRepository.addResult(result);
    }

}