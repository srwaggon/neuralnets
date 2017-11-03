package neural;

import java.util.ArrayList;
import java.util.List;

class Neuron {

  private static final double LEARNING_RATE = 0.1;

  private List<Neuron> inputNeurons = new ArrayList<>();
  private List<Neuron> outputNeurons = new ArrayList<>();
  private List<Double> inputWeights = new ArrayList<>();

  private double activation;

  void calcActivation() {
    this.activation = inputNeurons.stream().map(Neuron::getActivation).mapToDouble(Double::doubleValue).sum();
  }

  void learn(boolean isCorrect) {
    if (isCorrect) {
      return;
    }

    for (int i = 0; i < inputWeights.size(); i++) {
      double newWeight = inputWeights.get(i) + (isActive() ? LEARNING_RATE : -1 * LEARNING_RATE);
      inputWeights.set(i, newWeight);
    }

    inputNeurons.forEach(input -> input.learn(false));
  }

  private double getActivation() {
    return activation;
  }

  void setActivation(int activation) {
    this.activation = activation;
  }

  private double getThreshold() {
    return 0.5;
  }

  void addInputNode(Neuron node) {
    inputNeurons.add(node);
    inputWeights.add(Math.random());
  }

  void addOutputNode(Neuron node) {
    outputNeurons.add(node);
  }

  boolean isActive() {
    return getActivation() >= getThreshold();
  }
}
