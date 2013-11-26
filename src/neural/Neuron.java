package neural;

import java.util.ArrayList;
import java.util.List;

public class Neuron {

	public static final double SIGMA = 0.01;

	protected List<Neuron> inputNodes = new ArrayList<Neuron>();
	protected List<Neuron> outputNodes = new ArrayList<Neuron>();
	protected List<Double> inputWeights = new ArrayList<Double>();

	private double activation;
	private double threshold = 0.5;

	public void calcActivation() {
		activation = 0.0;
		for (int i = 0; i < inputNodes.size(); i++) {
			Neuron inputNode = inputNodes.get(i);
			if (inputNode.isActive()) {
				activation += inputWeights.get(i);
			}
		}
	}

	public void learn(boolean correct) {
		if (!correct) {
			for (int i = 0; i < inputWeights.size(); i++) {
				double weight = inputWeights.get(i);
				weight += isActive() ? SIGMA : -1 * SIGMA;
				inputWeights.set(i, weight);
			}
			
			for (Neuron inputNode : inputNodes) {
				inputNode.learn(correct);
			}
		}
	}

	public double getActivation() {
		return activation;
	}

	public void setActivation(int activation) {
		this.activation = activation;
	}

	public double getThreshold() {
		return threshold;
	}

	public void addInputNode(Neuron node) {
		inputNodes.add(node);
		inputWeights.add(Math.random());
	}

	public void addOutputNode(Neuron node) {
		outputNodes.add(node);
	}

	public boolean isActive() {
		return getActivation() >= getThreshold();
	}
}
