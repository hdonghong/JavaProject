package team.xg2.percussionplayer.utils;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import javafx.scene.control.ButtonBar.ButtonData;


//工具类

public class PercussionUtils {
	private PercussionUtils() {}
	
	//初始化舞台
	/*
	 * Stage stage：舞台
	 * double relativeX：相对屏幕1/4处的横坐标
	 * double relativeY：相对屏幕1/5处的纵坐标
	 * String title：舞台标题
	 */
	public static void initStage(Stage stage, double relativeX, double relativeY, String title) {
		//设置舞台出现位置
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int screenWidth = (int)screen.getWidth();
		int screenHeight = (int)screen.getHeight();
		stage.setX(screenWidth/4 + relativeX);
		stage.setY(screenHeight/5 + relativeY);
		//设置舞台标题
		stage.setTitle(title);
		//不允许用户调窗口大小
		stage.setResizable(false);
	}
	
	//初始化对话框
	/*
	 * String title：对话框标题
	 * String content：对话框内容
	 * Dialog<String> dialog：对话框实例
	 */
	public static void initDialog(String title, String content, Dialog<String> dialog) {
		 ButtonType confirmButtonType = new ButtonType("确定", ButtonData.YES);
		 dialog.getDialogPane().getButtonTypes().add(confirmButtonType);
		 dialog.setTitle(title);
		 dialog.setContentText(content);
		 dialog.showAndWait();
	}
}
