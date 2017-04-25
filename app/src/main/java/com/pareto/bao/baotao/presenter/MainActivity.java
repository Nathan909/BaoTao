package com.pareto.bao.baotao.presenter;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.pareto.bao.baotao.R;
import com.pareto.bao.baotao.view.HomeFragment;
import com.pareto.bao.baotao.view.WeitaoFragment;
import com.pareto.bao.baotao.view.AskFragment;
import com.pareto.bao.baotao.view.CartFragment;
import com.pareto.bao.baotao.view.MyFragment;


public class MainActivity extends AppCompatActivity {
    private HomeFragment homeFragment;
    private WeitaoFragment weitaoFragment;
    private AskFragment askFragment;
    private CartFragment cartFragment;
    private MyFragment myFragment;
    private FragmentManager fragmentManager;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Animation scaleAnimation;
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_icon);
        initView();
    }

    private void initView() {
        homeFragment = new HomeFragment();
        weitaoFragment = new WeitaoFragment();
        askFragment = new AskFragment();
        cartFragment = new CartFragment();
        myFragment = new MyFragment();
        fragmentManager = getSupportFragmentManager();
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new onCheckedChangeListener());
    }

    //单选点击监听
    private class onCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_nav_home:
                    fragmentManager.beginTransaction().replace(R.id.content, new HomeFragment()).addToBackStack(null).commit();
                    break;
                case R.id.rb_nav_weitao:
                    fragmentManager.beginTransaction().replace(R.id.content, new WeitaoFragment()).addToBackStack(null).commit();
                    break;
                case R.id.rb_nav_ask:
                    fragmentManager.beginTransaction().replace(R.id.content, new AskFragment()).addToBackStack(null).commit();
                    break;
                case R.id.rb_nav_cart:
                    fragmentManager.beginTransaction().replace(R.id.content, new CartFragment()).addToBackStack(null).commit();
                    break;
                case R.id.rb_nav_my:
                    fragmentManager.beginTransaction().replace(R.id.content, new MyFragment()).addToBackStack(null).commit();
                    break;
            }

            radioButton = (RadioButton) findViewById(checkedId);
            radioButton.startAnimation(scaleAnimation);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    radioButton.clearAnimation();
                }
            }, 200);
        }
    }
}