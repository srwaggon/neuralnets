package neural;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Network {

  private List<Neuron> inputNodes;
  private List<Neuron> hiddenNodes;
  private List<Neuron> outputNodes;

  Network(int inputNodeCount, int outputNodeCount, int hiddenNodeCount) {
    inputNodes = createNodes(inputNodeCount);
    outputNodes = createNodes(outputNodeCount);
    hiddenNodes = createHiddenNodes(inputNodes, outputNodes, hiddenNodeCount);
  }

  boolean[] stimulate(int[] activations) {
    for (int i = 0; i < activations.length; i++) {
      inputNodes.get(i).setActivation(activations[i]);
    }

    hiddenNodes.forEach(Neuron::calcActivation);
    outputNodes.forEach(Neuron::calcActivation);

    return readOutput();
  }

  void learn(int[] activations, boolean[] expectedOutputs, int iterations) {

    for (int i = 0; i < iterations; i++) {
      boolean[] data = stimulate(activations);

      for (int j = 0; j < expectedOutputs.length; j++) {
        boolean learning = data[j] == expectedOutputs[j];
        outputNodes.get(j).learn(learning);
      }
    }
  }

  private boolean[] readOutput() {
    boolean[] output = new boolean[outputNodes.size()];

    for (int i = 0; i < outputNodes.size(); i++) {
      output[i] = outputNodes.get(i).isActive();
    }

    return output;
  }

  private List<Neuron> createNodes(int numNodes) {
    return IntStream.range(0, numNodes).mapToObj(i -> new Neuron()).collect(Collectors.toList());
  }

  private List<Neuron> createHiddenNodes(List<Neuron> inputs, List<Neuron> outputs, int hiddenNeuronCount) {
    List<Neuron> hiddenNeurons = createNodes(hiddenNeuronCount);

    hiddenNeurons.forEach(hidden -> {
      inputs.forEach(input -> bind(input, hidden));
      outputs.forEach(output -> bind(hidden, output));
    });

    return hiddenNeurons;
  }

  private void bind(Neuron input, Neuron output) {
    output.addInputNode(input);
    input.addOutputNode(output);
  }

}
