package org.osori.androidstudy;

import android.app.Application;
import android.content.Context;

import org.osori.androidstudy.week10.MyDBMigration;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by junsu on 2017-07-21.
 */

public class AndroidApplication extends Application {

    private static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();

        // Initializing Realm
        Realm.init(this);


        // Configuring a Realm
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("osori.realm")
                .migration(new MyDBMigration())
                .schemaVersion(MyDBMigration.SCHEMA_VERSION)
                .build();

        Realm.setDefaultConfiguration(config);
    }

    public static Context getContext() {
        return applicationContext;
    }
}
