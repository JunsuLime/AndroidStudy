package org.osori.androidstudy.week0;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.osori.androidstudy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junsu on 2017-06-22.
 */

public class PatternStudyActivity extends AppCompatActivity implements ActivityContract.View, RecyclerViewAdapter.OnClickListener {

    private static final String TAG = PatternStudyActivity.class.getSimpleName();

    private Button itemAddButton;

    // itemListView 는 editText 와 같다고 생각하면 된다.
    private RecyclerView itemListView;
    private RecyclerViewAdapter adapter;

    private ActivityContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_study);

        itemAddButton = (Button) findViewById(R.id.add_item_button);
        itemListView = (RecyclerView) findViewById(R.id.item_list_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerViewAdapter(this, this);

        itemListView.setAdapter(adapter);
        itemListView.setLayoutManager(layoutManager);

        presenter = new ActivityPresenter(this);

        itemAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addItem();
            }
        });
    }

    @Override
    public void addItem() {
        Log.d(TAG, "addItem is called");
        // 여기서 adapter.addItem 이 납득이 되는 이유
        // editText 에 글을 쓴다고 생각해보자
        // data 를 넣는 것 이긴 하지만 이것은 내가 관리하는 data 는 아니다.
        // 그렇기 때문에 adapter.addItem() 는 납득 할 수 있는 행동이다.
        adapter.addItem();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(Integer data) {
        Log.d(TAG, "showToast is called");
        Toast.makeText(this, String.valueOf(data), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickItem(Integer data) {
        presenter.viewData(data);
    }
}
