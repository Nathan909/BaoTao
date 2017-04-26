package com.pareto.bao.baotao.presenter;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private WeitaoFragment weitaoFragment;
    private AskFragment askFragment;
    private CartFragment cartFragment;
    private MyFragment myFragment;

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private RadioButton rb_nav_home;
    private RadioButton rb_nav_weitao;
    private RadioButton rb_nav_ask;
    private RadioButton rb_nav_cart;
    private RadioButton rb_nav_my;
    private Animation scaleAnimation;

    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        fragmentManager = getSupportFragmentManager();
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_icon);

        rb_nav_home = (RadioButton) findViewById(R.id.rb_nav_home);
        rb_nav_weitao = (RadioButton) findViewById(R.id.rb_nav_weitao);
        rb_nav_ask = (RadioButton) findViewById(R.id.rb_nav_ask);
        rb_nav_cart = (RadioButton) findViewById(R.id.rb_nav_cart);
        rb_nav_my = (RadioButton) findViewById(R.id.rb_nav_my);

        radioGroup.setOnCheckedChangeListener(new onCheckedChangeListener());
        rb_nav_home.setChecked(true);
    }

    //单选点击监听
    private class onCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            hideAllFragment(fragmentTransaction);

            switch (checkedId) {
                case R.id.rb_nav_home:
                    if (homeFragment == null) {
                        homeFragment = new HomeFragment();
                        fragmentTransaction.add(R.id.content, homeFragment);
                    } else
                        fragmentTransaction.show(homeFragment);
                    rb_nav_home.startAnimation(scaleAnimation);
                    break;
                case R.id.rb_nav_weitao:
                    if (weitaoFragment == null) {
                        weitaoFragment = new WeitaoFragment();
                        fragmentTransaction.add(R.id.content, weitaoFragment);
                    } else
                        fragmentTransaction.show(weitaoFragment);
                    rb_nav_weitao.startAnimation(scaleAnimation);
                    break;
                case R.id.rb_nav_ask:
                    if (askFragment == null) {
                        askFragment = new AskFragment();
                        fragmentTransaction.add(R.id.content, askFragment);
                    } else
                        fragmentTransaction.show(askFragment);
                    rb_nav_ask.startAnimation(scaleAnimation);
                    break;
                case R.id.rb_nav_cart:
                    if (cartFragment == null) {
                        cartFragment = new CartFragment();
                        fragmentTransaction.add(R.id.content, cartFragment);
                    } else
                        fragmentTransaction.show(cartFragment);
                    rb_nav_cart.startAnimation(scaleAnimation);
                    break;
                case R.id.rb_nav_my:
                    if (myFragment == null) {
                        myFragment = new MyFragment();
                        fragmentTransaction.add(R.id.content, myFragment);
                    } else
                        fragmentTransaction.show(myFragment);
                    rb_nav_my.startAnimation(scaleAnimation);
                    break;
            }
            fragmentTransaction.commit();
        }
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (homeFragment != null)
            fragmentTransaction.hide(homeFragment);
        if (weitaoFragment != null)
            fragmentTransaction.hide(weitaoFragment);
        if (askFragment != null)
            fragmentTransaction.hide(askFragment);
        if (cartFragment != null)
            fragmentTransaction.hide(cartFragment);
        if (myFragment != null)
            fragmentTransaction.hide(myFragment);
    }
}