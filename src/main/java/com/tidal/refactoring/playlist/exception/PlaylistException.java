package com.tidal.refactoring.playlist.exception;

/**
 * @author: eivind.hognestad@wimpmusic.com
 * Date: 15.04.15
 * Time: 12.52
 */
public class PlaylistException extends RuntimeException {

	private static final long serialVersionUID = 759495431208011733L;

	public PlaylistException(String s) {
        super(s);
    }
}
