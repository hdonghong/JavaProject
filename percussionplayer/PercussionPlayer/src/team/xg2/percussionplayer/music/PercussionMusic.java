package team.xg2.percussionplayer.music;

import java.util.List;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import javafx.scene.control.CheckBox;
import team.xg2.percussionplayer.frame.MusicHandler;

//节拍类

public class PercussionMusic implements MusicHandler{
	private Sequencer sequencer;				//播放控制器
	private Sequence sequence;					//信息组织
	private Track track;						//轨道，存储乐曲事件
	private List<CheckBox> checkboxList;		//复选框列表
	
	private int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};
	
	public Sequencer getSequencer() {
		return sequencer;
	}
	
	public void setCheckbox(List<CheckBox> checkboxList) {
		this.checkboxList = checkboxList;
	}
	
	public void buildTrackAndStart() {
		int[] trackList = null;				//存储一项乐器的值
		
		sequence.deleteTrack(track);		//清除去旧的track，创建新的
		track = sequence.createTrack();
		
		for (int i = 0; i < 16; ++i) {		//每次执行一种乐器
			trackList = new int[16];
			int key = instruments[i];		//设定乐器的关键字
			for (int j = 0; j < 16; ++j) {
				CheckBox jc = checkboxList.get((16*i) + j);
											//16*i 是为了定位到第i行，+j是为了定位到第j列		
				trackList[j] = jc.isSelected() ? key : 0;
											//复选框被勾选
			}		
			makeTracks(trackList);			//创建此乐器的事件并加到track上
			track.add(makeEvent(176, 1, 127, 0, 16));
		}
		
		track.add(makeEvent(192, 9, 1, 0, 16));	
											//确保第16拍存在事件，以保证beatbox可以重复播放
		try {
			sequencer.setSequence(sequence);
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);	//指定无穷的重复次数
			sequencer.start();				//开始播放
			sequencer.setTempoInBPM(120);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//初始化Midi信息
	public void setUpMidi() {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequence = new Sequence(Sequence.PPQ, 4);
			track = sequence.createTrack();	//创建乐曲轨道
			sequencer.setTempoInBPM(120);	//设置每分钟节拍的速度
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//创建某项乐器的所有事件
	public void makeTracks(int[] list) {	
		for (int i = 0; i < 16; ++i) {
			int key = list[i];
			
			if (key != 0) {
				track.add(makeEvent(144, 9, key, 100, i));	//note on事件
				track.add(makeEvent(128, 9, key, 100, i+1));//note off事件
			}
		}
	}
	//创建Midi事件，相当于一段乐曲信息
	public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return event;
	}



}
