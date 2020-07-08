package com.MovieApps.widget;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import com.MovieApps.R;

public class DashboardIndicator extends RelativeLayout {

//    @Bind(com.MovieApps.R.id.history_tab_indicator_line) View indicator;
//    @Bind(com.MovieApps.R.id.history_tab_indicator_content) LinearLayout contentContainer;

    private OnTabClickListener listener;

    private int indicatorWidth;

    public DashboardIndicator(Context context) {
        this(context, null);
    }

    public DashboardIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashboardIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_dashboard_indicator, this);
        if (isInEditMode()) {
            return;
        }
        ButterKnife.bind(this);

//        ViewUtil.afterMeasured(contentContainer, view -> {
//            indicatorWidth = view.getWidth() / contentContainer.getChildCount();
//
//            indicator.getLayoutParams().width = indicatorWidth;
//            indicator.requestLayout();
//
//            ViewUtil.forEachChild(contentContainer, (child, integer) ->
//                    child.setOnClickListener(v -> {
//                        onIndicatorClick(integer);
//                    }));
//        });
    }

    private void onIndicatorClick(int position) {
        if (listener != null) {
            listener.onClick(position);
        }

//        ViewCompat.animate(indicator)
//                .x(position * indicatorWidth)
//                .start();
    }

    public void bind(ViewPager pager) {
        this.listener = pager::setCurrentItem;
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                onIndicatorClick(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public interface OnTabClickListener {
        void onClick(int position);
    }
}
