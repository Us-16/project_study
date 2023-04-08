package com.example.a16sserver.teacher;

public class TestDO {
    private Long id;
    private String questionTitle;
    private String subject;
    private int view;
    private double correct;

    public TestDO(Long id, String questionTitle, String subject, int view, double correct) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.subject = subject;
        this.view = view;
        this.correct = correct;
    }

    public Long getId() {
        return id;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public String getSubject() {
        return subject;
    }

    public int getView() {
        return view;
    }

    public double getCorrect() {
        return correct;
    }
}
