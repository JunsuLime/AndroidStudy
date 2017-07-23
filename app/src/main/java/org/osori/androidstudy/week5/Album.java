package org.osori.androidstudy.week5;

/**
 * Created by junsu on 2017-07-21.
 */

public class Album {
    private int bucketId;
    private String bucketName;

    private Photo coverPhoto;

    public int getBucketId() {
        return bucketId;
    }

    public void setBucketId(int bucketId) {
        this.bucketId = bucketId;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public Photo getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(Photo coverPhoto) {
        this.coverPhoto = coverPhoto;
    }
}
