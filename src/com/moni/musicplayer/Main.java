package com.moni.musicplayer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 程序入口：启动JavaFX应用
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 设置窗口标题
        primaryStage.setTitle("简易音乐播放器");

        // 创建主播放器界面
        com.moni.musicplayer.player.MusicPlayer player = new com.moni.musicplayer.player.MusicPlayer();

        // 创建场景并设置大小（宽度=封面200+列表300+间距20；高度=封面200+控制区50+间距20）
        Scene scene = new Scene(player, 520, 270);

        // 显示窗口
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // 禁止窗口缩放
        primaryStage.show();
    }

    public static void main(String[] args) {
        // 启动JavaFX应用
        launch(args);
    }
}