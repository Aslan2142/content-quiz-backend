package com.aslan.contentquiz.result;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public class Result {

    private String uuid;
    private String quizUuid;
    private String username;
    private List<Integer> answers;

    public Result(
            @JsonProperty("quizuuid") String quizUuid,
            @JsonProperty("username") String username,
            @JsonProperty("answers") List<Integer> answers
    )
    {
        this.uuid = UUID.randomUUID().toString();
        this.quizUuid = quizUuid;
        this.username = username;
        this.answers = answers;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getQuizUuid() {
        return quizUuid;
    }

    public String getUsername() {
        return username;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

}