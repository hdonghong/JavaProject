package team.xg2.percussionplayer.frame;

import java.util.List;

import javax.sound.midi.Sequencer;

import javafx.scene.control.CheckBox;

//节拍类的接口，作为和主窗体联系的桥梁

public interface MusicHandler {
	public void setUpMidi();
	public void buildTrackAndStart();
	public Sequencer getSequencer();
	public void setCheckbox(List<CheckBox> checkboxList);
}
