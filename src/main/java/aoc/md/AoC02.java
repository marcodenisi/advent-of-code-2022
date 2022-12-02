package aoc.md;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AoC02 {

  private static final String FILENAME =
      "/Users/mdenisi/workspace/aoc2022/src/main/resources/input/02.txt";

  private static final Map<String, Integer> SCORES =
      Map.of(
          "A Y", 8,
          "A Z", 3,
          "A X", 4,
          "B X", 1,
          "B Z", 9,
          "B Y", 5,
          "C X", 7,
          "C Z", 6,
          "C Y", 2);

  private static final Map<String, Integer> SCORES_P2 =
      Map.of(
          "A X", 3,
          "A Z", 8,
          "A Y", 4,
          "B X", 1,
          "B Z", 9,
          "B Y", 5,
          "C X", 2,
          "C Y", 6,
          "C Z", 7);

  private record Scores(int p1, int p2) {}

  public static void main(String[] args) throws IOException {

    final var scores =
        Stream.of(Files.readString(Paths.get(FILENAME)).split("\n"))
            .collect(
                Collectors.teeing(
                    Collectors.summingInt(SCORES::get),
                    Collectors.summingInt(SCORES_P2::get),
                    Scores::new));

    System.out.println(scores.p1);
    System.out.println(scores.p2);
  }
}
