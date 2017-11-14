package org.osori.androidstudy.week10;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import org.osori.androidstudy.R;
import org.osori.androidstudy.week10.model.Memo;
import org.osori.androidstudy.week10.model.User;

import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by junsu on 2017-11-13.
 */

public class RealmTestActivity extends AppCompatActivity implements
    RealmTestContract.View {

    private RealmTestPresenter presenter;

    @BindView(R.id.memo_user_name_text)
    EditText userText;
    @BindView(R.id.memo_title_text)
    EditText titleText;
    @BindView(R.id.memo_contents_text)
    EditText contentsText;
    @BindView(R.id.memo_recycler_view)
    RecyclerView memoRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_test);
        ButterKnife.bind(this);

        presenter = new RealmTestPresenter();
        presenter.attachView(this);

        MemoAdapter adapter = new MemoAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        memoRecyclerView.setAdapter(adapter);
        memoRecyclerView.setLayoutManager(layoutManager);

        presenter.attachAdapter(adapter);

        presenter.init();

//

//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.create
//            }
//        });
//
//        RealmResults<User> results = realm.where(User.class).findAll();
//
//        results.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<User>>() {
//            @Override
//            public void onChange(RealmResults<User> users, @javax.annotation.Nullable OrderedCollectionChangeSet changeSet) {
//
//            }
//        });
//
//        User someone = realm.where(User.class).findFirst();
//        someone.getMemoList().sort("title", Sort.ASCENDING);
//        someone.getMemoList()
//        realm.executeTransactionAsync(
//                new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//
//                    }
//                },
//                new Realm.Transaction.OnSuccess() {
//                    @Override
//                    public void onSuccess() {
//
//                    }
//                },
//                new Realm.Transaction.OnError() {
//                    @Override
//                    public void onError(Throwable error) {
//
//                    }
//                });

    }

    @Override
    public void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.memo_post_button)
    public void onClickPostMemoButton() {
        String userName = String.valueOf(userText.getText());
        String memoTitle = String.valueOf(titleText.getText());
        String memoContents = String.valueOf(contentsText.getText());

        presenter.postMemo(userName, memoTitle, memoContents);
    }
}
