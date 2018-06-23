package vampire.utils.sound;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {

	public static Sound[] sounds = new Sound[3];

	static {
		new JFXPanel();
	}

	private volatile MediaPlayer sound;
	private String soundFile = "";

	public Sound(String file) {
		create(file);
	}

	private void create(final String file) {
		// final File soundFile = new File(file);
		// if (!soundFile.exists()) {
		// System.err.println("Sound File \"" + file + "\" not found!");
		// return;
		// }
		// System.out.println("Sound File \"" + file + "\" loading succeded!");
		// String[] strings = file.split("/");
		// this.soundFile = strings[strings.length - 1];

		try {
			// sound = new MediaPlayer(new Media(soundFile.toURI().toString()));
			System.out.print("Loading file from " + file);
			sound = new MediaPlayer(new Media(Sound.class.getResource(file).toString()));
			System.out.println(" succeded!");

		} catch (Exception e) {
			System.err.println(" failed!");
			e.printStackTrace();
		}

	}

	public void setGain(double gain) {
		sound.setVolume(gain);
	}

	public void play() {
		new Thread("Sound") {
			public void run() {
				sound.stop();
				sound.setCycleCount(0);
				sound.play();
			}
		}.start();
	}

	public void loop() {
		new Thread("Sound: " + soundFile) {
			public void run() {
				sound.stop();
				sound.setCycleCount(MediaPlayer.INDEFINITE);
				sound.play();
			}
		}.start();
	}

	public void stop() {
		sound.stop();
	}
}
