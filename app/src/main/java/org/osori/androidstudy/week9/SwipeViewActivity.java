package org.osori.androidstudy.week9;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

//import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager;

import org.osori.androidstudy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by junsu on 2017-11-09.
 */

public class SwipeViewActivity extends AppCompatActivity {

    @BindView(R.id.swipe_recycler_view)
    RecyclerView swipeRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_view);

        ButterKnife.bind(this);

//        RecyclerViewSwipeManager manager = new RecyclerViewSwipeManager();
//
//        swipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        swipeRecyclerView.setAdapter(manager.createWrappedAdapter(new SwipeViewAdapter()));
//
//        manager.attachRecyclerView(swipeRecyclerView);
    }
}
