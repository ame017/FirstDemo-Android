package com.example.apple.BigDemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class AMETextView extends android.support.v7.widget.AppCompatTextView {

    private String allText;

    private boolean isAnimating;
    private int index;
    Timer timer = null;
    private Handler handler = null;


    public AMETextView(Context context) {
        super(context);
    }

    public AMETextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AMETextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void startAnimation() {
        if (isAnimating){
            isAnimating = false;
            timer.cancel();
            this.setText(allText);
        }
        isAnimating = true;
        index = 0;
        allText = (String) this.getText();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                AMETextView.this.post(new Runnable() {
                    @Override
                    public void run() {
                        AMETextView.this.setText(allText.substring(0,index));
                    }
                });
                index++;
                if (index == allText.length()){
                    Log.d("stop", "run: stop");
                    isAnimating = false;
                    timer.cancel();
                }
            }
        },0,30);
    }

}
