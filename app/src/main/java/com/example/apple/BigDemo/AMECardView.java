package com.example.apple.BigDemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AMECardView extends RelativeLayout {

    @BindView(R.id.text)
    TextView textView;
    @BindView(R.id.LinearLayout)
    LinearLayout linearLayout;


    private AMECardViewDelegate delegate;

    private List<AMEImageAndTextViewModel> models = new ArrayList<>();

//    private List<AMEImageAndTextView> views = new ArrayList<>();

    public AMECardView(Context context) {
        super(context);
    }

    public AMECardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.card_view, this);
        ButterKnife.bind(this);
    }

    public AMECardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AMECardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setModels(List<AMEImageAndTextViewModel> models) {
        this.models = models;
//        for (AMEImageAndTextView view : views) {
//            this.removeView(view);
//        }
        linearLayout.removeAllViews();
//        views = new ArrayList<>();
        LinearLayout row = null;


        for (int i = 0; i < models.size(); i++) {
            if (i % 4 == 0){
                LinearLayout row0 = new LinearLayout(getContext());
                row0.setOrientation(LinearLayout.HORIZONTAL);
                row0.setWeightSum(1.0f);
                row = row0;

                LinearLayout.LayoutParams rowLayout =  new LinearLayout.LayoutParams(ViewGroup
                        .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(rowLayout);

                linearLayout.addView(row);
            }
            AMEImageAndTextView view = new AMEImageAndTextView(getContext());
            view.setModel(models.get(i));
            final int finalI = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (delegate != null){
                        delegate.ame_cardViewDidClickIndex(finalI);
                    }
                }
            });
            Log.d("width", "setModels: "+this.getWidth());
            LinearLayout.LayoutParams lp =  new LinearLayout.LayoutParams(0, ViewGroup
                    .LayoutParams.WRAP_CONTENT,0.25f);
            view.setLayoutParams(lp);
            row.addView(view);
        }
    }

    public void setDelegate(AMECardViewDelegate delegate) {
        this.delegate = delegate;
    }

}
