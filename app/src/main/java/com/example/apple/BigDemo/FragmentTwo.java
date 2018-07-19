package com.example.apple.BigDemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {


    @BindView(R.id.listView)
    ListView listView;
    Unbinder unbinder;

    List<ListViewModel> modelList = new ArrayList<>();

    public FragmentTwo() {
        // Required empty public constructor

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainActivity activity = (MainActivity) getActivity();

        makeList();

        RelativeLayout.LayoutParams layoutListView = (RelativeLayout.LayoutParams) listView.getLayoutParams();
        int top = activity.titleBar.getHeight();
        Log.d("top", "onActivityCreated: "+ top);
        layoutListView.topMargin = top;
        listView.setLayoutParams(layoutListView);

        listView.setAdapter(new CustomListViewAdapter(getActivity(), R.layout.list_view_layout, modelList));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_two, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void makeList() {
        ListViewModel model0 = new ListViewModel();
        model0.setTextContent("Glide，一个被google所推荐的图片加载库，作者是bumptech。这个库被广泛运用在google的开源项目中，包括2014年的google " +
                "I/O大会上发布的官方app。（PS：众所周知的简介就到此为止了）");
        model0.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531721967657&di=a2daa5f4d9ba972cec1fb45e873ef635&imgtype=jpg&src=http%3A%2F%2Fimg1.imgtn.bdimg.com%2Fit%2Fu%3D968767876%2C1793120082%26fm%3D214%26gp%3D0.jpg");

        ListViewModel model1 = new ListViewModel();
        model1.setTextContent("with(Context context) - 需要上下文，这里还可以使用 Activity、FragmentActivity、android.support.v4.app" +
                ".Fragment、android.app.Fragment 的对象。将 Activity/Fragment 对象作为参数的好处是，图片的加载会和 Activity/Fragment " +
                "的生命周期保持一致，例如：onPaused 时暂停加载，onResume 时又会自动重新加载。所以在传参的时候建议使用 Activity/Fragment 对象，而不是 Context。");
        model1.setUrl("https://timgsa.baidu" +
                ".com/timg?image&quality=80&size=b9999_10000&sec=1531721106439&di=78337362d2f085d507173d3a767d1e7f" +
                "&imgtype=0&src=http%3A%2F%2Fbp.googleblog" +
                ".cn%2F-brgnjo5GUa0%2FWLhXuAwnQII%2FAAAAAAAAD88%2FoxL3WK0wiU8zRVDAKyt1sUo37VZLo3BrQCLcB%2Fs1600" +
                "%2FAndroid%252BLogo.png");

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0){
                modelList.add(model0);
            }else {
                modelList.add(model1);
            }
        }

    }
}
