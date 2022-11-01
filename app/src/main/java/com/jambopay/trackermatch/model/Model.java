package com.jambopay.trackermatch.model;

public class Model {
    private String inputNumber;
    private String randomNumber;
    private String timeMatchMilliseconds;
    private String sameSecondMatchMilliseconds;

    public Model(String inputNumber, String randomNumber, String timeMatchMilliseconds, String sameSecondMatchMilliseconds) {
        this.inputNumber = inputNumber;
        this.randomNumber = randomNumber;
        this.timeMatchMilliseconds = timeMatchMilliseconds;
        this.sameSecondMatchMilliseconds = sameSecondMatchMilliseconds;
    }

    public Model() {
    }

    public String getInputNumber() {
        return inputNumber;
    }

    public void setInputNumber(String inputNumber) {
        this.inputNumber = inputNumber;
    }

    public String getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(String randomNumber) {
        this.randomNumber = randomNumber;
    }

    public String getTimeMatchMilliseconds() {
        return timeMatchMilliseconds;
    }

    public void setTimeMatchMilliseconds(String timeMatchMilliseconds) {
        this.timeMatchMilliseconds = timeMatchMilliseconds;
    }

    public String getSameSecondMatchMilliseconds() {
        return sameSecondMatchMilliseconds;
    }

    public void setSameSecondMatchMilliseconds(String sameSecondMatchMilliseconds) {
        this.sameSecondMatchMilliseconds = sameSecondMatchMilliseconds;
    }



    @Override
    public String toString() {
        return "Model{" +
                "inputNumber='" + inputNumber + '\'' +
                ", randomNumber='" + randomNumber + '\'' +
                ", timeMatchMilliseconds='" + timeMatchMilliseconds + '\'' +
                ", sameSecondMatchMilliseconds='" + sameSecondMatchMilliseconds + '\'' +
                '}';
    }
}
