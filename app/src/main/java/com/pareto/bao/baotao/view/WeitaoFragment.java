package com.pareto.bao.baotao.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pareto.bao.baotao.R;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class WeitaoFragment extends Fragment implements Animator.AnimatorListener {
    // 总布局
    private View weitaoView;

    // 标题栏布局
    private LinearLayout tf_app_bar_layout;
    private RelativeLayout tf_action_bar;
    private LinearLayout tf_tab_bar;
    // tab_bar图标
    private ImageView iv_weitao_dynamic;
    private ImageView iv_weitao_new;
    private ImageView iv_weitao_direct_seeding;
    private ImageView iv_weitao_hot_topic;
    //tab_bar文字
    private TextView tv_weitao_dynamic;
    private TextView tv_weitao_new;
    private TextView tv_weitao_direct_seeding;
    private TextView tv_weitao_hot_topic;

    // 数据内容布局
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;// 数据适配器
    private ListView tf_tab_page;// 数据页面
    private List<String> items;// 数据项
    private int mOrientation;// 数据走向

    private boolean isDown;
    private boolean mIsAnim;

    /**
     * fragment 生命周期：
     * onAttach -->
     * onCreate -->
     * onCreateView -->
     * onActivityCreated -->
     * onStart() -->
     * onResume------fragment active
     * onPause() -->
     * onStop() -->
     * onDestroyView() -->
     * onDestroy() -->
     * onDetach()-----fragment is Destroyed
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();//初始化数据
    }

    private void initData() {
        items = new ArrayList<String>();
        for (int i = 1; i < 100; i++) {
            items.add("" + i);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 总布局实例化
        weitaoView = inflater.inflate(R.layout.weitao_fragment, container, false);
        initView();//初始化布局
        return weitaoView;
    }

    private void initView() {
        // 标题栏布局实例化
        tf_app_bar_layout = (LinearLayout) weitaoView.findViewById(R.id.tf_app_bar_layout);
        tf_action_bar = (RelativeLayout) weitaoView.findViewById(R.id.tf_action_bar);
        tf_tab_bar = (LinearLayout) weitaoView.findViewById(R.id.tf_tab_bar);
        // tab_bar图标
        iv_weitao_dynamic = (ImageView) weitaoView.findViewById(R.id.iv_weitao_dynamic);
        iv_weitao_new = (ImageView) weitaoView.findViewById(R.id.iv_weitao_new);
        iv_weitao_direct_seeding = (ImageView) weitaoView.findViewById(R.id.iv_weitao_direct_seeding);
        iv_weitao_hot_topic = (ImageView) weitaoView.findViewById(R.id.iv_weitao_hot_topic);
        //tab_bar文字
        tv_weitao_dynamic = (TextView) weitaoView.findViewById(R.id.tv_weitao_dynamic);
        tv_weitao_new = (TextView) weitaoView.findViewById(R.id.tv_weitao_new);
        tv_weitao_direct_seeding = (TextView) weitaoView.findViewById(R.id.tv_weitao_direct_seeding);
        tv_weitao_hot_topic = (TextView) weitaoView.findViewById(R.id.tv_weitao_hot_topic);

        // 数据内容布局实例化
        recyclerView = (RecyclerView) weitaoView.findViewById(R.id.tf_tab_page);
    }

    @Override
    public void onStart() {
        super.onStart();
        //设置RecyclerView布局显示方式
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter = new RecyclerViewAdapter());
        //设置RecyclerView的分割线
        recyclerView.addItemDecoration(new RecyclerViewItem(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();

                    // 判断是否滚动到底部，并且是向下滚动
                    if (lastVisibleItem == (totalItemCount - 1) && isDown) {
                        //加载更多功能的代码
                        Toast.makeText(getActivity(), "客官别扯了，我们是有底线的", Toast.LENGTH_SHORT).show();
                    }
                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    if (isDown) {
                        Log.d("txxz", "onStart0()--isDown=" + isDown);
                        ObjectAnimator animator = ObjectAnimator.ofFloat(tf_app_bar_layout, "translationY", 0.0f, -tf_action_bar.getHeight());
                        animator.setInterpolator(new AccelerateDecelerateInterpolator());
                        animator.setDuration(200);
                        animator.start();
                        animator.addListener(WeitaoFragment.this);
                    } else {
                        Log.d("txxz", "onStart1()--isDown=" + isDown);
                        ObjectAnimator animator = ObjectAnimator.ofFloat(tf_app_bar_layout, "translationY", -tf_action_bar.getHeight(), 0.0f);
                        animator.setDuration(200);
                        animator.setInterpolator(new AccelerateDecelerateInterpolator());
                        animator.start();
                        animator.addListener(WeitaoFragment.this);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) isDown = true;// 下滑
                else isDown = false;// 上滑
            }
        });
    }

    /**
     * 设置标题栏变化高度
     *
     * @param page
     */
    public void setMarginTop(int page) {
        RelativeLayout.LayoutParams layoutParam = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
        layoutParam.setMargins(0, page, 0, 0);
        recyclerView.setLayoutParams(layoutParam);
        recyclerView.invalidate();
    }

    /**
     * RecyclerView 分割线绘制
     */
    class RecyclerViewItem extends RecyclerView.ItemDecoration {
        private int[] ATTRS = new int[]{android.R.attr.listDivider};
        private int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
        private int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
        private Drawable mDivider;

        public RecyclerViewItem(Context context, int orientation) {
            final TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
            mDivider = typedArray.getDrawable(0);
            typedArray.recycle();
            setOrientation(orientation);
        }

        private void setOrientation(int orientation) {
            mOrientation = orientation;
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent) {
            super.onDraw(c, parent);
            if (mOrientation == VERTICAL_LIST) {
                drawVertical(c, parent);
            } else {
                drawHorizontal(c, parent);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
            if (mOrientation == VERTICAL_LIST) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            }
        }

        /**
         * 竖直方向的分割线
         *
         * @param c
         * @param parent
         */
        private void drawVertical(Canvas c, RecyclerView parent) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        /**
         * 水平方向的分割线
         *
         * @param c
         * @param parent
         */
        private void drawHorizontal(Canvas c, RecyclerView parent) {
            final int top = parent.getPaddingTop();
            final int bottom = parent.getHeight() - parent.getPaddingBottom();
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int left = child.getRight() + params.rightMargin;
                final int right = left + mDivider.getIntrinsicWidth();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);

            }
        }
    }

    /**
     * itemList数据适配器
     */
    class RecyclerViewAdapter extends RecyclerView.Adapter<WeitaoViewHolder> {

        @Override
        public WeitaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            WeitaoViewHolder weitaoViewHolder = new WeitaoViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.weitao_item, parent, false));
            return weitaoViewHolder;
        }

        @Override
        public void onBindViewHolder(final WeitaoViewHolder holder, int position) {
            holder.tv_item_weitao.setText(items.get(position));
            holder.tv_item_weitao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), holder.tv_item_weitao.getText(), Toast.LENGTH_LONG);
                }
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

    /**
     * itemView持有类
     */
    class WeitaoViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item_weitao;

        public WeitaoViewHolder(View itemView) {
            super(itemView);
            tv_item_weitao = (TextView) itemView.findViewById(R.id.tv_item_weitao);
        }
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (isDown) setMarginTop(tf_app_bar_layout.getHeight() - tf_action_bar.getHeight());
        else setMarginTop(tf_app_bar_layout.getHeight());
        mIsAnim = false;
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
