package org.osori.androidstudy.week10;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.osori.androidstudy.R;
import org.osori.androidstudy.week10.model.Memo;
import org.osori.androidstudy.week10.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmResults;

/**
 * Created by junsu on 2017-11-14.
 */

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.MemoHolder>
    implements
        MemoAdapterContract.View,
        MemoAdapterContract.Model
{

    private static final String TAG = MemoAdapter.class.getSimpleName();

    private LayoutInflater inflater;

    private RealmResults<Memo> memoList;
    private OnMemoClickListener listener;

    public MemoAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MemoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MemoHolder(inflater.inflate(R.layout.item_memo, parent, false));
    }

    @Override
    public void onBindViewHolder(MemoHolder holder, int position) {
        holder.bind(memoList.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount is called with memoList: " + memoList);
        if (memoList == null)
            return 0;
        else
            return memoList.size();
    }

    @Override
    public void notifyMemoRangeInserted(int from, int count) {
        notifyItemRangeInserted(from, count);
    }

    @Override
    public void notifyMemoRangeDeleted(int from, int count) {
        notifyItemRangeRemoved(from, count);
    }

    @Override
    public void notifyMemoRangeModified(int from, int count) {
        notifyItemRangeChanged(from, count);
    }

    @Override
    public void notifyMemoSetChanged() {
        notifyDataSetChanged();
    }

    @Override
    public void setOnMemoClickListener(OnMemoClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void init(RealmResults<Memo> memoResults) {
        this.memoList = memoResults;
    }

    class MemoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.memo_user_name_text)
        TextView userName;
        @BindView(R.id.memo_title_text)
        TextView memoTitle;
        @BindView(R.id.memo_contents_text)
        TextView memoContents;

        private Memo memo;

        public MemoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        void bind(Memo memo) {
            this.memo = memo;
            User user = memo.getUsers().first();

            userName.setText(user.getName());
            memoTitle.setText(memo.getTitle());
            memoContents.setText(memo.getContents());
        }

        @OnClick(R.id.memo_delete_button)
        public void onClickMemoDeleteButton() {
            listener.onMemoDeleteClick(memo);
        }

        @Override
        public void onClick(View view) {
            listener.onMemoClick(memo);
        }
    }

    public interface OnMemoClickListener {
        void onMemoDeleteClick(Memo memo);
        void onMemoClick(Memo memo);
    }
}
