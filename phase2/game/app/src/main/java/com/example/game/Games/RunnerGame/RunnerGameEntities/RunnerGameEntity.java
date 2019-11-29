package com.example.game.Games.RunnerGame.RunnerGameEntities;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * RunnerGameEntity is an entity of the RunnerGame. This includes the object the player controls, the
 * enemies that the player must avoid, as well as the coins that the player can collect.
 *
 * RunnerGameEntity is an abstract class, with mandatory methods draw(), move(), ...
 *
 * As of november 2019, every object is a rectangle. (unfortunately.)
 *
 * THE INITIALIZATION OF A RunnerGameEntity should take control of where the entity spawns.
 *
 */
public abstract class RunnerGameEntity {
    // RUNNER ENTITIES FALL DOWNWARDS: THIS MEANS Y COORD STARTS AT 0, AND Y SHOULD INCREASE AS TIME
    // PASSES

    // integers x and y are the centers of each RunnerGameEntity.
    int x;
    int y;
    // integers x_dim and y_dim are half of the total dimension of the RunnerGameEntity.
    // should and can be calculated as a percentage of the total gameboard.
    int x_dim;
    int y_dim;

    // the width and height of the board (screen that the game is being displayed on). These are
    // initialized in methods setBoardX() setBoardY()
    static int x_board;
    static int y_board;

    // boolean below_bottom is used to determine if the top of the entity is below the bottom of the
    // screen. if it is, the object should be removed from the gameboard.
    protected boolean below_bottom = false;

    int speed;
    private String on_contact;

    protected Paint entity_color = new Paint();



    RunnerGameEntity(){}

    public abstract void draw(Canvas canvas);

    public abstract void move();

    public String getOn_contact(){ return on_contact; }

    public static void setBoard_x(int width){ RunnerGameEntity.x_board = width;
        System.out.println("RGAMEENT_XBOARD" + RunnerGameEntity.x_board);}
    public static void setBoard_y(int height){ RunnerGameEntity.y_board = height;
        System.out.println("RGAMEENT_XBOARD" + RunnerGameEntity.y_board);}



}
