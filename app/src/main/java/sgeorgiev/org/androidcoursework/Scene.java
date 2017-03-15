package sgeorgiev.org.androidcoursework;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by Svetlozar Georgiev on 15/03/2017.
 */

public interface Scene {
    public void update();
    public void draw(Canvas canvas);
    public void terminate();
    public void receiveTouch(MotionEvent event);
}
