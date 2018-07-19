package com.example.apple.BigDemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoActicity extends AppCompatActivity {

    @BindView(R.id.titleBar)
    TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_acticity);
        ButterKnife.bind(this);

        titleBar.setLeftImageResource(R.mipmap.btn_back_normal);
        titleBar.setLeftTextColor(Color.WHITE);
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleBar.setTitle("文章详情");
        titleBar.setTitleColor(Color.WHITE);
        titleBar.setBackgroundColor(Color.parseColor("#5EA8ED"));
        titleBar.setDividerColor(Color.GRAY);
        titleBar.setImmersive(true);
        titleBar.setHeight(48 * 2);
    }
}
