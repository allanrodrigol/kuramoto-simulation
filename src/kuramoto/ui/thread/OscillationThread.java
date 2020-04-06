package kuramoto.ui.thread;

import java.util.List;

import kuramoto.ui.OscillatorApp;
import kuramoto.vo.Oscillator;

public class OscillationThread extends Thread {
	private OscillatorApp app = null;
	
	private boolean active = false; 

	public OscillationThread(OscillatorApp app){
		this.app = app;
	}
	
	public void run(){
		while (true) {
			if (this.isActive()) {
				if (app.getOscillators() != null){
					List<Oscillator> oscillators = app.getOscillators();
					double value = (double) app.getConfigurationPanel().getSliderStrength().getValue() / 100;
					
					final double strength = value / oscillators.size();
					
					oscillators.forEach(o -> o.move(o.synchronize(strength)));
					
					app.getOscillationPanel().repaint();
					
					app.getFrequencyPanel().updateDatasets();
					app.getComplexPanel().updateDatasets();
				}
			}
           
			try {
				int tempo = (int) 100 - app.getConfigurationPanel().getSliderSpeed().getValue();
				
				if(tempo < 10)
					tempo = 10;
        	   
				Thread.sleep(tempo);
			} catch (InterruptedException ex) {
			}
		}
	}
	
	public void setActive(boolean active){
		this.active = active;
	}
	
	public boolean isActive(){
		return this.active;
	}
}
