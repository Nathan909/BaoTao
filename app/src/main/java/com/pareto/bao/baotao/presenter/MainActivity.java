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
import com.pareto.bao.baotao.view.Fragment1;
import com.pareto.bao.baotao.view.Fragment2;
import com.pareto.bao.baotao.view.Fragment3;
import com.pareto.bao.baotao.view.Fragment4;
import com.pareto.bao.baotao.view.Fragment5;

import static com.pareto.bao.baotao.R.id.radioButton1;


public class MainActivity extends AppCompatActivity {
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
    private Fragment5 fragment5;
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
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        fragment5 = new Fragment5();
        fragmentManager = getSupportFragmentManager();
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new onCheckedChangeListener());
    }

    //单选点击监听
    private class onCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radioButton1:
                    fragmentManager.beginTransaction().replace(R.id.content, new Fragment1()).addToBackStack(null).commit();
                    break;
                case R.id.radioButton2:
                    fragmentManager.beginTransaction().replace(R.id.content, new Fragment2()).addToBackStack(null).commit();
                    break;
                case R.id.radioButton3:
                    fragmentManager.beginTransaction().replace(R.id.content, new Fragment3()).addToBackStack(null).commit();
                    break;
                case R.id.radioButton4:
                    fragmentManager.beginTransaction().replace(R.id.content, new Fragment4()).addToBackStack(null).commit();
                    break;
                case R.id.radioButton5:
                    fragmentManager.beginTransaction().replace(R.id.content, new Fragment5()).addToBackStack(null).commit();
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