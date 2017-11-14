package org.osori.androidstudy.week10.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by junsu on 2017-11-13.
 */

public class Memo extends RealmObject {

    @PrimaryKey
    private String title;

    private String contents;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}