package neural;

public class Main {

  public static void main(String[] args) {
    Network network = new Network(4, 1, 100);

    for (int i = 0; i < 10000; i++) {
      network.learn(intToActivations(i), intToOutput(i), 100);
    }

    int hits = 0;
    int misses = 0;
    for (int i = 0; i < 1000; i++) {
      boolean expected = intToOutput(i)[0];
      boolean actual = network.stimulate(intToActivations(i))[0];
      hits += (expected == actual ? 1 : 0);
      misses += (expected == actual ? 0 : 1);
      System.out.println(i + ", expected: " + expected + " :: " + actual);
    }
    System.out.println("Hits: " + hits);
    System.out.println("Misses: " + misses);
    System.out.println("Accuracy: " + (1.0 * hits / (hits + misses)));
  }

  private static boolean[] intToOutput(int i) {
    return new boolean[]{
        (i & (byte) 4) == 4
    };
  }

  private static int[] intToActivations(int i) {
    return new int[]{
        (i & (byte) 1),
        (i & (byte) 2) >> 1,
        (i & (byte) 4) >> 2,
        (i & (byte) 8) >> 3
    };
  }

}
