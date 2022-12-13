package aoc.md;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AoC09 {

  private static final String FILENAME =
      "/Users/mdenisi/workspace/aoc2022/src/main/resources/input/09.txt";

  public record Instruction(String direction, int steps) {}

  public static class Position {

    int x;
    int y;

    public Position(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  public static void main(String[] args) throws IOException {
    final var input = Files.readString(Paths.get(FILENAME)).split("\n");

    final var instructions =
        Arrays.stream(input)
            .map(line -> line.split(" "))
            .map(tokens -> new Instruction(tokens[0], Integer.parseInt(tokens[1])))
            .toList();

    final var solution = new AoC09();
    var positions = new Position[] {new Position(0, 0), new Position(0, 0)};
    System.out.println(solution.countTailPositions(instructions, positions));

    positions =
        new Position[] {
          new Position(0, 0),
          new Position(0, 0),
          new Position(0, 0),
          new Position(0, 0),
          new Position(0, 0),
          new Position(0, 0),
          new Position(0, 0),
          new Position(0, 0),
          new Position(0, 0),
          new Position(0, 0)
        };
    System.out.println(solution.countTailPositionsWithMultipleNodes(instructions, positions));
  }

  public int countTailPositions(final List<Instruction> instructions, final Position[] positions) {
    final var visited = new HashSet<String>();
    var headPosition = positions[0];
    var tailPosition = positions[1];
    visited.add(getKey(headPosition));

    for (Instruction instr : instructions) {
      switch (instr.direction) {
        case "R":
          for (int i = 0; i < instr.steps; i++) {
            headPosition.x++;
            if (!areAdjecent(headPosition, tailPosition)) {
              tailPosition.x = headPosition.x - 1;
              tailPosition.y = headPosition.y;
              visited.add(getKey(tailPosition));
            }
          }
          break;
        case "L":
          for (int i = 0; i < instr.steps; i++) {
            headPosition.x--;
            if (!areAdjecent(headPosition, tailPosition)) {
              tailPosition.x = headPosition.x + 1;
              tailPosition.y = headPosition.y;
              visited.add(getKey(tailPosition));
            }
          }
          break;
        case "U":
          for (int i = 0; i < instr.steps; i++) {
            headPosition.y++;
            if (!areAdjecent(headPosition, tailPosition)) {
              tailPosition.x = headPosition.x;
              tailPosition.y = headPosition.y - 1;
              visited.add(getKey(tailPosition));
            }
          }
          break;
        case "D":
          for (int i = 0; i < instr.steps; i++) {
            headPosition.y--;
            if (!areAdjecent(headPosition, tailPosition)) {
              tailPosition.x = headPosition.x;
              tailPosition.y = headPosition.y + 1;
              visited.add(getKey(tailPosition));
            }
          }
      }
    }

    return visited.size();
  }

  public int countTailPositionsWithMultipleNodes(
      final List<Instruction> instructions, final Position[] positions) {
    final var visited = new HashSet<String>();
    final var head = positions[0];
    visited.add(getKey(head));

    for (Instruction instr : instructions) {
      switch (instr.direction) {
        case "R":
          for (int i = 0; i < instr.steps; i++) {
            head.x++;
            moveNodes(positions, visited);
          }
          break;
        case "L":
          for (int i = 0; i < instr.steps; i++) {
            head.x--;
            moveNodes(positions, visited);
          }
          break;
        case "U":
          for (int i = 0; i < instr.steps; i++) {
            head.y++;
            moveNodes(positions, visited);
          }
          break;
        case "D":
          for (int i = 0; i < instr.steps; i++) {
            head.y--;
            moveNodes(positions, visited);
          }
      }
    }

    return visited.size();
  }

  private void moveNodes(final Position[] positions, final Set<String> visited) {
    for (int j = 1; j < positions.length; j++) {
      final var prevNode = positions[j - 1];
      final var currentNode = positions[j];
      if (!areAdjecent(prevNode, currentNode)) {
        var dX = prevNode.x - currentNode.x;
        var dY = prevNode.y - currentNode.y;
        currentNode.x += sign(dX);
        currentNode.y += sign(dY);
        if (j == positions.length - 1) visited.add(getKey(currentNode));
      }
    }
  }

  public boolean areAdjecent(final Position head, final Position tail) {
    return Math.abs(head.x - tail.x) <= 1 && Math.abs(head.y - tail.y) <= 1;
  }

  private String getKey(final Position position) {
    return position.x + "-" + position.y;
  }

  private int sign(int d) {
    if (d == 0) {
      return 0;
    }

    return d > 0 ? 1 : -1;
  }
}
