package org.osori.androidstudy.week9;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultActionDefault;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultActionMoveToOrigin;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultActionRemoveItem;

import org.osori.androidstudy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junsu on 2017-11-09.
 */

public class SwipeViewAdapter extends RecyclerView.Adapter<SwipeViewHolder> implements
        SwipeableItemAdapter<SwipeViewHolder> {

    private List<Item> itemList;

    public SwipeViewAdapter() {
        setHasStableIds(true);

        itemList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            itemList.add(new Item(i, "item: " + String.valueOf(i)));
        }
    }

    @Override
    public long getItemId(int position) {
        return itemList.get(position).id;
    }

    @Override
    public SwipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SwipeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_swipe, parent, false));
    }

    @Override
    public void onBindViewHolder(SwipeViewHolder holder, int position) {
        holder.textView.setText(itemList.get(position).text);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public int onGetSwipeReactionType(SwipeViewHolder holder, int position, int x, int y) {
        return Swipeable.REACTION_CAN_SWIPE_LEFT;
    }

    @Override
    public void onSwipeItemStarted(SwipeViewHolder holder, int position) {
        notifyDataSetChanged();
    }

    @Override
    public void onSetSwipeBackground(SwipeViewHolder holder, int position, int type) {
        // do nothing
    }

    @Override
    public SwipeResultAction onSwipeItem(SwipeViewHolder holder, int position, int result) {
        if (result == Swipeable.RESULT_CANCELED) {
            return new SwipeResultActionDefault();
        } else {
            return new SwipeResultActionMoveToOrigin();
        }
    }

    interface Swipeable extends SwipeableItemConstants {

    }

    static class MySwipeResultActionRemoveItem extends SwipeResultActionRemoveItem {
        private SwipeViewAdapter adapter;
        private int position;

        MySwipeResultActionRemoveItem(SwipeViewAdapter adapter, int position) {
            this.adapter = adapter;
            this.position = position;
        }

        @Override
        protected void onPerformAction() {
            adapter.itemList.remove(position);
            adapter.notifyItemRemoved(position);
        }
    }
}
