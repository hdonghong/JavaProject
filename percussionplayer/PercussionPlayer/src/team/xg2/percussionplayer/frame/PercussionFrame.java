package team.xg2.percussionplayer.frame;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import team.xg2.percussionplayer.chat.ChatClient;
import team.xg2.percussionplayer.chat.InputDialog;
import team.xg2.percussionplayer.music.PercussionMusic;
import team.xg2.percussionplayer.utils.PercussionUtils;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Sequencer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;

// 程序的主窗体类
public class PercussionFrame extends Application {
	//用于存储主舞台，方便弹框操作
	private Stage primaryStage;
	//复选框列表
	private List<CheckBox> checkboxList;
	// 设置按钮
	private Button btStart;
	private Button btStop;
	private Button btUpTempo;
	private Button btDownTempo;
	private Button btRandom;
	private Button btClear;
	private Button btSerializelt;
	private Button btLoad;
	private Button btChat;
	private Button btAbout;
	//音乐的处理器
	private MusicHandler musicHandler;
	//播放控制
	private Sequencer sequencer;
	//标识是否可以进入聊天室
	public boolean[] onChatting = new boolean[]{false};
	
	public PercussionFrame() {
		musicHandler = new PercussionMusic();	//music类和本类通过接口联系
	}
	
	//启动start方法，创建图形界面
	public void buildGUI() { 
		Application.launch(new String[0]);
	}
	
