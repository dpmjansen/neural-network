package net.thisisdave;


import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Driver {

  public static void main(String[] args) {
    int[][][] data = Perceptron.andData;
    double[] weights = Perceptron.INITIAL_WEIGHTS;
    Driver driver = new Driver();
    Perceptron perceptron = new Perceptron();

    int epochNumber = 0;
    boolean errorflag = true;
    double error = 0;
    double[] adjustedWeights = null;

    while (errorflag) {
      driver.printHeading(epochNumber++);
      errorflag = false;
      error = 0;
      for (int i = 0; i < data.length; i++) {
        double weightedSum = perceptron.calculateWeightedSum(data[i][0], weights);
        int result = perceptron.applyActivationFunction(weightedSum);
        error = data[i][1][0] - result;
        if (error != 0) errorflag = true;
        adjustedWeights = perceptron.adjustWeights(data[i][0], weights, error);
        driver.printVector(data[i], weights, result, error, weightedSum, adjustedWeights);
        weights = adjustedWeights;
      }
    }
  }

  public String getRowSeperator() {
    return Stream.generate(() -> "-").limit(40).collect(Collectors.joining());
  }

  public void printHeading(int epochNumber) {
    System.out.println("\n ===Epoch # " + epochNumber);
    System.out.println(String.format("%s | %s | %s | %s | %s | %s | %s | %s | %s | %s", "w1", "w2", "x1", "x2", "Target Result", "Result", "error", "Weighted Sum", "adjusted w1", "adjusted w2"));
    System.out.println(getRowSeperator());
  }

  public void printVector(int[][] data, double[] weights, int result, double error, double weightedSum, double[] adjustedWeights) {
    System.out.println("  " + String.format("%.2f | %.2f | %d | %d | %d | %d | %f | %f | %.2f | %.2f",
      weights[0],
      weights[1],
      data[0][0],
      data[0][1],
      data[1][0],
      result,
      error,
      weightedSum,
      adjustedWeights[0],
      adjustedWeights[1]));
  }


}
