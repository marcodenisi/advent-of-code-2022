package aoc.md;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AoC01 {

  private record Solution(int p1, int p2) {}

  private static final String FILENAME =
      "/Users/mdenisi/workspace/aoc2022/src/main/resources/input/01.txt";

  public static void main(String[] args) throws IOException {

    final var solution =
        Stream.of(Files.readString(Paths.get(FILENAME)).split("\n\n"))
            .map(elf -> Stream.of(elf.split("\n")).mapToInt(Integer::parseInt).sum())
            .sorted(Comparator.reverseOrder())
            .limit(3)
            .collect(
                Collectors.teeing(
                    Collectors.toList(),
                    Collectors.summingInt(x -> x),
                    (list, top3Sum) -> new Solution(list.get(0), top3Sum)));

    // Part 1
    System.out.println(solution.p1());

    // Part 2
    System.out.println(solution.p2());
  }
}
