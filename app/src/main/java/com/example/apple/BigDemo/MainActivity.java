package com.example.apple.BigDemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hjm.bottomtabbar.BottomTabBar;

import java.io.IOException;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;
    @BindView(R.id.titleBar)
    TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        hideBottomUIMenu();
        bottomTabBar.init(getSupportFragmentManager())
                .addTabItem("全部", R.mipmap.tab_all_selected, R.mipmap.tab_all, FragmentOne.class)
                .addTabItem("过期", R.mipmap.tab_over_selected, R.mipmap.tab_over, FragmentTwo.class)
                .addTabItem("未用", R.mipmap.tab_unused_selected, R.mipmap.tab_unused, FragmentThree.class)
                .addTabItem("已用", R.mipmap.tab_used_selected, R.mipmap.tab_used, FragmentFour.class);
        bottomTabBar.setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
            @Override
            public void onTabChange(int position, String name, View view) {
                Log.d(name, String.valueOf(position));
                switch (position) {
                    case 0:
                        titleBar.setTitle("全部");
                        break;
                    case 1:
                        titleBar.setTitle("过期");
                        break;
                    case 2:
                        titleBar.setTitle("未用");
                        break;
                    case 3:
                        titleBar.setTitle("已用");
                        break;

                }
            }
        });

        titleBar.setTitleColor(Color.WHITE);
        titleBar.setBackgroundColor(Color.parseColor("#5EA8ED"));
        titleBar.setDividerColor(Color.GRAY);
        titleBar.setImmersive(true);
        titleBar.setHeight(48*2);


        int bottomHeight = getNavigationBarHeight(getApplicationContext());
        Log.d("bottomHeight", "onCreate: "+bottomHeight);

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) bottomTabBar.getLayoutParams();
        lp.bottomMargin = bottomHeight;
        bottomTabBar.setLayoutParams(lp);

//        OkHttpClient client = new OkHttpClient();
//        RequestBody body = new FormBody.Builder()
//                .add("key","f9bc769c4e1d551c33c6b57530c584b9")
//                .add("q","羞羞的铁拳").build();
//        Request request = new Request.Builder()
//                .post(body)
//                .url("http://op.juhe.cn/onebox/movie/video")
//                .build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("111", String.valueOf(e));
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String json = response.body().string();
//                Log.d("3", "onResponse: " + json);
//                Gson gson = new Gson();
//                TestModel model = gson.fromJson(json, TestModel.class);
//                Log.d("123",model.getResult().getTitle());
//            }
//        });
    }

    //获取虚拟按键的高度
    public static int getNavigationBarHeight(Context context) {
        int result = 0;
        if (hasNavBar(context)) {
            Resources res = context.getResources();
            int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }

    /**
     * 检查是否存在虚拟按键栏
     *
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static boolean hasNavBar(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            // check override flag
            String sNavBarOverride = getNavBarOverride();
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else { // fallback
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

    /**
     * 判断虚拟按键栏是否重写
     *
     * @return String
     */
    private static String getNavBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
            }
        }
        return sNavBarOverride;
    }
}
