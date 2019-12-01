package com.example.game.Games.MemoryGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.game.Games.MemoryGame.Entities.Button;
import com.example.game.Games.MemoryGame.Entities.MemoryTile;

public class MemoryDrawer {

    private int width;
    private int height;

    private final int lineThickness = 5;
    private final int boxLineGap = 20;
    private final int uiOffset = 300;
    private int boxWidth;
    private int boxHeight;
    private Paint gridPaint = new Paint();
    private Paint textPaint = new Paint();

    MemoryDrawer(int width, int height, int boxWidth, int boxHeight){
        this.width = width;
        this.height = height;
        this.boxWidth = boxWidth;
        this.boxHeight = boxHeight;

        gridPaint.setColor(Color.WHITE);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(60);
    }

    protected void draw(Canvas canvas, String state, Button btn, MemoryTimer timer, int remaining,
                        int score, MemoryTile[][] grid) {

        drawGrid(canvas, grid);
        drawTargets(canvas, grid, state);
        drawStats(canvas, timer, remaining, score);

        if (state == "memorize") {
            drawButton(btn, canvas);
        }
    }


    private void drawButton(Button btn, Canvas canvas) {
        canvas.drawRect(btn.getXLoc(), btn.getYLoc(),
                btn.getXLoc() + btn.getWidth(), btn.getYLoc() + btn.getHeight(),
                textPaint);
        canvas.drawText(btn.getText(), btn.getXLoc() + (btn.getWidth() / 2), btn.getYLoc() +
                (btn.getHeight() / 2) + btn.getTextPaint().getTextSize() / 2, btn.getTextPaint());
    }

    private void drawStats(Canvas canvas, MemoryTimer timer, int remaining, int score){
        canvas.drawText("Time Left:" + timer.getCurrentTime() ,50, 50, textPaint);
        canvas.drawText("Remaining: " + remaining,50, 150, textPaint);
        canvas.drawText("Score: " + score,50, 250, textPaint);

    }

    private void drawGrid(Canvas canvas, MemoryTile[][] grid) {
        canvas.drawLine(0, 300, width, 300, gridPaint);
        canvas.drawLine(0, height, width, height, gridPaint);
        canvas.drawLine(0, 300, 0, height, gridPaint);
        canvas.drawLine(width, 300, width, height, gridPaint);

        for (int i = 0; i < grid.length; i++) {
            //vertical grid line
            float left = boxWidth * (i + 1);
            float right = left + lineThickness;
            float top = uiOffset;
            float bottom = height;
            canvas.drawRect(left, top, right, bottom, gridPaint);

            //horizontal grid line
            float left2 = 0;
            float right2 = width;
            float top2 = boxHeight * (i + 1) + uiOffset;
            float bottom2 = top2 + lineThickness;

            canvas.drawRect(left2, top2, right2, bottom2, gridPaint);
        }
    }

    private void drawTargets(Canvas canvas, MemoryTile[][] grid, String state){
        float startX;
        float startY;
        float endX;
        float endY;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                startX = (boxWidth * i) + boxLineGap;
                startY = uiOffset + (boxHeight * j) + boxLineGap;
                endX = (boxWidth * (i + 1)) - boxLineGap;
                endY = uiOffset + (boxHeight * (j + 1)) - boxLineGap;
                canvas.drawRect(startX, startY, endX, endY, grid[i][j].getColor(state));
            }
        }
    }
}
