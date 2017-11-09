package org.osori.androidstudy.week9;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractSwipeableItemViewHolder;

import org.osori.androidstudy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by junsu on 2017-11-09.
 */

public class SwipeViewHolder extends AbstractSwipeableItemViewHolder {

    @BindView(R.id.swipe_view_container)
    LinearLayout viewContainer;

    @BindView(R.id.view_text)
    TextView textView;

    public SwipeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public View getSwipeableContainerView() {
        return viewContainer;
    }
}
