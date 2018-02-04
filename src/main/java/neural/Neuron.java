package neural;

import java.util.ArrayList;
import java.util.List;

class Neuron {

  private static final double LEARNING_RATE = 0.1;

  private List<Neuron> inputNeurons = new ArrayList<>();
  private List<Neuron> outputNeurons = new ArrayList<>(); // TODO: Resolve inaccessed variable
  private List<Double> inputWeights = new ArrayList<>();

  private double activation;

  private static double sigmoid(double activation) {
    return 1.0 / (1 + Math.pow(Math.E, (-1.0 *  activation)));
  }

  void calcActivation() {
    double sum = 0.0;
    for (int i = 0; i < inputNeurons.size(); i++) {
      Neuron inputNeuron = inputNeurons.get(i);
      Double neuronActivation = inputNeuron.getActivation();
      sum += neuronActivation * inputWeights.get(i);
    }
    this.activation = sigmoid(sum);
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
