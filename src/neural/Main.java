package neural;

public class Main {
	
	public static void main(String[] args) {
		Network network = new Network(3, 3, 1, 0);
		
		int[] inputs = new int[]{};
		boolean[] outputs = new boolean[]{};
		int iterations = 100;
		
		network.learn(inputs, outputs, iterations);
	}

}
