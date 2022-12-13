package aoc.md;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class AoC08 {

  private static final String FILENAME =
      "/Users/mdenisi/workspace/aoc2022/src/main/resources/input/08.txt";

  public static void main(String[] args) throws IOException {
    final var solution = new AoC08();

    final var matrix =
        Stream.of(Files.readString(Paths.get(FILENAME)).split("\n"))
            .map(line -> line.split(""))
            .map(line -> Arrays.stream(line).mapToInt(Integer::parseInt).toArray())
            .toArray(int[][]::new);

    final var visiblePoints = solution.getVisiblePoints(matrix);
    System.out.println(visiblePoints.size());
    System.out.println(solution.getMaxScenicPoints(visiblePoints, matrix));
  }

  private Set<String> getVisiblePoints(final int[][] matrix) {

    var localMax = 0;
    final var visiblePoints = new HashSet<String>();

    // check from left
    for (int row = 0; row < matrix.length; row++) {
      localMax = Integer.MIN_VALUE;
      for (int col = 0; col < matrix[row].length; col++) {
        if (isVisible(localMax, col, row, matrix)) {
          final var key = row + "-" + col;
          visiblePoints.add(key);
          localMax = matrix[row][col];
        }
      }
    }

    // check from right
    for (int row = 0; row < matrix.length; row++) {
      localMax = Integer.MIN_VALUE;
      for (int col = matrix[row].length - 1; col >= 0; col--) {
        if (isVisible(localMax, col, row, matrix)) {
          final var key = row + "-" + col;
          visiblePoints.add(key);
          localMax = matrix[row][col];
        }
      }
    }

    // check from bottom
    for (int col = 0; col < matrix.length; col++) {
      localMax = Integer.MIN_VALUE;
      for (int row = matrix[col].length - 1; row >= 0; row--) {
        if (isVisible(localMax, col, row, matrix)) {
          final var key = row + "-" + col;
          visiblePoints.add(key);
          localMax = matrix[row][col];
        }
      }
    }

    // check from top
    for (int col = 0; col < matrix.length; col++) {
      localMax = Integer.MIN_VALUE;
      for (int row = 0; row < matrix[col].length; row++) {
        if (isVisible(localMax, col, row, matrix)) {
          final var key = row + "-" + col;
          visiblePoints.add(key);
          localMax = matrix[row][col];
        }
      }
    }

    return visiblePoints;
  }

  private boolean isVisible(
      final int localMax, final int col, final int row, final int[][] matrix) {
    return row == matrix[col].length - 1 || localMax < matrix[row][col];
  }

  private int getMaxScenicPoints(final Set<String> visiblePoints, final int[][] matrix) {
    var max = Integer.MIN_VALUE;

    for (String point : visiblePoints) {

      int[] coords = Arrays.stream(point.split("-")).mapToInt(Integer::parseInt).toArray();
      int row = coords[0];
      int col = coords[1];
      int currentVal = matrix[row][col];

      var down = 0;
      var i = row + 1;
      if (row != matrix.length - 1) {
        while (i < matrix.length - 1 && currentVal > matrix[i][col]) {
          down++;
          i++;
        }
        down++;
      }

      var up = 0;
      i = row - 1;
      if (row != 0) {
        while (i > 0 && currentVal > matrix[i][col]) {
          up++;
          i--;
        }
        up++;
      }

      var left = 0;
      i = col - 1;
      if (col != 0) {
        while (i > 0 && currentVal > matrix[row][i]) {
          left++;
          i--;
        }
        left++;
      }

      var right = 0;
      i = col + 1;
      if (col != matrix[row].length - 1) {
        while (i < matrix[row].length - 1 && currentVal > matrix[row][i]) {
          right++;
          i++;
        }
        right++;
      }

      max = Math.max(max, up * down * left * right);
    }

    return max;
  }
}
