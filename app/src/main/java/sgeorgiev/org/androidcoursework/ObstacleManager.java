package sgeorgiev.org.androidcoursework;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by Svetlozar Georgiev on 15/03/2017.
 */

public class ObstacleManager {
    private ArrayList<Obstacle> obstacles;
    private int playerGap;
    private int obstacleGap;
    private int obstacleHeight;
    private int colour;
    private long startTime;
    private long initTime;
    private int score = 0;

    public ObstacleManager(int playerGap, int obstacleGap, int obstacleHeight, int colour) {
        this.playerGap = playerGap;
        this.playerGap = playerGap;
        this.obstacleHeight = obstacleHeight;
        this.colour = colour;
        this.obstacleGap = obstacleGap;
        startTime = initTime = System.currentTimeMillis();

        obstacles = new ArrayList<>();
        populateObstacles();
    }

    public boolean playerCollide(Player player) {
        for(Obstacle ob : obstacles) {
            if(ob.playerCollide(player))
                return true;
        }
        return false;
    }

    private void populateObstacles() {
        int currY = -5 * Constants.SCREEN_HEIGHT/4;
        while(currY < 0 ) {
            int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(new Obstacle(obstacleHeight, colour, xStart, currY, playerGap));
            currY += obstacleHeight + obstacleGap;
        }
    }

    public void update() {
        int deltaTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = (float)(Math.sqrt(1 + (startTime - initTime)/2000.0)) * Constants.SCREEN_HEIGHT/10000.0f;
        for (Obstacle ob : obstacles )
            ob.incrementY(speed * deltaTime);
        if(obstacles.get(obstacles.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT) {
            int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(0, new Obstacle(obstacleHeight, colour, xStart, obstacles.get(0).getRectangle().top - obstacleHeight - obstacleGap,
                    playerGap));
            obstacles.remove(obstacles.size() - 1);
            score++;
        }
    }

    public void draw(Canvas canvas) {
        for (Obstacle ob : obstacles)
            ob.draw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.MAGENTA);
        paint.setTextSize(100);
        canvas.drawText("" + score, 50, 50 + paint.descent() - paint.ascent(), paint);
    }
}
