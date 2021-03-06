package com.example.velmurugan.progressview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Velmurugan on 3/15/2018.
 */

public class ProgressView extends View {
    private String TAG = ProgressView.class.getSimpleName();
    private Paint paint;
    boolean isDragging = false;


    private int circleX, circleY;
    int startX,startY,endX,endY;

    int canvasWidth,canvasHeight;
    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(50);
        paint.setColor(Color.GRAY);

        Path path = new Path();
        path.moveTo(startX,startY );
        path.lineTo(endX , endY);
        canvas.drawPath(path,paint);

        // Created Circle over progressbar
        paint.setColor(Color.BLACK);
        canvas.drawCircle(circleX,startY,21,paint);
        paint.setColor(Color.RED);
        canvas.drawCircle(circleX,startY,7,paint);





        /*View view = new CircleView(getContext());
        canvas.translate(startX, startY);
        view.draw(canvas);
        view.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        log("ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_UP:
                        log("ACTION_UP");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        log("ACTION_MOVE");
                        break;
                }
                return true;
            }
        });*/

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        canvasWidth = MeasureSpec.getSize(widthMeasureSpec);
        canvasHeight = MeasureSpec.getSize(heightMeasureSpec);

        int padding = canvasWidth / 10;

        startX = padding;
        startY = canvasHeight / 2;
        endX = canvasWidth - padding;
        endY = canvasHeight / 2;
        circleX = startX;
        circleY = startY;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                log("ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                log("ACTION_UP");
                isDragging = false;
                break;
            case MotionEvent.ACTION_MOVE:
               // log("ACTION_MOVE");
                float x = event.getX();
                float y = event.getY();
                log("x = "+x+" y = "+y);

                if((x > (circleX - 30) && x < (circleX + 30)  && y > (circleY - 30) && y < (circleY + 30)) || isDragging){
                        //log("YOU GOT IT");
                        //log("isDragging = "+isDragging);
                        isDragging = true;
                        circleX = (int) x;
                        circleY = (int) y;
                        invalidate();
                }
                break;
        }
        return true;
    }

    private void moveCircle(float x, float y) {

    }

    private void log(String log){
        Log.d(TAG,log);
    }
}
