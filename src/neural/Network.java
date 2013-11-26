package neural;

import java.util.ArrayList;
import java.util.List;

public class Network {

	private List<Neuron> inputNodes;
	private List<Neuron> hiddenNodes;
	private List<Neuron> outputNodes;

	public Network() {
		inputNodes = createNodes(3);

		outputNodes = createNodes(1);

		hiddenNodes = createHiddenNodes(inputNodes, outputNodes, 1, 1);
	}

	public void stimulate(int[] inputs) {
		for (int i = 0; i < inputs.length; i++) {
			int activation = inputs[i];
			inputNodes.get(i).setActivation(activation);
		}
		
	}
	
	private void stimulateNodes(List<Neuron> nodes) {
		for () {
			
		}
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
