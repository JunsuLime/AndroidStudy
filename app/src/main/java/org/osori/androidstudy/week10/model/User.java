package org.osori.androidstudy.week10.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by junsu on 2017-11-13.
 */

public class User extends RealmObject {

    @PrimaryKey
    private String name;
    private RealmList<Memo> memoList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Memo> getMemoList() {
        return memoList;
    }

    public void setMemoList(RealmList<Memo> memoList) {
        this.memoList = memoList;
    }
}
