package kuramoto.main;

import kuramoto.ui.OscillatorApp;

public class Main {
	public static void main(String[] args) {
		new OscillatorApp(10, 0.5, true)
			.setVisible(true);
	}
}
