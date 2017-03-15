package sgeorgiev.org.androidcoursework;

import android.graphics.Canvas;

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

    public ObstacleManager(int playerGap, int obstacleGap, int obstacleHeight, int colour) {
        this.playerGap = playerGap;
        this.playerGap = playerGap;
        this.obstacleHeight = obstacleHeight;
        this.colour = colour;
        this.obstacleGap = obstacleGap;
        startTime = System.currentTimeMillis();

        obstacles = new ArrayList<>();
        populateObstacles();
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
        float speed = Constants.SCREEN_HEIGHT/10000.0f;
        for (Obstacle ob : obstacles )
            ob.incrementY(speed * deltaTime);
        if(obstacles.get(obstacles.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT) {
            int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(0, new Obstacle(obstacleHeight, colour, xStart, obstacles.get(0).getRectangle().top - obstacleHeight - obstacleGap,
                    playerGap));
            obstacles.remove(obstacles.size() - 1);
        }
    }

    public void draw(Canvas canvas) {
        for (Obstacle ob : obstacles) {
            ob.draw(canvas);
        }
    }
}
