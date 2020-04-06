package kuramoto.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

import javax.swing.JPanel;

import kuramoto.vo.Oscillator;

public class OscillationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private OscillatorApp app = null;

	public OscillationPanel(OscillatorApp oscilador){
		super(null);
		
		this.app = oscilador;
		this.setBackground(Color.white);
	}
	
	public OscillatorApp getOscilador(){
		return app;
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		
		g2.clearRect(0,0,this.getWidth(),this.getHeight());
		g2.setColor(this.getBackground());
		
		g2.fillRect(0,0,this.getWidth(),this.getHeight());
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		g2.setColor(Color.BLACK);
		
		int diameter = this.getWidth() - 50;
		
		if(diameter > this.getHeight() - 50)
			diameter = this.getHeight() - 50;
		
		int x = this.getWidth() / 2 - diameter / 2;
		int y = this.getHeight() / 2 - diameter / 2;

		int axisX = x + diameter / 2;
		int axisY = y + diameter / 2;
		
		g2.setStroke(new BasicStroke(0.5f));
		g.drawOval(x,y,diameter,diameter);
		
		if(app.getOscillators() != null){
			List<Oscillator> oscillators = app.getOscillators();
			
			synchronized(oscillators) {
				for (Oscillator oscillator : app.getOscillators()) {
					x = axisX + (int) (Math.sin(oscillator.getPhase()) * diameter / 2);
					y = axisY + (int) (Math.cos(oscillator.getPhase()) * diameter / 2);
			        
					g.setColor(Color.LIGHT_GRAY);
					g.drawLine(axisX,axisY,x,y);
					
					g.setColor(oscillator.getColor());
					g.fillOval(x - 8,y - 8,17,17);
					
					g.setColor(Color.BLACK);
				}
			}
		}
		
		g.setColor(Color.GRAY);
		g.fillOval(axisX - 2,axisY - 2,5,5);
	}
}
