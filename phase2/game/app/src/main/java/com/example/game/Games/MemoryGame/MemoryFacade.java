package com.example.game.Games.MemoryGame;

import android.graphics.Canvas;
import android.graphics.Color;

import com.example.game.Games.Game;
import com.example.game.Games.MemoryGame.Entities.Button;
import com.example.game.Games.MemoryGame.Entities.MemoryTile;

import java.util.Random;

public class MemoryFacade extends Game {

    private MemoryGame game;
    private MemoryDrawer drawer;
    private MemoryTimer timer;

    private Button startBtn;

    public MemoryFacade(int width, int height) {
        super(width, height);
        game = new MemoryGame(width, height);
        timer = new MemoryTimer(this);
        startGame();

        drawer = new MemoryDrawer(width, height, game.getBoxWidth(), game.getBoxHeight());

        startBtn = new Button("Start", height / 12, width / 5,
                width/2 + 300, 100);
    }

    /**
     *
     */
    private void startGame(){
        game.createGrid();
        timer.start();
    }

    /**
     * Displays the rectangles and text representing the choices, and results of the game
     */
    @Override
    protected void draw(Canvas canvas) {
        drawer.draw(canvas, game.getState(), startBtn, timer, game.getRemaining(), game.getScore(),
                game.getGrid());
    }


    /**
     * Getting the user's choice by recording where the screen was tapped and running the game, end
     * the game after.
     */
    @Override
    protected void receiveInput(int x, int y) {
        game.receiveInput(x, y, startBtn);
    }

    public void endGame(){
        super.endGame(game.getScore());
    }

}
