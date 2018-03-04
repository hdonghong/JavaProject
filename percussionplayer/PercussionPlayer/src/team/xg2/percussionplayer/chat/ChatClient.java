package team.xg2.percussionplayer.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import team.xg2.percussionplayer.utils.PercussionUtils;

public class ChatClient {
	private Stage chatStage;			//表示当前主舞台
	private TextArea textBefore;		//接收区域
	private TextArea textNow;			//发送区域
	private Button sendBtn;				//发送按钮 
	private BufferedReader recvReader;	//读取接收的消息
	private PrintWriter sendWriter;		//写入发送的消息
	private Socket sock;				//套接字
	private String userName;			//用户昵称
	private String serverIp;			//服务器端ip
	private boolean[] onChatting;		//是否可以开启聊天
	private double primaryWidth;		//获取主窗体的宽度，用于设置聊天窗口的位置
	
	public ChatClient() {}
	public ChatClient(String userName, String serverIp, boolean[] onChatting, double primaryWidth) {
		this.userName = userName;
		this.serverIp = serverIp;
		this.onChatting = onChatting;
		this.primaryWidth = primaryWidth;
		buildGUI();
		init();
	}
	
	//实现聊天窗口图形界面的方法
	public void buildGUI() {
		BorderPane p0 = new BorderPane();
		//create border pane1 and border pane2
		BorderPane p1 = new BorderPane();
		BorderPane p2 = new BorderPane();
		BorderPane p3 = new BorderPane();
		
		p0.setPadding(new Insets(5,5,5,5));
		p1.setPadding(new Insets(0,5,5,5));
		p2.setPadding(new Insets(5,5,5,5));
		p3.setPadding(new Insets(5,5,5,15));
		
		p0.setTop(p1);
		p0.setCenter(p2);
		p0.setBottom(p3);
		p3.setCenter(sendBtn = new Button("发送"));
		
		//文本域
		textBefore = new TextArea();
		textNow = new TextArea();
		textBefore.setEditable(false);		//接收区域不可编辑
		textBefore.setWrapText(true);		//自动换行
		textNow.setWrapText(true);
		textBefore.setPrefSize(400, 320);
		textNow.setPrefSize(400, 45);
		p1.setCenter(textBefore);
		p2.setCenter(textNow);
		
		chatStage = new Stage();
		chatStage.setScene(new Scene(p0));
		PercussionUtils.initStage(chatStage, primaryWidth, 0, userName+"的聊天窗口");
		chatStage.show();//显示舞台
		
		chatStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override	//窗口关闭事件监听
			public void handle(WindowEvent arg0) {
				try {
					sendWriter.println(userName + "[" + InetAddress.getLocalHost().getHostAddress() + "]" + "退出聊天室\r\n");
				} catch (Exception e) { e.printStackTrace(); }
				onChatting[0] = false;
			}
		});
	}

	public void init() {		
		//发送消息按钮添加监听
		sendBtn.setOnAction(e ->  {
				try { //输出流写入数据
					sendWriter.println(userName + "[" + InetAddress.getLocalHost().getHostAddress() + "]" + " 说:");
					sendWriter.println(textNow.getText() + "\r\n");				
				} catch(Exception event) {
					System.out.println(event);
				}
				textNow.setText("");
				textNow.requestFocus();	//光标聚焦在发送消息版块
			}
		);
		
		setUpConnection(serverIp);		//建立与服务器的连接
		if (onChatting[0]) { 			//如果可以开启聊天
			Thread readerThread = new Thread(new ReceMsgReader());
			readerThread.start(); 		//开启新的线程，负责接收来自服务器端的消息
		} else {
			chatStage.close();
		}
	}
	
	//建立客户端与服务器端的连接
	public void setUpConnection(String ip) {
		try {
			sock = new Socket(ip, 6666); 
			recvReader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
										//获取Socket的读取流
			sendWriter = new PrintWriter(sock.getOutputStream(), true);
			sendWriter.println(userName + "[" + InetAddress.getLocalHost().getHostAddress() + "]" + "进入聊天室\r\n");
		} catch (Exception ex) {
			logInError(); 				//登录失败
			onChatting[0] = false; 		//不允许继续开启聊天
		}
	}
	
	//登录失败的处理方法
	public void logInError() {
		//错误对话框
		String title = "错误";
		String errmsg = "建立连接失败";
		PercussionUtils.initDialog(title, errmsg, new Dialog<>());
	}
	
	//接收处理来自服务器端的消息
	public class ReceMsgReader implements Runnable {
		public void run() {
			String message;
			try {
				while ((message = recvReader.readLine()) != null) {
					textBefore.appendText(message + "\r\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
