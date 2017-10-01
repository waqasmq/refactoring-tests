package com.tidal.refactoring.playlist;

import com.google.inject.Inject;
import com.tidal.refactoring.playlist.data.PlayList;
import com.tidal.refactoring.playlist.data.PlayListTrack;
import com.tidal.refactoring.playlist.data.Track;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.testng.AssertJUnit.assertTrue;

@Guice(modules = TestBusinessModule.class)
public class PlaylistBusinessBeanTest {

    @Inject
    PlaylistBusinessBean playlistBusinessBean;

    @BeforeMethod
    public void setUp() throws Exception {

    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddTracks() throws Exception {
        List<Track> trackList = new ArrayList<Track>();
        Track track = new Track();
        track.setArtistId(4);
        track.setTitle("A brand new track");
        track.setId(76868);
        trackList.add(track);

        List<PlayListTrack> playListTracks = playlistBusinessBean.addTracks(UUID.randomUUID().toString(), trackList, 5);
        assertThat(playListTracks, Matchers.iterableWithSize(Matchers.greaterThan(0)));

    }
    @Test
    public void testAddTrackAtNegativeIndex() {
        List<Track> trackList = new ArrayList<Track>();
        Track track = new Track();
        track.setArtistId(4);
        track.setTitle("A brand new track");
        track.setId(76868);
        trackList.add(track);

        List<PlayListTrack> playListTracks = playlistBusinessBean.addTracks(UUID.randomUUID().toString(), trackList, -5);
        assertThat(playListTracks, Matchers.iterableWithSize(Matchers.greaterThan(0)));
    }

    @Test
    public void testAddTrackAtHigherIndex() {
        List<Track> trackList = new ArrayList<Track>();
        Track track = new Track();
        track.setArtistId(4);
        track.setTitle("A brand new track");
        track.setId(76868);
        trackList.add(track);

        List<PlayListTrack> playListAddedTracks = playlistBusinessBean.addTracks(UUID.randomUUID().toString(), trackList, 500);
        assertThat(playListAddedTracks, Matchers.iterableWithSize(Matchers.greaterThan(0)));
    }
    @Test
    public void testRemoveTrackFromPlayListTest() {
        List<Integer> indexes = Arrays.asList(5,10);
        List<PlayListTrack> removedTracks = playlistBusinessBean.removeTracks(UUID.randomUUID().toString(), indexes);
        assertThat(removedTracks, Matchers.iterableWithSize(Matchers.equalTo(2)));
    }
    @Test
    public void testPlaylistDurationAfterAddTrackTest() {
        Track track = new Track();
        track.setArtistId(4);
        track.setTitle("A brand new track");
        track.setId(76868);
        track.setDuration(180);
        String uuid = UUID.randomUUID().toString();
        float duration = playlistBusinessBean.getPlaylistDuration(uuid);
        PlayListTrack playListAddedTrack = playlistBusinessBean.addTrack(uuid, track, 376);
        float durationAfterAdd = playlistBusinessBean.getPlaylistDuration(uuid);
        assertThat(durationAfterAdd, Matchers.equalTo(duration+track.getDuration()));
    }
    @Test
    public void deleteTracksFromPlayListTest() {
        List<Integer> indexes = Arrays.asList(5,19);
        List<PlayListTrack> removedTracks = playlistBusinessBean.removeTracks(UUID.randomUUID().toString(),indexes);
        assertThat(removedTracks, Matchers.iterableWithSize(Matchers.equalTo(2)));
    }
    @Test
    public void testDeleteTrackFromPlayListAtNegativeIndexTest() {
        PlayListTrack removedTrack = playlistBusinessBean.removeTrack(UUID.randomUUID().toString(), -5);
        assertThat(removedTrack, Matchers.nullValue()); // return null if index is not present
    }
    @Test
    public void testPlaylistDurationAfterDeleteTrackTest() {
        Track track = new Track();
        track.setArtistId(4);
        track.setTitle("A brand new track");
        track.setId(76868);
        track.setDuration(180);
        String uuid = UUID.randomUUID().toString();
        float duration = playlistBusinessBean.getPlaylistDuration(uuid);
        playlistBusinessBean.addTrack(uuid, track, 376);
        playlistBusinessBean.removeTrack(uuid,376);
        float durationAfterRemove = playlistBusinessBean.getPlaylistDuration(uuid);
        assertThat(durationAfterRemove, Matchers.equalTo(duration));
    }

}