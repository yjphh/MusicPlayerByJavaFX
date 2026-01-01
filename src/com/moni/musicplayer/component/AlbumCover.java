package com.moni.musicplayer.component;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import java.io.InputStream;

/**
 * 专辑封面组件：显示在播放器左侧，负责加载和显示专辑封面
 */
public class AlbumCover extends BorderPane {
    private ImageView coverView; // 用于显示图片的控件

    public AlbumCover() {
        // 初始化图片显示控件
        coverView = new ImageView();
        coverView.setPreserveRatio(true); // 保持图片比例
        coverView.setFitWidth(200);       // 固定宽度
        coverView.setFitHeight(200);      // 固定高度
        coverView.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1px;"); // 边框样式
        setCenter(coverView); // 将图片放在面板中心
    }

    /**
     * 根据相对路径加载并显示封面图片
     * @param relativePath 图片相对于resources的路径（如"images/album_cover.png"）
     */
    public void setCoverImage(String relativePath) {
        try {
            // 通过类加载器从resources目录加载图片（兼容打包后运行）
            InputStream imageStream = getClass().getResourceAsStream("/" + relativePath);
            if (imageStream == null) {
                throw new Exception("图片资源不存在：" + relativePath);
            }
            // 从输入流加载图片
            Image image = new Image(imageStream);
            coverView.setImage(image);
        } catch (Exception e) {
            System.err.println("封面加载失败：" + e.getMessage());
            coverView.setImage(null); // 加载失败时清空图片
        }
    }
}