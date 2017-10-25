package org.osori.androidstudy.week8;

import android.content.Context;
import android.support.annotation.StringDef;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.osori.androidstudy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by junsu on 2017-10-25.
 */

public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.SampleHolder> {

    private LayoutInflater inflater;

    public SampleAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public SampleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SampleHolder(inflater.inflate(R.layout.item_butter_test, parent, false));
    }

    @Override
    public void onBindViewHolder(SampleHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class SampleHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.butter_text)TextView butterText;
        @BindView(R.id.butter_button)Button butterButton;
        @BindView(R.id.butter_check)CheckBox butterCheck;

        public SampleHolder(View itemView) {
            super(itemView);

            // 이렇게 해주면 findViewById 를 사용하지 않아도 binding 이 된다.
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            butterText.setText(String.valueOf(position));
            butterButton.setText(String.valueOf(position));

            butterCheck.setChecked(position % 2 == 0);
        }
    }
}
