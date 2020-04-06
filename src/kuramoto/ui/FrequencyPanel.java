package kuramoto.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import kuramoto.vo.Oscillator;

public class FrequencyPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private OscillatorApp app = null;
	private JFreeChart chart = null;
	
	private XYSeriesCollection dataset = null; 
	
	private int counter = 0;
	
	public FrequencyPanel(OscillatorApp app){
		super(new BorderLayout());
		
		this.app = app;
		
		this.add(new ChartPanel(this.getChart()));
		
		this.setPreferredSize(new Dimension(300,300));
	}
	
	public JFreeChart getChart(){
		if(chart == null){
			dataset = new XYSeriesCollection();
			
			chart = ChartFactory.createXYLineChart("Frequencies","\u03B8","\u03C9",dataset,PlotOrientation.VERTICAL,true,true,false);
			chart.setBackgroundPaint(Color.WHITE);

			chart.getLegend().setVisible(false);
			chart.setAntiAlias(true);
			
			XYPlot plot = (XYPlot) chart.getPlot();  
			
			XYSplineRenderer renderer = new XYSplineRenderer();
			renderer.setBaseShapesVisible(false);

			chart.setAntiAlias(true);
			
			plot.setRenderer(renderer);
			plot.setBackgroundPaint(Color.WHITE);  
			plot.setDomainGridlinePaint(Color.WHITE);  
			plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
			
			NumberAxis axis = (NumberAxis) plot.getRangeAxis();  
			axis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
		}
		
		return chart; 
	}
	
	public void criateSeries(){
		dataset.removeAllSeries();
		
		counter = 1;
		
		XYItemRenderer renderer = this.getChart().getXYPlot().getRenderer();  
        
		int i = 0;
		
		for (Oscillator oscillator : app.getOscillators()) {
			XYSeries serie = new XYSeries(String.valueOf(i));
	        serie.add(counter,oscillator.getFrequency());
			
			dataset.addSeries(serie);
			
			renderer.setSeriesPaint(i++,oscillator.getColor());
		}
	}
	
	public void updateDatasets(){
		counter++;
		
		int i = 0;
		
		for (Oscillator oscillator : app.getOscillators()) {
			XYSeries serie = dataset.getSeries(i++);        
			serie.add(counter,oscillator.getFrequency());
			
			if(counter > 100){
				serie.remove(0);
			}
		}
	}
}
