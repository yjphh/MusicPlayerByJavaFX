package com.moni.musicplayer.component;

import com.moni.musicplayer.model.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import java.util.List;

/**
 * 播放列表视图：显示在播放器右侧，展示歌曲列表并支持选中
 */
public class PlaylistView extends BorderPane {
    private ListView<Song> songListView; // 歌曲列表控件
    private ObservableList<Song> songList; // 可观察列表（用于UI绑定）

    public PlaylistView() {
        // 初始化列表控件
        songListView = new ListView<>();
        songList = FXCollections.observableArrayList();
        songListView.setItems(songList); // 绑定数据到列表
        songListView.setPrefWidth(300); // 固定列表宽度
        songListView.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1px;"); // 边框样式
        setCenter(songListView); // 将列表放在面板中心
    }

    /**
     * 更新播放列表数据
     * @param songs 要显示的歌曲列表
     */
    public void setPlaylist(List<Song> songs) {
        songList.clear();
        songList.addAll(songs);
        // 默认选中第一首歌（如果列表不为空）
        if (!songs.isEmpty()) {
            songListView.getSelectionModel().select(0);
        }
    }

    // 获取当前选中的歌曲
    public Song getSelectedSong() {
        return songListView.getSelectionModel().getSelectedItem();
    }

    // 提供ListView的getter，用于外部监听选中事件
    public ListView<Song> getSongListView() {
        return songListView;
    }
}