package com.moni.musicplayer.player;

import com.moni.musicplayer.component.AlbumCover;
import com.moni.musicplayer.component.ControlPanel;
import com.moni.musicplayer.component.PlaylistView;
import com.moni.musicplayer.player.PlaylistManager;
import com.moni.musicplayer.model.Song;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * 主播放器界面：组合所有组件，构建完整UI结构
 */
public class MusicPlayer extends VBox { // 垂直布局（上半部分+控制面板）
    private AlbumCover albumCover;      // 左侧封面
    private PlaylistView playlistView;  // 右侧播放列表
    private ControlPanel controlPanel;  // 底部控制面板
    private PlaylistManager playlistManager; // 播放列表管理器
    private HBox topContainer;          // 上半部分容器（水平布局封面和列表）

    public MusicPlayer() {
        // 初始化组件
        albumCover = new AlbumCover();
        playlistView = new PlaylistView();
        controlPanel = new ControlPanel();
        playlistManager = new PlaylistManager();
        topContainer = new HBox(); // 水平排列封面和列表

        // 加载播放列表到视图
        playlistView.setPlaylist(playlistManager.getPlaylist());

        // 监听播放列表选中事件：切换歌曲时更新封面和加载音乐
        playlistView.getSongListView().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldSong, newSong) -> {
                    if (newSong != null) {
                        albumCover.setCoverImage(newSong.getAlbumCoverPath());
                        controlPanel.loadSong(newSong);
                    }
                }
        );

        // 初始化时默认加载第一首歌
        if (!playlistManager.getPlaylist().isEmpty()) {
            Song firstSong = playlistManager.getPlaylist().get(0);
            albumCover.setCoverImage(firstSong.getAlbumCoverPath());
            controlPanel.loadSong(firstSong);
        }

        // 组装上半部分（封面+列表，间距10px）
        topContainer.getChildren().addAll(albumCover, playlistView);
        topContainer.setSpacing(10);
        topContainer.setStyle("-fx-padding: 10px;");

        // 组装主界面（上半部分+控制面板）
        getChildren().addAll(topContainer, controlPanel);
        setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #dddddd;");
    }
}