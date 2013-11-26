package neural;

import java.util.ArrayList;
import java.util.List;

public class Neuron {
	
	List<Neuron> inputNodes = new ArrayList<Neuron>();
	List<Neuron> outputNodes = new ArrayList<Neuron>();
	private double activation;
	
	
	public void calcActivation() {
		activation = 0.0;
		for(Neuron inputNode : inputNodes) {
			if (inputNode.isActive()) {
				activation += 1.0; 
			}
		}
	}
	
	public double getActivation() {
		return activation;
	}
	
	public void setActivation(int activation) {
		this.activation = activation;
	}
	
	public double getThreshhold() {
		return 0;
	}
	
	public void addInputNode(Neuron node) {
		inputNodes.add(node);
	}
	
	public void addOutputNode(Neuron node) {
		outputNodes.add(node);
	}
	
	public boolean isActive() {
		return getActivation() >= getThreshhold(); 
	}
}
