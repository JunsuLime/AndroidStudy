package org.osori.androidstudy.week8;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.osori.androidstudy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junsu on 2017-10-25.
 */

public class ButterKnifeActivity extends AppCompatActivity {

    @BindView(R.id.sample_button)Button sampleButton;
    @BindView(R.id.sample_image)ImageView sampleImage;
    @BindView(R.id.sample_recyclerview)RecyclerView sampleRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);

        // 이렇게 해주면 findViewById 를 사용하지 않아도 binding 이 된다.
        ButterKnife.bind(this);

        sampleButton.setText("Hell..o wo..rld");

        SampleAdapter adapter = new SampleAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        sampleRecyclerView.setLayoutManager(layoutManager);
        sampleRecyclerView.setAdapter(adapter);
    }

    // setOnClickListener(new OnClickListener ..) 대신 이렇게 해서 click event 를 처리 할 수 있다.
    // click 이외에도 여러가지 event 를 다룰 수 있다.
    @OnClick(R.id.sample_image)
    public void onClickImage() {
        Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
    }
}
