package com.moni.musicplayer.component;

import com.moni.musicplayer.model.Song;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;

/**
 * 播放控制面板：包含播放/暂停按钮和状态显示，负责音频播放逻辑
 */
public class ControlPanel extends HBox {
    private Button playPauseBtn;      // 播放/暂停按钮
    private Label statusLabel;        // 状态显示标签
    private MediaPlayer mediaPlayer;  // JavaFX音频播放器
    private boolean isPlaying;        // 播放状态标记（true=播放中，false=暂停/停止）

    public ControlPanel() {
        // 初始化控件
        playPauseBtn = new Button("播放");
        statusLabel = new Label("等待加载...");
        isPlaying = false;

        // 设置控件样式
        playPauseBtn.setPrefSize(80, 30); // 按钮大小
        playPauseBtn.setStyle("-fx-font-size: 14px;");
        statusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #333333;");

        // 绑定按钮点击事件：切换播放/暂停
        playPauseBtn.setOnAction(e -> togglePlayPause());

        // 布局控件（按钮+状态标签，间距10px，居中对齐）
        setSpacing(10);
        setStyle("-fx-padding: 10px; -fx-alignment: center; -fx-background-color: #eaeaea;");
        getChildren().addAll(playPauseBtn, statusLabel);
    }

    /**
     * 加载指定歌曲并准备播放
     * @param song 要加载的歌曲
     */
    public void loadSong(Song song) {
        try {
            // 释放之前的播放器资源（防止内存泄漏）
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            }

            // 从resources目录加载音乐文件（通过类路径）
            URL musicUrl = getClass().getResource("/" + song.getFilePath());
            if (musicUrl == null) {
                throw new Exception("音乐文件不存在：" + song.getFilePath());
            }

            // 创建媒体对象和播放器
            Media media = new Media(musicUrl.toExternalForm());
            mediaPlayer = new MediaPlayer(media);

            // 监听播放结束事件
            mediaPlayer.setOnEndOfMedia(() -> {
                isPlaying = false;
                playPauseBtn.setText("播放");
                statusLabel.setText("播放结束");
            });

            // 显示加载成功状态
            statusLabel.setText("已加载：" + song.getTitle());

        } catch (Exception e) {
            statusLabel.setText("加载失败：" + e.getMessage());
            System.err.println("音乐加载错误：" + e.getMessage());
        }
    }

    /**
     * 切换播放/暂停状态
     */
    private void togglePlayPause() {
        if (mediaPlayer == null) {
            statusLabel.setText("请先选择歌曲");
            return;
        }

        if (isPlaying) {
            // 当前播放中 → 暂停
            mediaPlayer.pause();
            playPauseBtn.setText("播放");
            statusLabel.setText("已暂停");
        } else {
            // 当前暂停 → 播放
            mediaPlayer.play();
            playPauseBtn.setText("暂停");
            statusLabel.setText("播放中...");
        }
        isPlaying = !isPlaying; // 切换状态标记
    }
}