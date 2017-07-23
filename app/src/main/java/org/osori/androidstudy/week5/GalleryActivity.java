package org.osori.androidstudy.week5;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.osori.androidstudy.R;

import java.util.List;

/**
 * Created by junsu on 2017-07-21.
 */

public class GalleryActivity extends AppCompatActivity implements PhotoAdapter.PhotoClickListener {

    private static final String TAG = GalleryActivity.class.getSimpleName();

    private RecyclerView photoListView;
    private PhotoAdapter adapter;

    private final int STORAGE_PERMISSION_REQUEST = 2017;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        photoListView = (RecyclerView) findViewById(R.id.photo_list_view);

        adapter = new PhotoAdapter(this);
        adapter.setOnItemClickListener(this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);

        photoListView.setAdapter(adapter);
        photoListView.setLayoutManager(layoutManager);

        Log.d(TAG, "permission request is called");
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, permissions, STORAGE_PERMISSION_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case STORAGE_PERMISSION_REQUEST:
                if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
                    runGalleryScan();
                }
        }
    }

    private void runGalleryScan() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Photo> photoList = GalleryScanner.photoScan(GalleryScanner.ALL_PHOTO_BUCKET);
                Log.d(TAG, "photoScan is done");
                Log.d(TAG, "photo list size: " + photoList.size());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "runOnUiThread run is called");
                        adapter.addPhotoList(photoList);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClickPhoto(Photo photo) {
        Toast.makeText(this, photo.getPath(), Toast.LENGTH_SHORT).show();
    }
}
