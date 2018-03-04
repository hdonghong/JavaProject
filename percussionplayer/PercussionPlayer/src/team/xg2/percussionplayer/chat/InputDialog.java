package team.xg2.percussionplayer.chat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import team.xg2.percussionplayer.utils.PercussionUtils;

public class InputDialog implements EventHandler<ActionEvent>{
	private Stage inputStage;	 //当前舞台
	private TextField nameField; //用户输入昵称的文本框
	private TextField ipField;	 //用于输入ip的文本框
	private Button btAdd;		 //确认按钮
	private String name;		 //用户呢称
	private String ip;			 //服务器端IP地址
	private Label lblStatus;	 //用于显示错误提示的标签
	private boolean[] onChatting;//判断能否开启聊天的标识
	private double primaryStageWidth;	//主舞台宽度，用于设置输入框弹出位置
	private double primaryStageHeight;	//主舞台高度

	public InputDialog() {}
	public InputDialog(boolean[] onChatting, double primaryStageWidth, double primaryStageHeight) {
		this.onChatting = onChatting;
		this.onChatting[0] = true;
		this.primaryStageWidth = primaryStageWidth;
		this.primaryStageHeight = primaryStageHeight;
		buildGUI();
	}
	
	public void buildGUI() {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(10,10,10,10));
		pane.setHgap(5);
		pane.setVgap(5);
		
		nameField = new TextField();
		ipField = new TextField();
		
		pane.add(new Label("昵称"),0,0);
		pane.add(nameField,1,0);
		pane.add(new Label("服务器端ip"),0,1);
		pane.add(ipField,1,1);
		btAdd = new Button("确定");
		pane.add(btAdd,1,2);
		GridPane.setHalignment(btAdd,HPos.RIGHT);
		pane.add(new Label("温馨提示:"), 0, 3);
		lblStatus = new Label();
		pane.add(lblStatus, 1, 3);
		
		// 如果名字文本框获取焦点，则将错误提示设置为null
		nameField.focusedProperty().addListener(e -> setLabelTip("black", "请输入登录信息"));
		// 如果数字2文本框获取焦点，则将错误提示设置为null
		ipField.focusedProperty().addListener(e -> setLabelTip("black", "请输入登录信息"));
		// 按钮添加监听器
		btAdd.setOnAction(this);
		
		Scene scene = new Scene(pane);
		inputStage = new Stage();
		inputStage.setScene(scene);
		PercussionUtils.initStage(inputStage, primaryStageWidth/3, primaryStageHeight/3,"登录");
		inputStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override						//窗口关闭事件监听
			public void handle(WindowEvent arg0) {
				onChatting[0] = false;
			}
		});
		
		inputStage.showAndWait();
	}

	@Override
	//按钮监听器处理
	public void handle(ActionEvent e) {
		String regex = "[0-9]{0,3}\\.[0-9]{0,3}\\.[0-9]{0,3}\\.[0-9]{0,3}";
		if (nameField.getText().equals("") || ipField.getText().equals("")) {
		//如果文本框为空
			setLabelTip("red", "请输入完整登录信息");
			//红色提示
		} else if (!ipField.getText().matches(regex)) {
		//ip格式不符合正则表达式
			setLabelTip("red", "服务器端IP地址格式错误");
		} else {
		//符合要求
			name = nameField.getText();
			ip = ipField.getText();
			inputStage.close();
		}
	}
	
	//设置提示信息
	public void setLabelTip(String color, String tip) {
		lblStatus.setStyle("-fx-text-fill: " + color + ";");
		lblStatus.setText(tip);
	}
	
	public String getName() {
		return name;
	}
	public String getIp() {
		return ip;
	}
}
