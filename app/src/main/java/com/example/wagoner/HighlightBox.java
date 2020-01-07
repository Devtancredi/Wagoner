package com.example.wagoner;

public class HighlightBox {

    private int xPos, yPos, xWidth, yHeight, score;
    private String color;

    public HighlightBox() {
        xPos = 0;
        yPos = 0;
        xWidth = 0;
        yHeight = 0;
        score = 0;
        color = "yellow";
    }

    public HighlightBox(int xPos, int yPos, int xWidth, int yHeight) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xWidth = xWidth;
        this.yHeight = yHeight;
        score = 0;
        color = "yellow";
    }

    public void createHighlightBox(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void setBoxDimensions(int xWidth, int yHeight) {
        this.xWidth = xWidth;
        this.yHeight = yHeight;
    }

    public void increaseScore() {
        score++;
        if (score >= 5) {
            color = "blue";
        }
    }

    public void decreaseScore() {
        score--;
        if (score < 5) {
            color = "yellow";
        }
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public int getXWidth() {
        return xWidth;
    }

    public int getYHeight() {
        return yHeight;
    }

    public int getScore() {
        return score;
    }
}
