package com.example.myapplication;

public class QuizTracker {

    //1. create encapsulated members
    private int correctAnswerNum = 0;
    private int incorrectAnswerNum = 0;
    private int questionNum = 0;
    private String name;

    //2. provide a static instance of self
    private static QuizTracker quizTracker;

    //3.  override the no-arg constructor as private
    private QuizTracker(){};

    //4. provide a static getInstance() method
    public static QuizTracker getInstance(){
        if(null == quizTracker){
            quizTracker = new QuizTracker();
            return  quizTracker;
        } else {
            return  quizTracker;
        }
    }

    public int getCorrectAnswerNum() {
        return correctAnswerNum;
    }

    public void setCorrectAnswerNum(int correctAnswerNum) {
        this.correctAnswerNum = correctAnswerNum;
    }

    public int getIncorrectAnswerNum() {
        return incorrectAnswerNum;
    }

    public void setIncorrectAnswerNum(int incorrectAnswerNum) {
        this.incorrectAnswerNum = incorrectAnswerNum;
    }

    public int getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void reset(){
        setName("");
        setQuestionNum(1);
        setIncorrectAnswerNum(0);
        setCorrectAnswerNum(0);
    }

    public void start(String name){
        setName(name);
        setQuestionNum(1);
        setIncorrectAnswerNum(0);
        setCorrectAnswerNum(0);
    }

    public void again(){
        setQuestionNum(1);
        setIncorrectAnswerNum(0);
        setCorrectAnswerNum(0);
    }



}
