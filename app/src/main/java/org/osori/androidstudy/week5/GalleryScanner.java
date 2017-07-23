package org.osori.androidstudy.week5;

import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import org.osori.androidstudy.AndroidApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by junsu on 2017-07-21.
 */

public class GalleryScanner {

    private static final String TAG = GalleryScanner.class.getSimpleName();

    public static final int ALL_PHOTO_BUCKET = 0;

    private static final String[] projection = {
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
    };

//    public static List<Album> albumScan() {
//        Cursor cursor = MediaStore.Images.Media.query(
//                AndroidApplication.getContext().getContentResolver(),
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                projection,
//                null,
//                null,
//                MediaStore.Images.Media.DATE_TAKEN + " DESC");
//
//        int photoIdColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID);
//        int bucketIdColumn = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID);
//        int bucketNameColumn = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
//        int pathColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
//
//        List<Album> albumList = new ArrayList<>();
//        Album allPhotoAlbum = new Album();
//        allPhotoAlbum.setBucketId(ALL_PHOTO_BUCKET);
//        allPhotoAlbum.setBucketName("all photos");
//
//        albumList.add(allPhotoAlbum);
//
//        while (cursor.moveToNext()) {
//            int bucketId = cursor.getInt(bucketIdColumn);
//            String bucketName = cursor.getString(bucketNameColumn);
//            int photoId = cursor.getInt(photoIdColumn);
//            String path = cursor.getString(pathColumn);
//
//            PhotoEntry photoEntry = new PhotoEntry(bucketId, imageId, path);
//
//            if (allPhotosAlbum.coverPhoto == null) {
//                allPhotosAlbum.coverPhoto = photoEntry;
//            }
//
//            if (!albumIdSet.contains(bucketId)) {
//                AlbumEntry newAlbum = new AlbumEntry(bucketId, bucketName);
//                newAlbum.coverPhoto = photoEntry;
//                albumEntryList.add(newAlbum);
//
//                albumIdSet.add(bucketId);
//            }
//        }
//
//        return albumList;
//    }

    public static List<Photo> photoScan(int albumId) {
        String selection = null;
        if (albumId != ALL_PHOTO_BUCKET)
            selection = String.format("%s = %d", MediaStore.Images.Media.BUCKET_ID, albumId);

        Cursor cursor = MediaStore.Images.Media.query(
                AndroidApplication.getContext().getContentResolver(),
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                MediaStore.Images.Media.DATE_TAKEN + " DESC");

        Log.d(TAG, "cursor is initialized and cursor total size: " + cursor.getCount());

        int photoIdColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID);
        int bucketIdColumn = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID);
        int bucketNameColumn = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        int pathColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA);

        List<Photo> photoList = new ArrayList<>();

        while (cursor.moveToNext()) {
            int bucketId = cursor.getInt(bucketIdColumn);
            String bucketName = cursor.getString(bucketNameColumn);
            int photoId = cursor.getInt(photoIdColumn);
            String path = cursor.getString(pathColumn);

            Photo photo = new Photo();
            photo.setBucketId(bucketId);
            photo.setPath(path);

            photoList.add(photo);
        }
        return photoList;
    }
}
