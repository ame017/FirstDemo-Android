package com.example.apple.BigDemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFour extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.cardView)
    AMECardView cardView;

    public FragmentFour() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainActivity activity = (MainActivity) getActivity();

        RelativeLayout.LayoutParams layout = (RelativeLayout.LayoutParams) cardView
                .getLayoutParams();
        int top = activity.titleBar.getHeight();
        Log.d("top", "onActivityCreated: "+ top);
        layout.topMargin = top+10;
        cardView.setLayoutParams(layout);


        cardView.textView.setText("包裹功能");
        cardView.setDelegate(new AMECardViewDelegate() {
            @Override
            public void ame_cardViewDidClickIndex(int index) {
                Log.d("index", "ame_cardViewDidClickIndex: "+index);
            }
        });


        AMEImageAndTextViewModel model = new AMEImageAndTextViewModel();
        model.setImageId(R.mipmap.index_sign);
        model.setText("签收拍照");
        AMEImageAndTextViewModel model1 = new AMEImageAndTextViewModel();
        model1.setImageId(R.mipmap.index_reject);
        model1.setText("拒收拍照");
        AMEImageAndTextViewModel model2 = new AMEImageAndTextViewModel();
        model2.setImageId(R.mipmap.index_receive);
        model2.setText("收取包裹");
        AMEImageAndTextViewModel model3 = new AMEImageAndTextViewModel();
        model3.setImageId(R.mipmap.index_dispatch);
        model3.setText("派送包裹");
        AMEImageAndTextViewModel model4 = new AMEImageAndTextViewModel();
        model4.setImageId(R.mipmap.index_in_warehouse);
        model4.setText("包裹入库");
        AMEImageAndTextViewModel model5 = new AMEImageAndTextViewModel();
        model5.setImageId(R.mipmap.index_out_warehouse);
        model5.setText("包裹出库");
        AMEImageAndTextViewModel model6 = new AMEImageAndTextViewModel();
        model6.setImageId(R.mipmap.index_order);
        model6.setText("订单中心");

        final List<AMEImageAndTextViewModel> list = new ArrayList();
        list.add(model);
        list.add(model1);
        list.add(model2);
        list.add(model3);
        list.add(model4);
        list.add(model5);
        list.add(model6);

        final int[] i = {0};
        final List<AMEImageAndTextViewModel> list0 = new ArrayList<>();
        final Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                list0.add(list.get(i[0]));
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        cardView.setModels(list0);
                    }
                });
                i[0]++;
                if (i[0] > 6){
                    timer.cancel();
                }
            }
        },0,1000);

 //       cardView.setModels(list);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_four, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
