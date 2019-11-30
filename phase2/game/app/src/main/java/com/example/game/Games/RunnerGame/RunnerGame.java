package com.example.game.Games.RunnerGame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;


import com.example.game.Games.Game;
import com.example.game.Games.RunnerGame.RunnerGameEntities.Coin;
import com.example.game.Games.RunnerGame.RunnerGameEntities.Enemies;
import com.example.game.Games.RunnerGame.RunnerGameEntities.Player;
import com.example.game.Games.RunnerGame.RunnerGameEntities.RunnerGameEntity;
import com.example.game.Games.RunnerGame.RunnerGameEntities.RunnerGameEntityFactory;
import com.example.game.R;

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
    private RunnerGameEntityFactory runnerGameEntityFactory;

    private int difficulty = 10;



    public RunnerGame(int width, int height){
        super(width, height);

        RunnerGameEntity.setBoard_y(height);
        RunnerGameEntity.setBoard_x(width);

        Player player = new Player(0);

        this.runnerGameEntityFactory = new RunnerGameEntityFactory();
        this.player = player;
        this.gameBoard = new ArrayList<>();

        Bitmap coinBMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.goldenpepe);
        Bitmap enemyBMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.feelsbadman);
        Bitmap friendlyBMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.feelsgoodman);
        Player.setPlayerBMP(friendlyBMP);
        Coin.setCoinBMP(coinBMP);
        Enemies.setBmp(enemyBMP);
    }

    @Override
    protected void draw(Canvas canvas) {
        updateGame(this.secondsPlayed);

        for (RunnerGameEntity entity: gameBoard) {
            entity.draw(canvas);
        }
        player.draw(canvas);
        detectCollision();
        removeEntities();
    }



    // need to find continuous input
    // change receive input of gameview
    @Override
    protected void receiveInput(int x, int y) {
        if(y > .8*height){
            //left
            if(x < .25*width){
                player.move(1);
            }
            //right
            else if(x > .75*width){
                player.move(2);
            } else{
                //up
                if (y > .9*height){
                    player.move(3);
                }
                //down
                else{
                    player.move(4);
                }
            }
        }
    }


    // in charge of spawning entities in
    private void updateGame(int secondsPlayed) {
        if (secondsPlayed % 10 == 0){
            this.difficulty += 10;
        }

        //spawn entities according to rules.
        spawn();
        for (RunnerGameEntity entity: gameBoard) {
            entity.move();
        }
    }

    private void spawn(){
        while(gameBoard.size() < difficulty/3){
            gameBoard.add(runnerGameEntityFactory.createRunnerGameEntity(difficulty));
        }
    }


    private void detectCollision(){
        int[] playerBounds = player.getBounds();
        int[] entityBounds;
        boolean[] inBounds = {false, false};
        for (RunnerGameEntity entity: gameBoard
             ) {
            entityBounds = entity.getBounds();
            inBounds[0] = ((playerBounds[0] < entityBounds[0] && entityBounds[0] < playerBounds[1])||(playerBounds[0] < entityBounds[1] && entityBounds[1] < playerBounds[1]));
            inBounds[1] = ((playerBounds[2] < entityBounds[2] && entityBounds[2] < playerBounds[3])||(playerBounds[2] < entityBounds[3] && entityBounds[3] < playerBounds[3]));
            if(inBounds[0] && inBounds[1]){
                switch (entity.getOnContact()){
                    case "Coin":
                        coins_collected++;
                    case "Enemy":
                        endGame(this.secondsPlayed);
                        break;
                }

            }
            inBounds[0] = false;
            inBounds[1] = false;

        }
    }

    private void removeEntities(){
        for (RunnerGameEntity entity: gameBoard) {
            if(entity.isBelowBottom()){
                gameBoard.remove(entity);
            }
        }
    }


}
