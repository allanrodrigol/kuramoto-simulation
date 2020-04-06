package kuramoto.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import kuramoto.ui.thread.OscillationThread;
import kuramoto.vo.Oscillator;

public class OscillatorApp extends JFrame {
	private static final long serialVersionUID = 1L;

	private OscillationPanel oscillation = null;
	private ConfigurationPanel config = null;
	private FrequencyPanel frequency = null;
	private ComplexPanel complex = null;
	private GraphPanel graph = null;
	
	private OscillationThread thread = null;
	
	private List<Oscillator> oscillators = null;
	
	public OscillatorApp(int n, double prob, boolean normalize){
		this.generateOscillators(n, prob, normalize);
		
		this.getContentPane().add(this.getConfigurationPanel(),BorderLayout.WEST);
		
		JPanel header = new JPanel(new GridLayout(1,2));
		header.add(this.getGraphPanel());
		header.add(this.getOscillationPanel());
		
		this.getContentPane().add(header,BorderLayout.CENTER);
		
		JPanel footer = new JPanel(new GridLayout(1,2));
		footer.add(this.getFrequencyPanel());
		footer.add(this.getComplexPanel());
		
		this.getContentPane().add(footer,BorderLayout.SOUTH);
		
		this.setSize(new Dimension(950,650));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.getOscillationThread().start();
	}
	
	public GraphPanel getGraphPanel(){
		if (graph == null) {
			graph = new GraphPanel(this);
		}
		
		return graph;
	}
	
	public OscillationPanel getOscillationPanel() {
		if (oscillation == null) {
			oscillation = new OscillationPanel(this);
		}
		
		return oscillation;
	}
	
	public ConfigurationPanel getConfigurationPanel() {
		if (config == null) {
			config = new ConfigurationPanel(this);
		}
		
		return config;
	}
	
	public FrequencyPanel getFrequencyPanel() {
		if (frequency == null) {
			frequency = new FrequencyPanel(this);
		}
		
		return frequency;
	}
	
	public ComplexPanel getComplexPanel() {
		if (complex == null) {
			complex = new ComplexPanel(this);
		}
		
		return complex;
	}
	
	public OscillationThread getOscillationThread(){
		if (thread == null) {
			thread = new OscillationThread(this);
		}
		
		return thread;
	}
	
	public void setOsciladores(List<Oscillator> osciladores){
		this.oscillators = osciladores; 
	}
	
	public List<Oscillator> getOscillators(){
		return this.oscillators; 
	}
	
	public void generateOscillators(int n, double prob, boolean normalize) {
		int[][] g = Oscillator.generateGraph(n, prob);
		
		oscillators = Oscillator.createOscillators(g, normalize);
	}
}
