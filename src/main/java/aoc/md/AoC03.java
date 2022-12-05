package aoc.md;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AoC03 {

  private static final String FILENAME =
      "/Users/mdenisi/workspace/aoc2022/src/main/resources/input/03.txt";

  public static void main(String[] args) throws IOException {

    final var lines = Stream.of(Files.readString(Paths.get(FILENAME)).split("\n")).toList();

    final var result =
        lines.stream().map(AoC03::part1).filter(Objects::nonNull).mapToInt(AoC03::toInt).sum();

    System.out.println(result);

    final var resultP2 =
        IntStream.range(0, lines.size() / 3)
            .map(i -> i * 3)
            .mapToObj(i -> lines.subList(i, i + 3))
            .map(AoC03::part2)
            .mapToInt(AoC03::toInt)
            .sum();

    System.out.println(resultP2);
  }

  private static Character part1(final String line) {
    final var set = new HashSet<Character>();
    final var array = line.toCharArray();

    for (int i = 0; i < array.length / 2; i++) {
      set.add(array[i]);
    }
    for (int i = array.length / 2; i < array.length; i++) {
      if (set.contains(array[i])) {
        return array[i];
      }
    }

    return null;
  }

  private static Character part2(final List<String> sublist) {
    Set<Character> s1 = asCharacterSet(sublist.get(0));
    Set<Character> s2 = asCharacterSet(sublist.get(1));
    Set<Character> s3 = asCharacterSet(sublist.get(2));

    s2.retainAll(s1);
    s3.retainAll(s2);
    return s3.iterator().next();
  }

  private static int toInt(final Character c) {
    if (Character.isUpperCase(c)) return c - 'A' + 27;
    return c - 'a' + 1;
  }

  private static Set<Character> asCharacterSet(String value) {
    final var t1 = new TreeSet<Character>();
    for (char c1 : value.toCharArray()) {
      t1.add(c1);
    }
    return t1;
  }
}
