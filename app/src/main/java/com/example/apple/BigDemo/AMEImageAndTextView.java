package com.example.apple.BigDemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AMEImageAndTextView extends LinearLayout {
    @BindView(R.id.image_and_textview_image)
    ImageView imageView;
    @BindView(R.id.image_and_textview_text)
    TextView textView;
    private AMEImageAndTextViewModel model;


    public AMEImageAndTextView(Context context) {
        super(context);
        LayoutInflater.from(getContext()).inflate(R.layout.image_and_textview_layout, this);
        ButterKnife.bind(this);
    }

    public AMEImageAndTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.image_and_textview_layout, this);
        ButterKnife.bind(this);
    }

    public AMEImageAndTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AMEImageAndTextView(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public AMEImageAndTextViewModel getModel() {
        return model;
    }

    public void setModel(AMEImageAndTextViewModel model) {
        this.model = model;
        imageView.setImageResource(model.getImageId());
        textView.setText(model.getText());
    }
}
