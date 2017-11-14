package org.osori.androidstudy.week10;

import org.osori.androidstudy.week10.model.Memo;

/**
 * Created by junsu on 2017-11-14.
 */

public interface RealmTestContract {

    interface View {
        void toastMessage(String message);
    }

    interface Presenter {
        void attachView(RealmTestContract.View view);
        void detachView();

        void attachAdapter(MemoAdapter adapter);

        void init();

        void postMemo(String userName, String memoTitle, String memoContents);
    }
}
