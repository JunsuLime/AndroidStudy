package org.osori.androidstudy.week4;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import org.osori.androidstudy.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by junsu on 2017-07-16.
 */

public class FloatingViewService extends Service {

    private static final String TAG = FloatingViewService.class.getSimpleName();

    private WindowManager mWindowManager;
    private View mFloatingView;
    private ImageView mImageView;

    // 주기적 실행을 위한 timer object
    private Timer timer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Add the view to the window.
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        //Specify the view position
        params.gravity = Gravity.TOP | Gravity.LEFT;        //Initially view will be added to top-left corner
        params.x = 0;
        params.y = 100;

        mFloatingView = LayoutInflater.from(this).inflate(R.layout.view_floating_head, null);

        // image 크기를 키워야되기 때문에 image 객체를 가져온다.
        // mFloatingView 내에 imageView 가 있기 때문에 mFloatingView.findViewById(..) 로 가져온다.
        mImageView = (ImageView) mFloatingView.findViewById(R.id.image);

        mFloatingView.setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        //remember the initial position.
                        initialX = params.x;
                        initialY = params.y;

                        //get the touch location
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //Calculate the X and Y coordinates of the view.
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);

                        //Update the layout with new X & Y coordinate
                        mWindowManager.updateViewLayout(mFloatingView, params);
                        break;
                }
                return false;
            }
        });

        mFloatingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 이미 timer 가 켜져있다면 cancel() 한다.
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                    return;
                }

                // 새로운 timer 를 만든다.
                timer = new Timer();
                // timer 에 새로운 timerTask 를 부여한다.
                // 100ms 이후 실행하며 20ms 주기로 run method 가 실행된다.
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // UI 변경 작업이기 때문에 main thread 로 돌려야한다.
                        // getMainLooper() 를 가져와서 Handler 에 넣어 실행
                        new Handler(getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                ViewGroup.LayoutParams viewParam = mImageView.getLayoutParams();
                                Log.d(TAG, "viewParam width: " + viewParam.width + "  height: " + viewParam.height);
                                viewParam.height += 2;
                                viewParam.width += 2;
                                mImageView.setLayoutParams(viewParam);
                            }
                        });
                    }
                }, 100, 20);
            }
        });

        // 길게 누르면 끄기!
        mFloatingView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                stopSelf();
                return false;
            }
        });

        //Add the view to the window
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(mFloatingView, params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFloatingView != null) mWindowManager.removeView(mFloatingView);
        // timer 가 있다면 끄기!
        if (timer != null) timer.cancel();
    }
}
