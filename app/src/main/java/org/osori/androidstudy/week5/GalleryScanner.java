package org.osori.androidstudy.week5;

import android.database.Cursor;
import android.location.Location;
import android.media.ExifInterface;
import android.provider.MediaStore;
import android.util.Log;

import org.osori.androidstudy.AndroidApplication;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by junsu on 2017-07-21.
 */

public class GalleryScanner {

    private static final String TAG = GalleryScanner.class.getSimpleName();

    public static final int ALL_PHOTO_BUCKET = 0;

    private static final String[] projection = {
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media.DATE_MODIFIED,
            MediaStore.Images.Media.LATITUDE,
            MediaStore.Images.Media.LONGITUDE,
            MediaStore.Images.Media._ID,
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

    // 위치 정보가 있는 사진만 가져오기 위한 selection query
    private static final String LOCATION_FILTER =
            String.format("((%s is not null and %s is not null) or (%s is not null and %s is not null))",
                    MediaStore.Images.Media.LATITUDE, MediaStore.Images.Media.LONGITUDE,
                    MediaStore.Images.ImageColumns.LATITUDE, MediaStore.Images.ImageColumns.LONGITUDE);

    public static List<Photo> photoScan(int albumId) {
        String selection = null;
        if (albumId != ALL_PHOTO_BUCKET)
            selection = String.format("%s = %d", MediaStore.Images.Media.BUCKET_ID, albumId);

        Cursor cursor = MediaStore.Images.Media.query(
                AndroidApplication.getContext().getContentResolver(),
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                MediaStore.Images.Media.DATE_TAKEN + " DESC");

        Log.d(TAG, "cursor is initialized and cursor total size: " + cursor.getCount());

        DateFormat toFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

        int photoIdCol = cursor.getColumnIndex(MediaStore.Images.Media._ID);
        int bucketIdCol = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID);
        int bucketNameCol = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        int pathCol = cursor.getColumnIndex(MediaStore.Images.Media.DATA);

        int latitudeCol = cursor.getColumnIndex(MediaStore.Images.Media.LATITUDE);
        int longitudeCol = cursor.getColumnIndex(MediaStore.Images.Media.LONGITUDE);

        int dateTakenCol = cursor.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN);
        int dateAddedCol = cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED);
        int dateModifiedCol = cursor.getColumnIndex(MediaStore.Images.Media.DATE_MODIFIED);


        List<Photo> photoList = new ArrayList<>();

        int i = 0;
        while (cursor.moveToNext() && i < 20) {
            i++;
            int bucketId = cursor.getInt(bucketIdCol);
            String bucketName = cursor.getString(bucketNameCol);
            int photoId = cursor.getInt(photoIdCol);
            String path = cursor.getString(pathCol);

            long dateTaken = cursor.getLong(dateTakenCol);
            long dateAdded = cursor.getLong(dateAddedCol);
            long dateModified = cursor.getLong(dateModifiedCol);

            String latitude = cursor.getString(latitudeCol);
            String longitude = cursor.getString(longitudeCol);

            Log.d(TAG, "path: " + path);

            Log.d(TAG, "dateTaken: " + toFormat.format(new Date(dateTaken)));
            Log.d(TAG, "dateAdded: " + toFormat.format(new Date(dateAdded)));
            Log.d(TAG, "dateModified: " + toFormat.format(new Date(dateModified)));
            Log.d(TAG, "latitude: " + latitude);
            Log.d(TAG, "longitude: " + longitude);

            // ExifInterface: Excahgnedable image file format (사진 정보)
            ExifInterface exif = null;
            try {
                exif = new ExifInterface(path);
                Log.d(TAG, "exif datetime: " + exif.getAttribute(ExifInterface.TAG_DATETIME));

                String lat = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
                String lng = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
                String latRef = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
                String lngRef = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);

                Log.d(TAG, "exif lat: " + exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE));
                Log.d(TAG, "exif lng: " + exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE));
                Log.d(TAG, "exif lat ref: " + exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF));
                Log.d(TAG, "exif lng ref: " + exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF));
                GLocation location = getLocation(lat, latRef, lng, lngRef);
                Log.d(TAG, "exif latitude: " + location.latitude);
                Log.d(TAG, "exif longitude: " + location.longitude);
            } catch (IOException e) {
                Log.d(TAG, "IOException e: " + e.toString());
            } catch (NullPointerException ne) {
                Log.d(TAG, "NullPointerException ne: " + ne.toString());
            }

            Log.d(TAG, "********************************************************************");


            Photo photo = new Photo();
            photo.setBucketId(bucketId);
            photo.setPath(path);

            photoList.add(photo);
        }
        return photoList;
    }

    private static GLocation getLocation(String latitude, String latitudeRef, String longitude, String longitudeRef) {
        Map<String, Integer> ref = new HashMap<String, Integer>();
        ref.put("N", 1);
        ref.put("E", 1);
        ref.put("S", -1);
        ref.put("W", -1);

        String[] latArr = latitude.split(",");
        String[] lngArr = longitude.split(",");
        float[] latArrInt = new float[latArr.length];
        float[] lngArrInt = new float[lngArr.length];

        for (int i = 0; i < latArr.length; i++) {
            String[] numArr = latArr[i].split("/");
            latArrInt[i] = Float.parseFloat(numArr[0]) / Integer.parseInt(numArr[1]);
        }

        for (int i = 0; i < lngArr.length; i++) {
            String[] numArr = lngArr[i].split("/");
            lngArrInt[i] = Float.parseFloat(numArr[0]) / Integer.parseInt(numArr[1]);
        }

        double convertLat = (latArrInt[0] + (latArrInt[1] / 60) + (latArrInt[2] / 3600)) * ref.get(latitudeRef);
        double convertLng = (lngArrInt[0] + (lngArrInt[1] / 60) + (lngArrInt[2] / 3600)) * ref.get(longitudeRef);

        return new GLocation(convertLat, convertLng);
    }

    static class GLocation {
        private double latitude;
        private double longitude;

        GLocation(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }
}
