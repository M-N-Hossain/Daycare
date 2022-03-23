package com.example.daycare2;

public class User {

    String firstColumn;
    String secondColumn;
    String thirdColumn;
    String fourthColumn;
    String fifthColumn;



    public User(String firstColumn, String secondColumn, String thirdColumn, String fourthColumn, String fifthColumn) {
        this.firstColumn = firstColumn;
        this.secondColumn = secondColumn;
        this.thirdColumn = thirdColumn;
        this.fourthColumn = fourthColumn;
        this.fifthColumn = fifthColumn;
    }



    public String getFirstColumn() {
        return firstColumn;
    }

    public String getSecondColumn() {
        return secondColumn;
    }

    public String getThirdColumn() {
        return thirdColumn;
    }

    public String getFourthColumn() {
        return fourthColumn;
    }

    public String getFifthColumn() {
        return fifthColumn;
    }

}
