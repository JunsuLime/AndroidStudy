package org.osori.androidstudy.week0;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.osori.androidstudy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junsu on 2017-06-22.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.SampleViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;

    private final List<Integer> integerList = new ArrayList<>();

    private OnClickListener mListener;

    public RecyclerViewAdapter(Context context, OnClickListener listener) {
        mContext = context;
        mInflater = LayoutInflater.from(context);

        mListener = listener;
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SampleViewHolder(mInflater.inflate(R.layout.item_sample, parent, false));
    }

    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {
        holder.bind(integerList.get(position));
    }

    @Override
    public int getItemCount() {
        return integerList.size();
    }

    public void addItem() {
        integerList.add(integerList.size());
    }

    class SampleViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        Integer mData;

        public SampleViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.sample_text_view);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClickItem(mData);
                }
            });
        }

        public void bind(int data) {
            mData = data;
            textView.setText(String.valueOf(data));
        }
    }

    interface OnClickListener {
        public void onClickItem(Integer data);
    }
}
