package kuramoto.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JPanel;

import kuramoto.vo.Oscillator;

public class GraphPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private OscillatorApp app = null;

	public GraphPanel(OscillatorApp app){
		super(null);
		
		this.app = app;
		this.setBackground(Color.WHITE);
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		
		g2.clearRect(0,0,this.getWidth(),this.getHeight());
		g2.setColor(this.getBackground());
		
		g2.fillRect(0,0,this.getWidth(),this.getHeight());
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		g2.setColor(Color.LIGHT_GRAY);
		
		int diameter = this.getWidth() - 50;
		
		if(diameter > this.getHeight() - 50)
			diameter = this.getHeight() - 50;
		
		int x = this.getWidth() / 2 - diameter / 2;
		int y = this.getHeight() / 2 - diameter / 2;

		int axisX = x + diameter / 2;
		int axisY = y + diameter / 2;
		
		g2.setStroke(new BasicStroke(0.5f));
		
		if(app.getOscillators() != null){
			List<Oscillator> oscillators = app.getOscillators();
			
			double angle = 0;
			
			Map<Integer,Double> angles = new HashMap<Integer,Double>();
			List<Edge> edges = new ArrayList<Edge>();
			
			for (Oscillator i : oscillators) {
				angles.put(i.getId(),angle);
				
				angle += (Math.PI * 2) / oscillators.size();
			}
			
			g2.setColor(Color.BLACK);
			
			for (Oscillator i : oscillators) {	
				double aI = angles.get(i.getId());
				
				for (Oscillator j : i.getNeighbors()) {
					boolean add = true;
					
					for (Edge e : edges) {
						if((e.getI().getId() == i.getId() && 
							e.getJ().getId() == j.getId()) ||
						   (e.getJ().getId() == i.getId() && 
						    e.getI().getId() == j.getId())){
							add = false;
							break;
						}
					}
					
					if(add){
						double aJ = angles.get(j.getId());
						
						int x1 = axisX + (int) (Math.sin(aI) * diameter / 2);
						int y1 = axisY + (int) (Math.cos(aI) * diameter / 2);
						
						int x2 = axisX + (int) (Math.sin(aJ) * diameter / 2);
						int y2 = axisY + (int) (Math.cos(aJ) * diameter / 2);
						
						g2.setStroke(new BasicStroke(0.3f));
						g.drawLine(x1,y1,x2,y2);
						
						edges.add(new Edge(i,j));
					}
				}
			}
			
			for (Oscillator i : oscillators) {
				angle = angles.get(i.getId());
				
				x = axisX + (int) (Math.sin(angle) * diameter / 2);
				y = axisY + (int) (Math.cos(angle) * diameter / 2);
		        
				g.setColor(i.getColor());
				g.fillOval(x - 4,y - 4,10,10);
			}
		}
	}
	
	private class Edge {
		private Oscillator i = null;
		private Oscillator j = null;
		
		public Edge(Oscillator i,Oscillator j){
			this.i = i;
			this.j = j;
		}
		
		public Oscillator getI(){
			return i;
		}
		
		public Oscillator getJ(){
			return j;
		}
	}
}
