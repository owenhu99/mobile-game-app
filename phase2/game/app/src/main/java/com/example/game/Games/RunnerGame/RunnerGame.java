package com.example.game.Games.RunnerGame;

import android.graphics.Canvas;


import com.example.game.Games.Game;
import com.example.game.Games.RunnerGame.RunnerGameEntities.Coin;
import com.example.game.Games.RunnerGame.RunnerGameEntities.Player;
import com.example.game.Games.RunnerGame.RunnerGameEntities.RunnerGameEntity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Description of Game:
 *      Runner is a game where the user controls an object on the board. The goal of the user
 *      is to survive for as long as possible, avoiding other objects on the field that will kill them
 *      As of November 2019, the user will be able to collect coins, which will help them in the shop.
 *
 * RunnerGame is essentially a facade for the overall game. The RunnerGame interface is used to create
 * instance of a game of Runner, controlling the movement of the player, making sure the game is updated,
 * and "drawing" everything. Every object of the game board is a GameEntity, meaning it should
 * contain a draw method, with instructions on how to draw the object.
 *
 * In this game, the runner is running upwards, with the objects that must be avoided coming down.
 * The controller that the user uses to move the player object is at the bottom of the screen, and
 * will be invisible after a couple seconds.
 *
 *
 * the spawning of enemies and entities will occur in the draw method.
 *
 * gameBoard is expected to have a maximum of like 20 objects, so cycling over it shouldn't be too
 * memory intense.
 *
 *
 *
 */
public class RunnerGame extends Game {
    private ArrayList<RunnerGameEntity> gameBoard;


    private int entitiesUntilCoin = 10;
    private int entittiesSpawned = 0;


    // variables that deal with spawning
    private Random spawner = new Random();
    //entities is the total amount of entities programmed. whenever a new entity is added, this must
    // change. I know is shit, but no other solution known.




    private int secondsPlayed;
    private int entities = 2;
    
    
    
    private int coins_collected = 0;

    private Player player;



    public RunnerGame(int width, int height){
        super(width, height);

        RunnerGameEntity.setBoard_y(height);
        RunnerGameEntity.setBoard_x(width);

        Player player = new Player();
        this.gameBoard = new ArrayList<>();
        gameBoard.add(player);
    }

    @Override
    protected void draw(Canvas canvas) {
        for (RunnerGameEntity entity: gameBoard) {
            entity.draw(canvas);
        }

    }



    // need to find continuous input
    // change receive input of gameview
    @Override
    protected void receiveInput(int x, int y) {
        /**
        if(x > (0.5 * this.width)){
            this.player.move(1);
        }
        else{
            player.move(2);
        }
         */
    }

    @Override
    protected int endGame() {
        return 0;
    }

    @Override
    protected void reset() {
        this.gameBoard = new ArrayList<RunnerGameEntity>();

    }

    // in charge of spawning entities in
    @Override
    protected void updateGame(int secondsPlayed) {
        spawn();

        for (RunnerGameEntity entity: gameBoard
             ) {
            entity.move();
        }
    }

    private void spawn(){
        while(gameBoard.size() < entities){
            spawnHelper();
        }
    }
    private void spawnHelper(){
        this.gameBoard.add(new Coin());
    }

    private void detectCollision(){

    }



}
