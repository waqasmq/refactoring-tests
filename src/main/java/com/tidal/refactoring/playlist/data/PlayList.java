package com.tidal.refactoring.playlist.data;

import java.util.*;


/**
 * No of tracks property removed. getNrOfRecords should return size of playListTracks instead.
 *
 */
public class PlayList {

    private Integer id;
    private String playListName;
    private List<PlayListTrack> playListTracks = new ArrayList<>(); // list instead of set.
    private Date registeredDate;
    private Date lastUpdated;
    private String uuid;
    private boolean deleted;
    private Float duration;

    public PlayList() {
        this.uuid = UUID.randomUUID().toString();
        Date d = new Date();
        this.registeredDate = d;
        this.lastUpdated = d;
        this.playListTracks = new ArrayList<>();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    public List<PlayListTrack> getPlayListTracks() {
        return playListTracks;
    }

    public void setPlayListTracks(List<PlayListTrack> playListTracks) {
        this.playListTracks = playListTracks;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getNrOfTracks() {
        return this.playListTracks.size(); // number of tracks is always the size of tracks list
    }
    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }
}