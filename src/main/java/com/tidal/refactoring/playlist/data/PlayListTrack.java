package com.tidal.refactoring.playlist.data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: eivind.hognestad@wimpmusic.com
 * Date: 15.04.15
 * Time: 12.46
 */
public class PlayListTrack implements Serializable, Comparable<PlayListTrack> {

    private static final long serialVersionUID = 5464240796158432162L;

    private Integer id;
    private TrackPlayList profileTrackPlayList;
    private int trackArtistId;
    private int index;
    private Date dateAdded;
    private SharingLevel sharingLevel;
    private String description;
    private int trackId;
    private String isrc;

    private Track track;

    public PlayListTrack() {
        dateAdded = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public TrackPlayList getTrackPlayList() {
        return profileTrackPlayList;
    }

    public void setTrackPlaylist(TrackPlayList profileTrackPlayList) {
        this.profileTrackPlayList = profileTrackPlayList;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getTrackArtistId() {
        return trackArtistId;
    }

    public void setTrackArtistId(int trackArtistId) {
        this.trackArtistId = trackArtistId;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int compareTo(PlayListTrack o) {
        return this.getIndex() - o.getIndex();
    }

    public SharingLevel getSharingLevel() {
        return sharingLevel;
    }

    public void setSharingLevel(SharingLevel sharingLevel) {
        this.sharingLevel = sharingLevel;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayListTrack that = (PlayListTrack) o;

        if (index != that.index) return false;
        if (trackArtistId != that.trackArtistId) return false;
        if (trackId != that.trackId) return false;
        if (dateAdded != null ? !dateAdded.equals(that.dateAdded) : that.dateAdded != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return !(id != null ? !id.equals(that.id) : that.id != null) && sharingLevel == that.sharingLevel;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + trackArtistId;
        result = 31 * result + index;
        result = 31 * result + (dateAdded != null ? dateAdded.hashCode() : 0);
        result = 31 * result + (sharingLevel != null ? sharingLevel.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + trackId;
        return result;
    }

    public String toString() {
        return "PlayListTrack id[" + getId() + "], description[" + getDescription() + "]";
    }
}
