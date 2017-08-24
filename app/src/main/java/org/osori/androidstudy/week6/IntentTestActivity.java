package org.osori.androidstudy.week6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.osori.androidstudy.R;

/**
 * Created by junsu on 2017-08-24.
 */

public class IntentTestActivity extends AppCompatActivity {

    private RecyclerView intentListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_test);

        intentListView = (RecyclerView) findViewById(R.id.intent_flag_list_view);
        IntentTestAdapter adapter = new IntentTestAdapter(this);

        intentListView.setAdapter(adapter);
        intentListView.setLayoutManager(new LinearLayoutManager(this));
    }
}
