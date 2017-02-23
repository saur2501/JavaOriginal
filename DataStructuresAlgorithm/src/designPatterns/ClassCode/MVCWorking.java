package designPatterns.ClassCode;

import java.awt.event.*;
import java.util.*;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.Sequencer;
import javax.swing.*;

interface BeatObserver{}
interface BPMObserver{}

interface BeatModelInterface {
	void initialize();
	void on();
	void off();
	void setBPM(int bpm);
	int getBPM();
	void registerObserver(BeatObserver o);
	void removeObserver(BeatObserver o);
	void registerObserver(BPMObserver o);
	void removeObserver(BPMObserver o);
}

class BeatModel implements BeatModelInterface, MetaEventListener {
	Sequencer sequencer;	//in metaEventListener
	ArrayList beatObservers = new ArrayList();
	ArrayList bpmObservers = new ArrayList();
	int bpm = 90;
	// other instance variables here
	public void initialize() {
		setUpMidi();
		buildTrackAndStart();
	}
	private void setUpMidi() {}
	private void buildTrackAndStart() {}
	public void on() {
		sequencer.start();
		setBPM(90);
	}
	public void off() {
		setBPM(0);
		sequencer.stop();
	}
	public void setBPM(int bpm) {
		this.bpm = bpm;
		sequencer.setTempoInBPM(getBPM());
		notifyBPMObservers();
	}
	public int getBPM() {
		return bpm;
	}
	void beatEvent() {
		notifyBeatObservers();
	}
	private void notifyBPMObservers() {}
	private void notifyBeatObservers() {}
	// Code to register and notify observers
	// Lots of MIDI code to handle the beat
	public void meta(MetaMessage meta) {}
	public void registerObserver(BeatObserver o) {}
	public void removeObserver(BeatObserver o) {}
	public void registerObserver(BPMObserver o) {}
	public void removeObserver(BPMObserver o) {}
}

interface BeatBar{
	void setValue(int i);
}
class DJView implements ActionListener, BeatObserver, BPMObserver {
	BeatModelInterface model;
	ControllerInterface controller;
	JFrame viewFrame;
	JPanel viewPanel;
	BeatBar beatBar;
	JLabel bpmOutputLabel;
	
	JLabel bpmLabel;
	JTextField bpmTextField;
	JButton setBPMButton;
	JButton increaseBPMButton;
	JButton decreaseBPMButton;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem startMenuItem;
	JMenuItem stopMenuItem;
	public DJView(ControllerInterface controller, BeatModelInterface model) {
		this.controller = controller;
		this.model = model;
		model.registerObserver((BeatObserver)this);
		model.registerObserver((BPMObserver)this);
	}
	public void createView() {
		// Create all Swing components here
	}
	public void updateBPM() {
		int bpm = model.getBPM();
		if (bpm == 0) {
			bpmOutputLabel.setText("offline");
		} else {
			bpmOutputLabel.setText("Current BPM: " + model.getBPM());
		}
	}
	public void updateBeat() {
		beatBar.setValue(100);
	}
	public void createControls() {
		// Create all Swing components here
	}
	public void enableStopMenuItem() {
		stopMenuItem.setEnabled(true);
	}
	public void disableStopMenuItem() {
		stopMenuItem.setEnabled(false);
	}
	public void enableStartMenuItem() {
		startMenuItem.setEnabled(true);
	}
	public void disableStartMenuItem() {
		startMenuItem.setEnabled(false);
	}
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == setBPMButton) {
			int bpm = Integer.parseInt(bpmTextField.getText());
			controller.setBPM(bpm);
		} else if (event.getSource() == increaseBPMButton) {
			controller.increaseBPM();
		} else if (event.getSource() == decreaseBPMButton) {
			controller.decreaseBPM();
		}
	}
}

interface ControllerInterface {
	void start();
	void stop();
	void increaseBPM();
	void decreaseBPM();
	void setBPM(int bpm);
}

class BeatController implements ControllerInterface {
	BeatModelInterface model;
	DJView view;
	public BeatController(BeatModelInterface model) {
		this.model = model;
		view = new DJView(this, model);
		view.createView();
		view.createControls();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
		model.initialize();
	}
	public void start() {
		model.on();
		view.disableStartMenuItem();
		view.enableStopMenuItem();
	}
	public void stop() {
		model.off();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
	}
	public void increaseBPM() {
		int bpm = model.getBPM();
		model.setBPM(bpm + 1);
	}
	public void decreaseBPM() {
		int bpm = model.getBPM();
		model.setBPM(bpm - 1);
	}
	public void setBPM(int bpm) {
		model.setBPM(bpm);
	}
}

public class MVCWorking {
	public static void main (String[] args) {
		BeatModelInterface model = new BeatModel();
		ControllerInterface controller = new BeatController(model);
	}
}