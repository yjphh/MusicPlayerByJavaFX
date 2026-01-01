package com.moni.musicplayer.player;

import com.moni.musicplayer.model.Song;
import java.util.ArrayList;
import java.util.List;

/**
 * 播放列表管理器：负责初始化和管理歌曲列表
 */
public class PlaylistManager {
    private List<Song> playlist; // 存储歌曲的列表

    // 初始化播放列表（添加一首示例歌曲）
    public PlaylistManager() {
        playlist = new ArrayList<>();
        // 注意：这里的路径是相对于resources目录的相对路径
        playlist.add(new Song(
                "Throwaway",
                "Unknown Artist",
                "music/Throwaway.mp3",   // 音乐文件在resources中的路径
                "images/album_cover.png" // 封面图片在resources中的路径
        ));
    }

    // 获取完整播放列表
    public List<Song> getPlaylist() {
        return playlist;
    }
}