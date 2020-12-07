package com.aslan.contentquiz.quiz;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class QuizQuestion {

    private String text;
    private String link;
    private List<String> answers;

    public QuizQuestion(
            @JsonProperty("text") String text,
            @JsonProperty("link") String link,
            @JsonProperty("answers") List<String> answers
    )
    {
        this.text = text;
        this.link = link;
        this.answers = answers;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    public List<String> getAnswers() {
        return answers;
    }

}