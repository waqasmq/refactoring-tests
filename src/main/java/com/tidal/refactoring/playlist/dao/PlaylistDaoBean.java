package com.tidal.refactoring.playlist.dao;

import java.util.*; 

import com.tidal.refactoring.playlist.data.PlayListTrack;
import com.tidal.refactoring.playlist.data.Track;
import com.tidal.refactoring.playlist.data.PlayList;

/**
 * Class faking the data layer, and returning fake playlists
 */
public class PlaylistDaoBean {

    /**
     * Returning fake TrackPlaylist
     *
     * @param uuid
     * @param userId
     * @return
     */
    public PlayList getPlaylistByUUID(String uuid, int userId) {
        PlayList trackPlayList = new PlayList();

        trackPlayList.setDeleted(false);
        trackPlayList.setDuration((float) (60 * 60 * 2));
        trackPlayList.setId(49834);
        trackPlayList.setLastUpdated(new Date());
        trackPlayList.setNrOfTracks(376);
        trackPlayList.setPlayListName("Collection of great songs");
        trackPlayList.setPlayListTracks(getPlaylistTracks());
        trackPlayList.setUuid(uuid);

        return trackPlayList;
    }

    private static Set<PlayListTrack> getPlaylistTracks() {

        Set<PlayListTrack> playListTracks = new HashSet<PlayListTrack>();
        for (int i = 0; i < 376; i++) {
            PlayListTrack playListTrack = new PlayListTrack();
            playListTrack.setDateAdded(new Date());
            playListTrack.setDescription("A description");
            playListTrack.setId(i + 1);
            playListTrack.setIndex(i);
            playListTrack.setTrack(getTrack());

        }

        return playListTracks;
    }

    public static Track getTrack() {
        Random randomGenerator = new Random();

        Track track = new Track();
        track.setArtistId(randomGenerator.nextInt(10000));
        track.setDuration(60 * 3);

        int trackNumber = randomGenerator.nextInt(15);
        track.setTitle("Track no: " + trackNumber);
        track.setTrackNumberIdx(trackNumber);

        return track;
    }
}
