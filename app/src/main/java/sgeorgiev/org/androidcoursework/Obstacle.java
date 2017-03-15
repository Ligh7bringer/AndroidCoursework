package sgeorgiev.org.androidcoursework;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Svetlozar Georgiev on 15/03/2017.
 */

public class Obstacle implements GameObject {

    private Rect rectangle;
    private int colour;

    public Obstacle(Rect rectangle, int colour) {
        this.rectangle = rectangle;
        this.colour = colour;
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
    }

    @Override
    public void update() {

    }
}
