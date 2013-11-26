package neural;

import java.util.ArrayList;
import java.util.List;

public class Network {

	private List<Neuron> inputNodes;
	private List<Neuron> hiddenNodes;
	private List<Neuron> outputNodes;

	public Network(int numInput, int numOuput, int numHidden, int numLayers) {
		inputNodes = createNodes(numInput);

		outputNodes = createNodes(numOuput);

		hiddenNodes = createHiddenNodes(inputNodes, outputNodes, numHidden, numLayers);
	}

	private boolean[] stimulate(int[] inputs) {
		for (int i = 0; i < inputs.length; i++) {
			int activation = inputs[i];
			inputNodes.get(i).setActivation(activation);
		}

		stimulateNodes(hiddenNodes);
		stimulateNodes(outputNodes);

		return readOutput();
	}

	public void learn(int[] inputs, boolean[] expectedOutputs, int iterations) {

		for (int iter = 0; iter < iterations; iter++) {
			boolean[] data = stimulate(inputs);

			for (int j = 0; j < expectedOutputs.length; j++) {
				outputNodes.get(j).learn(data[j] == expectedOutputs[j]);
			}
		}
	}

	private void stimulateNodes(List<Neuron> nodes) {
		for (Neuron node : nodes) {
			node.calcActivation();
		}
	}

	private boolean[] readOutput() {
		int size = outputNodes.size();
		boolean[] output = new boolean[size];

		for (int i = 0; i < size; i++) {
			output[i] = outputNodes.get(i).isActive();
		}

		return output;
	}

	private List<Neuron> createNodes(int numNodes) {
		List<Neuron> nodes = new ArrayList<Neuron>();

		for (int i = 0; i < numNodes; i++) {
			nodes.add(new Neuron());
		}
		return nodes;
	}

	private List<Neuron> createHiddenNodes(List<Neuron> inputNodes,
			List<Neuron> outputNodes, int numNodes, int numLayers) {
		List<Neuron> hiddenNodes = createNodes(numNodes);

		for (Neuron node : hiddenNodes) {
			for (Neuron input : inputNodes) {
				bind(input, node);
			}

			for (Neuron output : outputNodes) {
				bind(node, output);
			}
		}

		return hiddenNodes;
	}

	private void bind(Neuron input, Neuron ouptut) {
		ouptut.addInputNode(input);
		input.addOutputNode(ouptut);
	}

}
