package org.osori.androidstudy.week6;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.osori.androidstudy.MainActivity;
import org.osori.androidstudy.R;
import org.osori.androidstudy.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by junsu on 2017-08-24.
 */

public class IntentTestAdapter extends RecyclerView.Adapter<IntentTestAdapter.StudyViewHolder> {

    private final String TAG = IntentTestAdapter.class.getSimpleName();

    private Context mContext;
    private LayoutInflater mInflater;

    private List<Integer> studyList = new ArrayList<>();
    private Integer[] studyArray = {
            Intent.FLAG_ACTIVITY_CLEAR_TOP,
            Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK,
    };

    public IntentTestAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);

        studyList.addAll(Arrays.asList(studyArray));
    }

    @Override
    public StudyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StudyViewHolder(mInflater.inflate(R.layout.item_study_viewholder, parent, false));
    }

    @Override
    public void onBindViewHolder(StudyViewHolder holder, int position) {
        if (position == 0) {
            Intent intent = new Intent(mContext, MainActivity.class);
            holder.bind(intent.getFlags());
        }
        else {
            holder.bind(studyList.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount is called: " + studyList.size());
        return studyList.size() + 1;
    }

    class StudyViewHolder extends RecyclerView.ViewHolder {

        TextView studyTitleView;
        int intentFlag;

        public StudyViewHolder(View itemView) {
            super(itemView);
            studyTitleView = (TextView) itemView.findViewById(R.id.study_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.setFlags(intentFlag);
                    mContext.startActivity(intent);

                }
            });
        }
        private void bind(int intentFlag) {
            this.intentFlag = intentFlag;
            studyTitleView.setText(Utils.getFlagInfo(intentFlag));
        }
    }
}
