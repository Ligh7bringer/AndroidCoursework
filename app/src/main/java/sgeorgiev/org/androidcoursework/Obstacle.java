package sgeorgiev.org.androidcoursework;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Svetlozar Georgiev on 15/03/2017.
 */

public class Obstacle implements GameObject {

    private Rect rectangle;
    private Rect rectangle2;
    private int colour;

    public Obstacle(int rectHeight, int colour, int startX, int startY, int playerGap) {
        this.colour = colour;
        rectangle = new Rect(0, startY, startX, startY + rectHeight);
        rectangle2 = new Rect(startX + playerGap, startY, Constants.SCREEN_WIDTH, startY + rectHeight);
    }

    public Rect getRectangle() {
        return rectangle;
    }

    public void incrementY(float value) {
        rectangle.top += value;
        rectangle.bottom += value;
        rectangle2.top += value;
        rectangle2.bottom += value;
    }

    public boolean playerCollide(Player player) {
        // check for collisions on all sides
        if(rectangle.contains(player.getPlayer().left, player.getPlayer().top) ||
           rectangle.contains(player.getPlayer().right, player.getPlayer().top) ||
           rectangle.contains(player.getPlayer().left, player.getPlayer().bottom) ||
           rectangle.contains(player.getPlayer().right, player.getPlayer().bottom))
                return true; // return true if there was a collision
        //return false if there wasn't
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(colour);
        canvas.drawRect(rectangle, paint);
        canvas.drawRect(rectangle2, paint);
    }

    @Override
    public void update() {

    }
}
