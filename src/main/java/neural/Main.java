package neural;

public class Main {

  public static void main(String[] args) {
    Network network = new Network(4, 1, 100);

    System.out.println("Learning...");
    int lessonsCount = 10000;
    for (int i = 0; i < lessonsCount; i++) {
      System.out.println(String.format("%d/%d: %.00f", i, lessonsCount, 1.0 * i / lessonsCount));
      network.learn(intToActivations(i), intToOutput(i), 100);
    }

    int hits = 0;
    int misses = 0;
    System.out.println("Stimulating...");
    int stimulationCount = 1000;
    for (int i = 0; i < stimulationCount; i++) {
      System.out.println(String.format("%d/%d: %.00f", i, stimulationCount, 1.0 * i / 1000.0));
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
