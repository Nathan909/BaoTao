package com.pareto.bao.baotao.presenter;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
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
    private RadioButton rb_nav;
    private RadioButton rb_nav_home;

    private Animation scaleAnimation;

    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        fragmentManager = getSupportFragmentManager();
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_icon);

        rb_nav_home = (RadioButton) findViewById(R.id.rb_nav_home);

        radioGroup.setOnCheckedChangeListener(new onCheckedChangeListener());
        rb_nav_home.setChecked(true);
    }

    /**
     * 导航栏点击动画
     */
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
                    break;
                case R.id.rb_nav_weitao:
                    if (weitaoFragment == null) {
                        weitaoFragment = new WeitaoFragment();
                        fragmentTransaction.add(R.id.content, weitaoFragment);
                    } else
                        fragmentTransaction.show(weitaoFragment);
                    break;
                case R.id.rb_nav_ask:
                    if (askFragment == null) {
                        askFragment = new AskFragment();
                        fragmentTransaction.add(R.id.content, askFragment);
                    } else
                        fragmentTransaction.show(askFragment);
                    break;
                case R.id.rb_nav_cart:
                    if (cartFragment == null) {
                        cartFragment = new CartFragment();
                        fragmentTransaction.add(R.id.content, cartFragment);
                    } else
                        fragmentTransaction.show(cartFragment);
                    break;
                case R.id.rb_nav_my:
                    if (myFragment == null) {
                        myFragment = new MyFragment();
                        fragmentTransaction.add(R.id.content, myFragment);
                    } else
                        fragmentTransaction.show(myFragment);
                    break;
            }

            rb_nav = (RadioButton) findViewById(checkedId);
            rb_nav.startAnimation(scaleAnimation);
            fragmentTransaction.commit();
        }
    }

    /**
     * 隐藏所有fragment
     *
     * @param fragmentTransaction
     */
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