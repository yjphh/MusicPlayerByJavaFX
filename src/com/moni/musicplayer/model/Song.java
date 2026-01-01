package com.moni.musicplayer.model;

/**
 * 歌曲模型类，封装单首歌曲的核心信息
 * 存储资源相对路径（相对于resources目录）
 */
public class Song {
    private String title;       // 歌曲标题
    private String artist;      // 艺术家
    private String filePath;    // 音乐文件相对路径（如"music/xxx.mp3"）
    private String albumCoverPath; // 专辑封面相对路径（如"images/xxx.png"）

    // 构造方法：初始化歌曲信息
    public Song(String title, String artist, String filePath, String albumCoverPath) {
        this.title = title;
        this.artist = artist;
        this.filePath = filePath;
        this.albumCoverPath = albumCoverPath;
    }

    // Getter方法：获取歌曲信息
    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getAlbumCoverPath() {
        return albumCoverPath;
    }

    // 重写toString：在播放列表中显示"歌曲名 - 艺术家"
    @Override
    public String toString() {
        return title + " - " + artist;
    }
}