package aoc.md;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AoC05 {

  private static final String FILENAME =
      "/Users/mdenisi/workspace/aoc2022/src/main/resources/input/05.txt";

  private record Instruction(int moves, int from, int to) {}

  public static void main(String[] args) throws IOException {
    /*
    [B]                     [N]     [H]
    [V]         [P] [T]     [V]     [P]
    [W]     [C] [T] [S]     [H]     [N]
    [T]     [J] [Z] [M] [N] [F]     [L]
    [Q]     [W] [N] [J] [T] [Q] [R] [B]
    [N] [B] [Q] [R] [V] [F] [D] [F] [M]
    [H] [W] [S] [J] [P] [W] [L] [P] [S]
    [D] [D] [T] [F] [G] [B] [B] [H] [Z]
     1   2   3   4   5   6   7   8   9
    */
    final var stacks = getInitialStacks();

    final var instructions =
        Stream.of(Files.readString(Paths.get(FILENAME)).split("\n"))
            .map(line -> line.split(" "))
            .map(
                line ->
                    new Instruction(
                        Integer.parseInt(line[1]),
                        Integer.parseInt(line[3]),
                        Integer.parseInt(line[5])))
            .toList();

    instructions.forEach(
        instruction -> {
          for (int i = 0; i < instruction.moves; i++) {
            final var fromStack = stacks.get(instruction.from - 1);
            stacks.get(instruction.to - 1).add(fromStack.remove(fromStack.size() - 1));
          }
        });

    System.out.println(
        stacks.stream().map(stack -> stack.get(stack.size() - 1)).collect(Collectors.joining()));

    final var stacksP2 = getInitialStacks();

    instructions.forEach(
        instruction -> {
          final var fromStack = stacksP2.get(instruction.from - 1);

          final var sublist =
              fromStack.subList(fromStack.size() - instruction.moves, fromStack.size());

          stacksP2.get(instruction.to - 1).addAll(sublist);

          for (int i = 0; i < instruction.moves; i++) {
            fromStack.remove(fromStack.size() - 1);
          }
        });

    System.out.println(
        stacksP2.stream().map(stack -> stack.get(stack.size() - 1)).collect(Collectors.joining()));
  }

  private static List<List<String>> getInitialStacks() {
    return List.of(
        new ArrayList<String>(Arrays.asList("D", "H", "N", "Q", "T", "W", "V", "B")),
        new ArrayList<String>(Arrays.asList("D", "W", "B")),
        new ArrayList<String>(Arrays.asList("T", "S", "Q", "W", "J", "C")),
        new ArrayList<String>(Arrays.asList("F", "J", "R", "N", "Z", "T", "P")),
        new ArrayList<String>(Arrays.asList("G", "P", "V", "J", "M", "S", "T")),
        new ArrayList<String>(Arrays.asList("B", "W", "F", "T", "N")),
        new ArrayList<String>(Arrays.asList("B", "L", "D", "Q", "F", "H", "V", "N")),
        new ArrayList<String>(Arrays.asList("H", "P", "F", "R")),
        new ArrayList<String>(Arrays.asList("Z", "S", "M", "B", "L", "N", "P", "H")));
  }
}
