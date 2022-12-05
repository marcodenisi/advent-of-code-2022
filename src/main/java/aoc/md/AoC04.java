package aoc.md;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class AoC04 {

  private static final String FILENAME =
      "/Users/mdenisi/workspace/aoc2022/src/main/resources/input/04.txt";

  private record Pair(String first, String second) {}

  public static void main(String[] args) throws IOException {
    final var resultP1 =
        Stream.of(Files.readString(Paths.get(FILENAME)).split("\n"))
            .map(line -> line.split(","))
            .filter(
                elves ->
                    AoC04.fullyContain(elves[0], elves[1])
                        || AoC04.fullyContain(elves[1], elves[0]))
            .count();

    System.out.println(resultP1);

    final var resultP2 =
        Stream.of(Files.readString(Paths.get(FILENAME)).split("\n"))
            .map(line -> line.split(","))
            .filter(
                elves ->
                    AoC04.overlap(elves[0], elves[1])
                        || AoC04.overlap(elves[1], elves[0]))
            .count();

    System.out.println(resultP2);

  }

  private static boolean fullyContain(final String s1, final String s2) {
    final var a1 = s1.split("-");
    final var a2 = s2.split("-");

    return Integer.parseInt(a1[0]) >= Integer.parseInt(a2[0])
        && Integer.parseInt(a1[1]) <= Integer.parseInt(a2[1]);
  }

  private static boolean overlap(final String s1, final String s2) {
    final var a1 = s1.split("-");
    final var a2 = s2.split("-");

    final var start1 = Integer.parseInt(a1[0]);
    final var start2 = Integer.parseInt(a2[0]);
    final var end1 = Integer.parseInt(a1[1]);
    final var end2 = Integer.parseInt(a2[1]);

    final var maxStart = Integer.max(start1, start2);
    final var minEnd = Integer.min(end1, end2);

    return minEnd >= maxStart;
  }
}
