package sgeorgiev.org.androidcoursework;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Svetlozar Georgiev on 15/03/2017.
 */

public class Player implements GameObject {

    private Rect rectangle;
    private int colour;
    private AnimationManager animationManager;
    private Animation idle;
    private Animation walkRight;
    private Animation walkLeft;

    public Player(Rect rectangle, int colour) {
        this.rectangle = rectangle;
        this.colour = colour;

        BitmapFactory bf = new BitmapFactory();
        Bitmap idleIMG = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alien);
        Bitmap walk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienwalk1);
        Bitmap walk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienwalk2);

        idle = new Animation(new Bitmap[] {idleIMG}, 2);
        walkRight = new Animation(new Bitmap[] {walk1, walk2}, 0.5f);

        Matrix m = new Matrix();
        m.preScale(-1, 1);
        walk1 = Bitmap.createBitmap(walk1, 0, 0, walk1.getWidth(), walk1.getHeight(), m, false);
        walk2 = Bitmap.createBitmap(walk2, 0, 0, walk2.getWidth(), walk2.getHeight(), m, false);
        walkLeft = new Animation(new Bitmap[] {walk1, walk2}, 0.5f);

        animationManager = new AnimationManager(new Animation[] {idle, walkRight, walkLeft});
    }

    public Rect getPlayer() {
        return rectangle;
    }

    @Override
    public void draw(Canvas canvas) {
        animationManager.draw(canvas, rectangle);
    }

    @Override
    public void update() {
        animationManager.update();
    }

    public void update(Point point) {
        float oldLeft = rectangle.left;

        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2 );

        int state = 0;

        if(rectangle.left - oldLeft > 5 )
            state = 1;
        else if (rectangle.left - oldLeft < -5 )
            state = 2;

        animationManager.playAnim(state);
        animationManager.update();
    }
}
