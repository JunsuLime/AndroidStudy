package org.osori.androidstudy.week10;

import org.osori.androidstudy.week10.model.Memo;

import io.realm.RealmResults;

/**
 * Created by junsu on 2017-11-14.
 */

public interface MemoAdapterContract {

    interface View {
        void notifyMemoRangeInserted(int from, int count);
        void notifyMemoRangeDeleted(int from, int count);
        void notifyMemoRangeModified(int from ,int count);

        void notifyMemoSetChanged();

        void setOnMemoClickListener(MemoAdapter.OnMemoClickListener listener);
    }
    interface Model {
        void init(RealmResults<Memo> memoResults);
    }
}
