package kuramoto.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;

import kuramoto.vo.Oscillator;

public class ConfigurationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JSlider speed = null;
	private JSlider strength = null;
	private JSlider density = null;
	private JSpinner oscillators = null;
	
	private JLabel labelSpeed = null;
	private JLabel labelStrength = null;
	private JLabel labelDensity = null;
	
	private JCheckBox normalize = null;
	
	private JButton configurate = null;
	private JButton start = null;
	
	private OscillatorApp app = null;
	
	private Font font = new Font("Lucida Console",Font.PLAIN, 12);
	
	public ConfigurationPanel(OscillatorApp app){
		super(null);
		
		this.app = app;
		
		this.setPreferredSize(new Dimension(290,170));
		this.setBackground(Color.WHITE);
		
		JLabel label = null;
		
		label = new JLabel("Speed:");
		label.setBounds(10,10,100,15);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setFont(font);
		
		this.add(label);
		this.add(this.getSliderSpeed());
		this.add(this.getLabelSpeed());
		
		label = new JLabel("Coupling (K):");
		label.setBounds(10,40,100,15);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setFont(font);
		
		this.add(label);
		this.add(this.getSliderStrength());
		this.add(this.getLabelStrength());
		
		label = new JLabel("Oscillators (N):");
		label.setBounds(10,72,100,18);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setFont(font);
		
		this.add(label);
		this.add(this.getSpinnerOscillators());
		
		label = new JLabel("Density (p):");
		label.setBounds(10,100,100,18);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setFont(font);
		
		this.add(label);
		this.add(this.getSliderDensity());
		this.add(this.getLabelDensity());
		
		this.add(this.getCheckBoxNormalize());
		
		this.add(this.getButtonConfigurate());
		this.add(this.getButtonStart());
	}
	
	public JSlider getSliderSpeed(){
		if(speed == null){
			speed = new JSlider(JSlider.HORIZONTAL,0,100,(int) (Oscillator.DEFAULT_SPEED * 100));
			speed.setBounds(110,10,130,20);
			speed.setBackground(Color.WHITE);
			
			speed.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					JSlider slider = (JSlider) e.getSource();

					DecimalFormat format = new DecimalFormat("##0.00");
					getLabelSpeed().setText(String.valueOf(format.format((double) slider.getValue() / 100)));
				}
			});
		}
		
		return speed;
	}
	
	public JLabel getLabelSpeed(){
		if(labelSpeed == null){
			DecimalFormat format = new DecimalFormat("##0.00");
			
			labelSpeed = new JLabel(String.valueOf(format.format(Oscillator.DEFAULT_SPEED)));
			labelSpeed.setVerticalAlignment(JLabel.CENTER);
			
			labelSpeed.setBounds(250,10,50,15);
			labelSpeed.setFont(font);
		}
		
		return labelSpeed;
	}

	public JSlider getSliderStrength(){
		if(strength == null){
			strength = new JSlider(JSlider.HORIZONTAL,0,2000,0);
			strength.setBounds(110,40,130,20);
			strength.setBackground(Color.WHITE);
			
			strength.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					JSlider c = (JSlider) e.getSource();
					
					DecimalFormat format = new DecimalFormat("##0.00");
					
					getLabelStrength().setText(String.valueOf(format.format((double) c.getValue() / 100)));
				}
			});
		}
		
		return strength;
	}
	
	public JLabel getLabelStrength(){
		if(labelStrength == null){
			DecimalFormat formato = new DecimalFormat("##0.00");
			
			labelStrength = new JLabel(String.valueOf(formato.format(0)));
			labelStrength.setVerticalAlignment(JLabel.CENTER);
			
			labelStrength.setBounds(250,40,50,15);
			labelStrength.setFont(font);
		}
		
		return labelStrength;
	}
	
	public JSpinner getSpinnerOscillators(){
		if(oscillators == null){
			oscillators = new JSpinner();
			oscillators.setModel(new SpinnerNumberModel(app.getOscillators().size(),0,500,1));
			
			JFormattedTextField field = (JFormattedTextField) oscillators.getEditor().getComponent(0);
		    DefaultFormatter format = (DefaultFormatter) field.getFormatter();
		    format.setCommitsOnValidEdit(true);
		    format.setAllowsInvalid(false);
			
			oscillators.setBounds(115,70,50,25);
			oscillators.setFont(font);
		}
		
		return oscillators;
	}

	public JCheckBox getCheckBoxNormalize(){
		if(normalize == null){
			normalize = new JCheckBox("Normalize?");
			normalize.setBounds(180,70,95,25);
			normalize.setBackground(Color.WHITE);
			
			normalize.setSelected(true);
			normalize.setFont(font);
		}
		
		return normalize;
	}
	
	public JSlider getSliderDensity(){
		if(density == null){
			density = new JSlider(JSlider.HORIZONTAL,0,100,50);
			density.setBounds(110,100,130,20);
			density.setBackground(Color.WHITE);
			
			density.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					JSlider slider = (JSlider) e.getSource();

					DecimalFormat format = new DecimalFormat("##0.00");
					getLabelDensity().setText(String.valueOf(format.format((double) slider.getValue() / 100)));
				}
			});
		}
		
		return density;
	}
	
	public JLabel getLabelDensity(){
		if(labelDensity == null){
			DecimalFormat format = new DecimalFormat("##0.00");
			
			labelDensity = new JLabel(String.valueOf(format.format(0.50)));
			labelDensity.setVerticalAlignment(JLabel.CENTER);
			
			labelDensity.setBounds(250,100,50,15);
			labelDensity.setFont(font);
		}
		
		return labelDensity;
	}
	
	public JButton getButtonConfigurate(){
		if(configurate == null){
			configurate = new JButton("Configure");
			configurate.setBounds(70,140,95,25);
			configurate.setFont(font);
			
			configurate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int    n = (int) ((Integer) getSpinnerOscillators().getValue()).intValue();
					double p = (double) getSliderDensity().getValue() / 100;
					
					int[][] g = Oscillator.generateGraph(n, p);
					
					app.setOsciladores(Oscillator.createOscillators(g,getCheckBoxNormalize().isSelected()));
					
					app.getGraphPanel().repaint();
					app.getOscillationPanel().repaint();
				}
			});
		}
		
		return configurate;
	}
	
	public JButton getButtonStart(){
		if(start == null){
			start = new JButton("Start");
			start.setBounds(180,140,95,25);
			start.setFont(font);
			
			start.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean active = app.getOscillationThread().isActive();
					
					getButtonStart().setText(active ? "Start" : "Stop");
					
					if(!active){
						app.getFrequencyPanel().criateSeries();
						app.getComplexPanel().createSeries();
					}
					
					getSpinnerOscillators().setEnabled(active);
					getSliderDensity().setEnabled(active);
					getCheckBoxNormalize().setEnabled(active);
					getButtonConfigurate().setEnabled(active);
					
					app.getOscillationThread().setActive(!active);
				}
			});
		}
		
		return start;
	}
}
