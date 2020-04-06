package kuramoto.vo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Oscillator {
	public static final double DEFAULT_FREQUENCY = 0.1;
	public static final double DEFAULT_PHASE = Math.PI / 2;
	public static final double DEFAULT_SPEED = 0.5;
	
	private static int count = 0;
	
	private double frequency = 0;
	private double phase = 0;
	
	private double initialFrequency = 0;
	
	private List<Oscillator> neighbors = null;
	
	private int id;
	private Color color;
	
	private static Random random = new Random(1);
	
	public Oscillator() {
		this(Oscillator.random.nextDouble());
	}
	
	public Oscillator(double frequency) {
		int r = (int) (Oscillator.random.nextDouble() * 205) + 50;
		int g = (int) (Oscillator.random.nextDouble() * 205) + 50;
		int b = (int) (Oscillator.random.nextDouble() * 205) + 50;
		
		this.setId(Oscillator.count++);
		this.setPhase(Oscillator.random.nextDouble() * Math.PI * 2);
		this.setFrequency(frequency);
		this.setFrequenciaInicial(frequency);
		this.setColor(new Color(r,g,b));
		
		neighbors = new ArrayList<Oscillator>();
	}
	
	public void move(double frequencia){
		this.setFrequency(frequencia);
		this.setPhase(this.getPhase() + (frequencia / 10));
	}
	
	public double synchronize(double strength){
		final var phase = this.getPhase();
		
		double phases = this.getNeighbors().stream()
				.mapToDouble(o -> Math.sin(o.getPhase() - phase))
				.sum();
		
		return this.getFrequenciaInicial() + (strength * phases);
	}
	
	public double getPhase() {
		return phase;
	}
	
	public void setPhase(double phase){
		this.phase = phase;
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}
	
	public double getFrequenciaInicial() {
		return initialFrequency;
	}

	public void setFrequenciaInicial(double frequenciaInicial) {
		this.initialFrequency = frequenciaInicial;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void addNeighbor(Oscillator neighbor){
		neighbors.add(neighbor);
	}
	
	public List<Oscillator> getNeighbors(){
		return Collections.unmodifiableList(neighbors);
	}
	
	private void normalize(List<Double> serie){
		final double sum = serie.stream()
				.reduce(0D, Double::sum);
		
		double mean = sum / serie.size();
		
		double deviation = serie.stream()
				.mapToDouble(a -> Math.pow(a - mean,2))
				.sum();
				
		deviation = Math.sqrt(deviation / (serie.size() - 1));
		
		double normalized = deviation != 0 ? 
				(double) (this.getFrequency() - mean) / deviation : 0;
		
		this.setFrequency(normalized);
		this.setFrequenciaInicial(normalized);
	}
	
	public static int[][] generateGraph(int n,double p){
		Oscillator.random = new Random(1);
		
		int[][] g = new int[n][];
		
		for (int i = 0; i < g.length; i++){
			g[i] = new int[g.length];
		}
		
		for (int i = 0; i < g.length; i++){
			for (int j = i + 1; j < g[i].length; j++) {
				double q = Oscillator.random.nextDouble();
				int c = q <= p ? 1 : 0;
				
				g[i][j] = g[j][i] = c;
			}
		}
		
		return g;
	}
	
	public static List<Oscillator> createOscillators(int[][] graph,boolean normalize){
		List<Oscillator> oscillators = new ArrayList<Oscillator>();
		List<Double> serie = new ArrayList<Double>();
		
		for(int i = 0; i < graph.length; i++) {
			Oscillator oscillator = new Oscillator();
			
			serie.add(oscillator.getFrequency());
			oscillators.add(oscillator);
		}
		
		for(int i = 0; i < graph.length; i++) {
			for (int j = i + 1; j < graph[i].length; j++) {
				if(graph[i][j] == 1){
					Oscillator x = oscillators.get(i);
					Oscillator y = oscillators.get(j);
				
					x.addNeighbor(y);
					y.addNeighbor(x);
				}
			}
		}
		
		if (normalize) {
			oscillators.forEach(o -> o.normalize(serie));
		}
		
		return oscillators;
	}
	
	public static double getOrderParameter(List<Oscillator> oscillators){
		ComplexNumber complex = new ComplexNumber();
		
		complex = oscillators.stream()
				.map(o -> new ComplexNumber(1, o.getPhase()))
				.reduce(new ComplexNumber(), ComplexNumber::sum);
		
		return complex.getR() / oscillators.size();
	}
}

class ComplexNumber {
	private double r = 0;
	private double phase = 0;
	
	ComplexNumber(){
		r = 0;
		phase = 0;
	}
	
	ComplexNumber(double r,double phase){
		this.r = r;
		this.phase = phase;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public double setPhase() {
		return phase;
	}

	public void setPhase(double fase) {
		this.phase = fase;
	}

	public double cartesianX() {
        return r * Math.cos(phase);
    }

    public double cartesianY() {
        return r * Math.sin(phase);
    }

    public void setCartesian(double x, double y) {
        r = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
        phase = Math.atan2(y,x);
    }
    
    public static ComplexNumber sum(ComplexNumber c1, ComplexNumber c2) {
        ComplexNumber result = new ComplexNumber();
        
        result.setCartesian(
        		c1.cartesianX() + c2.cartesianX(),
        		c1.cartesianY() + c2.cartesianY());
        
        return result;
    }
}

