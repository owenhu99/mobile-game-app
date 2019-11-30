package com.example.game.Games.MemoryGame.Entities;

public class Button {


    private String text;
    private int height;
    private int width;
    private int xLoc;
    private int yLoc;


    public Button(String text, int height, int width, int xLoc, int yLoc) {
        this.text = text;
        this.height = height;
        this.width = width;
        this.xLoc = xLoc;
        this.yLoc = yLoc;
    }


    public int getYLoc() {
        return yLoc;
    }

    public int getXLoc() {
        return xLoc;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getText() {
        return text;
    }


}
