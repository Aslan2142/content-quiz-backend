package com.aslan.contentquiz.quiz;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public class Quiz {

    private String uuid;
    private String name;
    private List<QuizQuestion> questions;

    public Quiz(
            @JsonProperty("name") String name,
            @JsonProperty("questions") List<QuizQuestion> questions
    )
    {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.questions = questions;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public List<QuizQuestion> getQuestions() {
        return questions;
    }

}