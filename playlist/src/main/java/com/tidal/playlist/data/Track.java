package com.tidal.playlist.data;


public class Track{

    private String title;
    private float duration;

    private int trackNumberIdx;

    private int artistId;
    private int id;

    public Track() {
    }

    /**
     * The track index in an album
     */
    public int getTrackNumberIdx() {
        return this.trackNumberIdx;
    }

    public void setTrackNumberIdx(int trackNumberIdx) {
        this.trackNumberIdx = trackNumberIdx;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getDuration() {
        return this.duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}