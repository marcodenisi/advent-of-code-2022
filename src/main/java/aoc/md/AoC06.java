package aoc.md;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class AoC06 {

  private static final String FILENAME =
      "/Users/mdenisi/workspace/aoc2022/src/main/resources/input/06.txt";

  // change this to 14 to solve the second part
  private static final int RANGE = 4;

  public static void main(String[] args) throws IOException {
    final var input = Files.readString(Paths.get(FILENAME));

    final var map = new HashMap<Character, Integer>();
    var i = 0;
    var j = 0;
    while (j < RANGE) {
      map.put(input.charAt(j), map.getOrDefault(input.charAt(j), 0) + 1);
      j++;
    }

    for (; j < input.length(); j++, i++) {
      if (map.keySet().size() == RANGE) {
        System.out.println("SOLUTION: " + (i + RANGE));
        return;
      }

      final var current = input.charAt(j);
      final var toRemove = input.charAt(i);

      map.put(toRemove, map.get(toRemove) - 1);
      if (map.get(toRemove) == 0) {
        map.remove(toRemove);
      }

      map.put(current, map.getOrDefault(current, 0) + 1);
    }
  }
}
