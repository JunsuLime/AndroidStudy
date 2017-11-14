package org.osori.androidstudy.week10;

import android.util.Log;

import org.osori.androidstudy.week10.model.Memo;
import org.osori.androidstudy.week10.model.User;

import javax.annotation.Nullable;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.exceptions.RealmException;

/**
 * Created by junsu on 2017-11-14.
 */

public class RealmTestPresenter implements RealmTestContract.Presenter, OrderedRealmCollectionChangeListener<RealmResults<Memo>>, MemoAdapter.OnMemoClickListener {

    private static final String TAG = RealmTestPresenter.class.getSimpleName();

    private RealmTestContract.View mainView;
    private MemoAdapterContract.Model adapterModel;
    private MemoAdapterContract.View adapterView;

    private RealmResults<Memo> memoResults;

    @Override
    public void attachView(RealmTestContract.View view) {
        mainView = view;
    }

    @Override
    public void detachView() {
        mainView = null;
        memoResults.removeAllChangeListeners();
    }

    @Override
    public void attachAdapter(MemoAdapter adapter) {
        adapterModel = adapter;
        adapterView = adapter;

        adapterView.setOnMemoClickListener(this);
    }

    @Override
    public void init() {
        Realm realm = Realm.getDefaultInstance();

        memoResults = realm.where(Memo.class).findAll();
        Log.d(TAG, "before sort: " + memoResults);
        memoResults = memoResults.sort("title");
        Log.d(TAG, "after sort: " + memoResults);

        memoResults.addChangeListener(this);

        adapterModel.init(memoResults);
    }

    @Override
    public void postMemo(String userName, String memoTitle, String memoContents) {
        User user = new User();
        user.setName(userName);

        Memo memo = new Memo();
        memo.setTitle(memoTitle);
        memo.setContents(memoContents);

        try {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();

            user = realm.copyToRealmOrUpdate(user);
            user.getMemoList().add(memo);

            realm.commitTransaction();
        }
        catch (RealmException e) {
            mainView.toastMessage("failure");
            return;
        }
        mainView.toastMessage("success");
    }

    @Override
    public void onChange(RealmResults<Memo> memos, @Nullable OrderedCollectionChangeSet changeSet) {
        Log.d(TAG, "onChange is called with changeSet: " + changeSet);
        if (changeSet == null) {
            adapterView.notifyMemoSetChanged();
            return;
        }

        for (OrderedCollectionChangeSet.Range range : changeSet.getDeletionRanges()) {
            adapterView.notifyMemoRangeDeleted(range.startIndex, range.length);
        }
        for (OrderedCollectionChangeSet.Range range : changeSet.getInsertionRanges()) {
            adapterView.notifyMemoRangeInserted(range.startIndex, range.length);
        }
        for (OrderedCollectionChangeSet.Range range : changeSet.getChangeRanges()) {
            adapterView.notifyMemoRangeModified(range.startIndex, range.length);
        }
    }

    @Override
    public void onMemoDeleteClick(Memo memo) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        memo.deleteFromRealm();
        realm.commitTransaction();
    }

    @Override
    public void onMemoClick(Memo memo) {
        mainView.toastMessage("title: " + memo.getTitle() + " contents: " + memo.getContents());
    }
}
