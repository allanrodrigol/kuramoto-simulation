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

public class ComplexPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private OscillatorApp app = null;
	private JFreeChart chart = null;
	
	private XYSeriesCollection dataset = null; 
	
	private int counter = 0;
	
	public ComplexPanel(OscillatorApp oscilador){
		super(new BorderLayout());
		
		this.app = oscilador;
		
		this.add(new ChartPanel(this.getChart()));
		
		this.setPreferredSize(new Dimension(300,300));
	}
	
	public JFreeChart getChart(){
		if(chart == null){
			dataset = new XYSeriesCollection();
			
			chart = ChartFactory.createXYLineChart("Order parameter","\u03B8","r",dataset,PlotOrientation.VERTICAL,true,true,false);
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
			axis.setRange(0,1);
		}
		
		return chart; 
	}
	
	public void createSeries(){
		dataset.removeAllSeries();
		
		counter = 1;
		
		XYItemRenderer renderer = this.getChart().getXYPlot().getRenderer();  
        
		XYSeries serie = new XYSeries("r");
	    serie.add(counter,0);
			
		dataset.addSeries(serie);
			
		renderer.setSeriesPaint(0,Color.BLACK);
	}
	
	public void updateDatasets(){
		counter++;
		
		var oscillators = app.getOscillators();
		double complex = Oscillator.getOrderParameter(oscillators);
		
		XYSeries serie = dataset.getSeries(0);
		serie.add(counter,complex);
			
		if(counter > 100){
			serie.remove(0); 
		}
	}
}
