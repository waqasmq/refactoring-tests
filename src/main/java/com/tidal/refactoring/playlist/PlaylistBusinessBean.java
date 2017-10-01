package com.tidal.refactoring.playlist;

import com.google.inject.Inject; 
import com.tidal.refactoring.playlist.dao.PlaylistDaoBean;
import com.tidal.refactoring.playlist.data.PlayListTrack;
import com.tidal.refactoring.playlist.data.Track;
import com.tidal.refactoring.playlist.data.PlayList;
import com.tidal.refactoring.playlist.exception.PlaylistException;

import java.util.*;
import java.util.stream.Collectors;

/*

*/

public class PlaylistBusinessBean {

    private PlaylistDaoBean playlistDaoBean;

    @Inject
    public PlaylistBusinessBean(PlaylistDaoBean playlistDaoBean){
        this.playlistDaoBean = playlistDaoBean;
    }

    /**
     * Add tracks to the index
     */
    public List<PlayListTrack> addTracks(String uuid, List<Track> tracksToAdd, int toIndex) throws PlaylistException {

        try {

            PlayList playList = playlistDaoBean.getPlaylistByUUID(uuid);

            //We do not allow > 500 tracks in new playlists
            if (playList.getNrOfTracks() + tracksToAdd.size() > 500) {
                throw new PlaylistException("Playlist cannot have more than " + 500 + " tracks");
            }
            // The index is out of bounds, put it in the end of the list.
            int size = playList.getPlayListTracks() == null ? 0 : playList.getPlayListTracks().size();
            if (toIndex > size || toIndex < 0) {
                toIndex = size;
            }
            List<PlayListTrack> added = new ArrayList<PlayListTrack>(tracksToAdd.size());
            for (Track track : tracksToAdd) {
                added.add(addTrack(uuid, track, toIndex)); // add the track to playlist one by one.
                toIndex++;
            }
            return added;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PlaylistException("Generic error");
        }
    }
    
	/**
	 * Remove the tracks from the playlist located at the sent indexes
	 */
	public List<PlayListTrack> removeTracks(String uuid, List<Integer> indexes) throws PlaylistException {
        PlayList playList =  playlistDaoBean.getPlaylistByUUID(uuid);
        List<PlayListTrack> removedTracks = new ArrayList<>();
        for (int index : indexes) {
            PlayListTrack removedTrack = removeTrack(uuid,index);
            if(null != removedTrack) {
                removedTracks.add(removedTrack);
            }
        }
        return removedTracks;
	}

    /*
     returns playlisttrack added to the playlist
    */
	public PlayListTrack addTrack(String uuid, Track track, int toIndex) {
	    PlayList playList = playlistDaoBean.getPlaylistByUUID(uuid);
        PlayListTrack playlistTrack = new PlayListTrack();
	    playlistTrack.setTrack(track);
        playlistTrack.setTrackPlaylist(playList);
        playlistTrack.setDateAdded(new Date());
        playlistTrack.setTrack(track);
        playList.setDuration(addTrackDurationToPlaylist(playList, track));
        playList.getPlayListTracks().add(toIndex, playlistTrack);
        playList.setLastUpdated(new Date());
        return playlistTrack;
    }
    /*
     returns removed to the playlist
    */

    public PlayListTrack removeTrack(String uuid , int index) {
        PlayList playList =  playlistDaoBean.getPlaylistByUUID(uuid);
        List<PlayListTrack> tracks = playList.getPlayListTracks();
        PlayListTrack playListTrack = null;
        if(index >= 0 && index < tracks.size()) { // validate the index to be removed to avoid nullpointer.
           playListTrack =  tracks.remove(index);
           playList.setLastUpdated(new Date());
            subtractTrackDurationFromPlaylist(playList, playListTrack.getTrack()); // subtract the duration of removed track from the playlist duration.
        }
        return playListTrack;
    }

    public float getPlaylistDuration(String uuid) {
	    return playlistDaoBean.getPlaylistByUUID(uuid).getDuration();
    }
    private float addTrackDurationToPlaylist(PlayList playList, Track track) {
        return (track != null ? track.getDuration() : 0)
                + (playList != null && playList.getDuration() != null ? playList.getDuration() : 0);
    }
    private void subtractTrackDurationFromPlaylist(PlayList playList, Track track) {
        float playListDuration = playList != null && playList.getDuration() != null ? playList.getDuration() : 0;
        float trackDuration = track != null ? track.getDuration() : 0;
        float remainingDuration = playListDuration - trackDuration;
        playList.setDuration(remainingDuration);
    }
}
