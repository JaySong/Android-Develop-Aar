package com.qjay.android_widget.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.qjay.android_widget.R;
import com.qjay.android_widget.recyclerview.divider.HorizontalDividerItemDecoration;
import com.qjay.android_widget.recyclerview.divider.VerticalDividerItemDecoration;

/**
 *
 * Created by Q.Jay on 2015/8/26 0026.
 */
public class SuperRecyclerView extends RecyclerView {
    /**线性布局管理器标识常量*/
    private static final int LINEAR_LAYOUT_MANAGER = 0;
    /**网格布局管理器标识常量*/
    private static final int GRID_LAYOUT_MANAGER = 1;
    /**瀑布流布局管理器标识常量*/
    private static final int STAGGERED_GRID_LAYOUT_MANAGER = 2;
    /**是否自动适配高度*/
    private boolean isAutoHeight;
    /**默认放置item方向*/
    private int mOrientation = VERTICAL;
    /**默认布局管理器*/
    private static final int DEFAULT_LAYOUT_MANAGER = LINEAR_LAYOUT_MANAGER;
    /**当前布局管理器*/
    private int mLayoutManager;
    /**item之间装饰间隔*/
    private int mDividerMargin;
    private int mDividerMarginLeft;
    private int mDividerMarginRight;
    private int mDividerMarginTop;
    private int mDividerMarginBottom;
    /**item装饰的空间与颜色*/
    private int mDividerSpace;
    private int mDividerColor;
    /**没有数据时显示的View*/
    private View mEmptyView;

    public SuperRecyclerView(Context context) {
        this(context, null);
    }

    public SuperRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SuperRecyclerView);
        try {
            /*获取布局中设置的布局管理器*/
            mLayoutManager = a.getInt(R.styleable.SuperRecyclerView_layout_manager, DEFAULT_LAYOUT_MANAGER);
            /*获取方向*/
            mOrientation = a.getInt(R.styleable.SuperRecyclerView_orientation, mOrientation);
            /*是否自动适配高度*/
            isAutoHeight = a.getBoolean(R.styleable.SuperRecyclerView_isAutoHeight, false);
            mDividerMargin = a.getDimensionPixelSize(R.styleable.SuperRecyclerView_dividerMargin, 0);
            mDividerColor = a.getColor(R.styleable.SuperRecyclerView_dividerColor, Color.GRAY);
            mDividerSpace = a.getDimensionPixelSize(R.styleable.SuperRecyclerView_dividerSpace, 0);
            if(mDividerMargin == 0) {
                mDividerMarginLeft = a.getDimensionPixelSize(R.styleable.SuperRecyclerView_dividerMarginLeft, 0);
                mDividerMarginRight = a.getDimensionPixelSize(R.styleable.SuperRecyclerView_dividerMarginRight, 0);
                mDividerMarginTop = a.getDimensionPixelSize(R.styleable.SuperRecyclerView_dividerMarginTop, 0);
                mDividerMarginBottom = a.getDimensionPixelSize(R.styleable.SuperRecyclerView_dividerMarginBottom, 0);
            }
            switch (mLayoutManager) {
                case LINEAR_LAYOUT_MANAGER:
                    setLinearLayoutManager(context, a);
                    break;
                case GRID_LAYOUT_MANAGER:
                    setGridLayoutManager(context,a);
                    break;
            }
        } finally {
            a.recycle();
        }
        init();
    }

    private void init() {
        if (mDividerSpace != 0) {
            initItemDivider();
        }
    }

    /**
     * 设置流式布局管理器
     * @param context
     * @param a
     */
    private void setLinearLayoutManager(Context context,TypedArray a) {
        LinearLayoutManager layoutManager = LayoutManagerHelper.buildLinerLayoutManager(context, isAutoHeight, mOrientation, false, mDividerSpace);
        this.setLayoutManager(layoutManager);
    }

    /**
     * 设置风格布局管理器
     * @param context
     * @param a
     */
    private void setGridLayoutManager(Context context,TypedArray a) {
        int spanCount = a.getInt(R.styleable.SuperRecyclerView_span_count, 1);
        GridLayoutManager layoutManager = LayoutManagerHelper.buildGridLayoutManager(context, spanCount, mOrientation, false, isAutoHeight,mDividerSpace);
        this.setLayoutManager(layoutManager);
    }

    /**初始Item装饰*/
    private void initItemDivider(){
        if(mOrientation ==VERTICAL){
            this.addItemDecoration(buildHorizontalDivider());
        }else{
            this.addItemDecoration(buildVerticalDivider());
        }
    }
    /**构建水平装饰*/
    private HorizontalDividerItemDecoration buildHorizontalDivider(){
        HorizontalDividerItemDecoration.Builder divider = new HorizontalDividerItemDecoration.Builder(getContext()).color(mDividerColor).size(mDividerSpace);
        if(mDividerMargin == 0) {
            divider.margin(mDividerMarginLeft, mDividerMarginRight);
        }else {
            divider.margin(mDividerMargin);
        }
        return divider.build();
    }
    /**构建垂直装饰*/
    private VerticalDividerItemDecoration buildVerticalDivider(){
        VerticalDividerItemDecoration.Builder divider = new VerticalDividerItemDecoration.Builder(getContext()).color(mDividerColor);
        if(mDividerMargin == 0) {
            divider.margin(mDividerMarginTop, mDividerMarginBottom);
        }else {
            divider.margin(mDividerMargin);
        }
        return divider.build();
    }
    /**设置条目点击事件*/
    public void addOnItemClickListener(SuperRecyclerViewAdapter.OnItemClickListener listener) {
        Adapter adapter = getAdapter();
        if (adapter instanceof SuperRecyclerViewAdapter) {
            ((SuperRecyclerViewAdapter) adapter).addOnItemClickListener(listener);
        }
    }
    /**设置空View*/
    public void setEmptyView(View view) {
        if(view == null) {
            throw new NullPointerException("view is null");
        }
        mEmptyView = view;
        mEmptyView.setVisibility(INVISIBLE);
    }
    /**添加 head view*/
    public void addHeadView(View view){}
    /**添加 footer view*/
    public void addFooterView(View view){}

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if(adapter!= null){
            adapter.registerAdapterDataObserver(mAdapterDataObserver);
            adapter.notifyDataSetChanged();
        }

//        if (adapter instanceof SuperRecyclerViewAdapter) {
//            ((SuperRecyclerViewAdapter) adapter).setEmptyView(mEmptyView);
//        }
    }

    private AdapterDataObserver mAdapterDataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            Adapter adapter = getAdapter();
            if (adapter != null) {
                if(adapter.getItemCount() == 0 ){
                    if(mEmptyView != null){
                        setVisibility(INVISIBLE);
                        mEmptyView.setVisibility(VISIBLE);
                    }
                }else{
                    if(mEmptyView != null){
                        setVisibility(VISIBLE);
                        mEmptyView.setVisibility(INVISIBLE);
                    }
                }
            }
//            if (getAdapter() instanceof SuperRecyclerViewAdapter) {
//                SuperRecyclerViewAdapter adapter = (SuperRecyclerViewAdapter) getAdapter();
//                if(adapter.getItemCount() == 0){
//                    adapter.setState(SuperRecyclerViewAdapter.TYPE_EMPTY);
//                    adapter.setEmptyView(mEmptyView);
//                }else{
//                    adapter.setState(SuperRecyclerViewAdapter.TYPE_DEFAULT);
//                }
//            }
        }
    };
}
