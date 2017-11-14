package org.osori.androidstudy.week10;

import android.util.Log;

import org.osori.androidstudy.week10.model.Memo;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by junsu on 2017-11-13.
 */

public class MyDBMigration implements RealmMigration {
    private static final String TAG = MyDBMigration.class.getSimpleName();

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        Log.d(TAG, "old: " + oldVersion + " new: " + newVersion);

        RealmSchema schema = realm.getSchema();
        switch ((int) oldVersion) {

        }
    }

    public static final int SCHEMA_VERSION = 0;
}
