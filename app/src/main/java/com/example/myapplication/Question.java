package com.example.myapplication;

import java.util.HashSet;
import java.util.Set;

public class Question {

    private String latin;
    private String greek;
    private String english;
    private Set<String> wrongAnswers = new HashSet<>();

    public Question(String english, String latin, String greek) {
        this.latin = latin;
        this.greek = greek;
        this.english = english;
    }

    public String getLatin() {
        return latin;
    }

    public String getGreek() {
        return greek;
    }

    public String getEnglish() {
        return english;
    }

    public Set<String> getWrongAnswers() {
        return wrongAnswers;
    }

    public void addWrongAnswer(String wrong){
        wrongAnswers.add(wrong);
    }

    public String getQuestionText(){
        return ("Which is "+english+"?");
    }

}