	//实现主图形界面的方法
	public void start(Stage primaryStage) {
		//获取主舞台
		this.primaryStage = primaryStage;
		
		//获取创建按钮
		btStart = new Button("开始播放");
		btStop = new Button("暂停播放");
		btUpTempo = new Button("增强节奏");
		btDownTempo = new Button("减弱节奏");
		btRandom = new Button("随机乐曲");
		btClear = new Button("清空节拍");
		btSerializelt = new Button("存档");
		btLoad = new Button("载入");
		btChat = new Button("聊天");
		btAbout = new Button("关于");
		
		//统一按钮尺寸
		btStart.setPrefWidth(80);
		btStop.setPrefWidth(80);
		btUpTempo.setPrefWidth(80);
		btDownTempo.setPrefWidth(80);
		btRandom.setPrefWidth(80);
		btClear.setPrefWidth(80);
		btSerializelt.setPrefWidth(80);
		btLoad.setPrefWidth(80);
		btChat.setPrefWidth(80);
		btAbout.setPrefWidth(80);
		
		// 主面板:BorderPane
		BorderPane bPane = new BorderPane();
		bPane.setPadding(new Insets(10, 10, 10, 10));
		
		// 将面板添加到scene
		Scene scene = new Scene(bPane);
		
		// 设置背景图片
		Image image = new Image("background.png");
		ImageView iv = new ImageView();
		iv.setImage(image);
		iv.setSmooth(true);
		iv.fitHeightProperty().bind(bPane.heightProperty());
		iv.fitWidthProperty().bind(bPane.widthProperty());
		bPane.getChildren().add(iv);

		// 在主面板的左边\中间\右边设置GridPane面板
		GridPane gPaneleft = new GridPane();
		GridPane gPaneright = new GridPane();
		GridPane gPanecenter = new GridPane();
		gPanecenter.setPadding(new Insets(0, 5, 0, 5));	// 中间面板的外边距
		bPane.setLeft(gPaneleft);
		bPane.setCenter(gPanecenter);
		bPane.setRight(gPaneright);

		// 设置面板的内部节点间距
		gPaneleft.setVgap(6);
		gPaneright.setVgap(6);
		gPanecenter.setVgap(5);

		// 左边面板添加label
		gPaneleft.add(new Label("低音鼓"), 0, 0);
		gPaneleft.add(new Label("闭镲"), 0, 1);
		gPaneleft.add(new Label("开镲"), 0, 2);
		gPaneleft.add(new Label("原声军鼓"), 0, 3);
		gPaneleft.add(new Label("强音钹"), 0, 4);
		gPaneleft.add(new Label("拍手"), 0, 5);
		gPaneleft.add(new Label("高音桶鼓"), 0, 6);
		gPaneleft.add(new Label("高音邦戈"), 0, 7);
		gPaneleft.add(new Label("沙球"), 0, 8);
		gPaneleft.add(new Label("口哨"), 0, 9);
		gPaneleft.add(new Label("低音康加"), 0, 10);
		gPaneleft.add(new Label("牛铃"), 0, 11);
		gPaneleft.add(new Label("颤音叉"), 0, 12);
		gPaneleft.add(new Label("低音桶鼓"), 0, 13);
		gPaneleft.add(new Label("高音拉丁打铃"), 0, 14);
		gPaneleft.add(new Label("高音康加"), 0, 15);

		// 右边面板添加按钮
		gPaneright.add(btStart, 0, 0);
		gPaneright.add(btStop, 0, 1);
		gPaneright.add(btUpTempo, 0, 2);
		gPaneright.add(btDownTempo, 0, 3);
		gPaneright.add(btRandom, 0, 4);
		gPaneright.add(btClear, 0, 5);
		gPaneright.add(btSerializelt, 0, 6);
		gPaneright.add(btLoad, 0, 7);
		gPaneright.add(btChat, 0, 8);
		gPaneright.add(btAbout, 0, 9);
		
		checkboxList = new ArrayList<CheckBox>();
		// 设置16行*16列复选框,置于中间面板
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				CheckBox c = new CheckBox("");
				c.setOpacity(0.75);
				c.setSelected(false);
				checkboxList.add(c);
				gPanecenter.add(c, i, j);
			}
		}
		// 将scene添加到stage
		primaryStage.setScene(scene);
		// 调用工具类初始化舞台
		PercussionUtils.initStage(primaryStage, 0, 0, "敲击乐达人");
		//显示舞台
		primaryStage.show();
		//设置关闭程序
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent arg0) {
				System.exit(0);
			}
		});
		//初始化程序
		init(true);
	}
	
	//初始化程序
	public void init(boolean bl) {
		//按钮注册事件
		if (bl) {
			btStart.setOnAction(new StartAction());
			btStop.setOnAction(new StopAction());
			btUpTempo.setOnAction(new UpTempoAction());	
			btDownTempo.setOnAction(new DownTempoAction());
			btRandom.setOnAction(new RandomAction());
			btClear.setOnAction(new ClearAction());		
			btSerializelt.setOnAction(new SaveAction());
			btLoad.setOnAction(new LoadAction());
			btChat.setOnAction(new ChatAction());
			btAbout.setOnAction(new AboutAction());
			
			musicHandler.setUpMidi();		
			sequencer = musicHandler.getSequencer();	//获得播放控制器
		}
	}

	//开始播放的事件监听处理
	public class StartAction implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			musicHandler.setCheckbox(checkboxList);
			musicHandler.buildTrackAndStart();
		}
	}
	//暂停播放的事件监听处理
	public class StopAction implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			sequencer.stop();
		}
	}
	//增强节奏的事件监听处理
	public class UpTempoAction implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			float tempoFactor = sequencer.getTempoFactor(); 		//返回音序器的当前速度因子。 默认值为1.0
			sequencer.setTempoFactor((float)(tempoFactor * 1.03));	//增强节奏
		}
	}
	//减弱节奏的事件监听处理
	public class DownTempoAction implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor * 0.97));	//减弱节奏
		}
	}
	//随机乐曲的事件监听处理
	public class RandomAction implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			for (CheckBox checkBox : checkboxList) {
				checkBox.setSelected(Math.random() > 0.8); //按照1：4的比例打勾
			}
			sequencer.stop();
		}
	}
	//清空节拍的事件监听处理
	public class ClearAction implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			for (CheckBox checkBox : checkboxList) {
				checkBox.setSelected(false);
			}
			sequencer.stop();
		}
	}
	//存档的事件监听处理
	public class SaveAction implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			boolean[] checkboxState = new boolean[256];
			for (int i = 0; i < 256; ++i) {
				CheckBox check = checkboxList.get(i);
				if (check.isSelected()) {
					checkboxState[i] = true;
				}
			}
			FileChooser fileSave = new FileChooser();
			File file = fileSave.showSaveDialog(primaryStage);
			if (file != null)
				saveFile(file, checkboxState);	
		}
	}
	//载入的事件监听处理
	public class LoadAction implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			FileChooser fileLoad = new FileChooser();
			File file = fileLoad.showOpenDialog(primaryStage);
			if (file == null) return;
			boolean[] checkboxState = loadFile(file);
			if (checkboxState != null)
				for (int i = 0; i < 256; ++i) {
					CheckBox check = checkboxList.get(i);
					check.setSelected(checkboxState[i]);	//复选框打勾
				}
			else {
				//错误对话框
				String title = "错误";
				String content = "载入文件失败，请重新选择";
				PercussionUtils.initDialog(title, content, new Dialog<>());
			}
			sequencer.stop(); 	//停止正在播放的节奏
		}
	}
	//聊天的事件监听处理
	public class ChatAction implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			if (!onChatting[0]) { //如果当前没有开启聊天，则可以弹出登陆框
				InputDialog input = new InputDialog(onChatting, primaryStage.getWidth(), primaryStage.getHeight());
				if (onChatting[0]) { //如果用户开启了聊天且完成了登录，则可以进入聊天室
					new ChatClient(input.getName(), input.getIp(), onChatting, primaryStage.getWidth());
				}
			} else {
				//错误对话框
				String title = "错误";
				String content = "您处于登录模式或已进入聊天室";
				PercussionUtils.initDialog(title, content, new Dialog<>());
			}
		}
	}
	//关于的事件监听处理
	public class AboutAction implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			//读取文件并显示到对话框上
			String title = "关于";
			String content = readFile(new File("README.txt")).toString(); 
			PercussionUtils.initDialog(title, content, new Dialog<>());
		}
	}
	//存储文件	
	public void saveFile(File file, boolean[] checkboxState) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(checkboxState);
			os.close();
		} catch(Exception ex) {
			System.out.println(ex + "\r\n存档失败");
		}
	}
	//加载文件
	public boolean[] loadFile(File file) {
		boolean[] checkboxState = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream is = new ObjectInputStream(fis);
			checkboxState = (boolean[])is.readObject();
			is.close();
		} catch (Exception ex) {
			return null;
		}
		return checkboxState;
	}
	//读取文件
	public StringBuilder readFile(File file) {
		StringBuilder destString = new StringBuilder();
		try {
			InputStream in = PercussionFrame.class.getResourceAsStream(file.getName()); //从当前加载类目录寻找文件
			BufferedReader bufr = new BufferedReader(new InputStreamReader(in));
			String line = null;
			boolean flag = false;
			while ((line = bufr.readLine()) != null) {
				if (flag) destString.append("\r\n" + line);
				else flag = true;
			}
			bufr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return destString;
	}
}