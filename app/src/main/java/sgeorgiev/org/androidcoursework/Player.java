package sgeorgiev.org.androidcoursework;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Svetlozar Georgiev on 15/03/2017.
 */

public class Player implements GameObject {

    private Rect rectangle;
    private int colour;

    public Player(Rect rectangle, int colour) {
        this.rectangle = rectangle;
        this.colour = colour;
    }

    public Rect getPlayer() {
        return rectangle;
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

    public void update(Point point) {
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2 );
    }
}
