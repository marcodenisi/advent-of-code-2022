package aoc.md;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {

  public static List<String> readFileAsStringList(final String fileName) {
    final var path = Paths.get(fileName);
    final var output = new ArrayList<String>();
    try (var scanner = new Scanner(path)) {
      while (scanner.hasNextLine()) {
        output.add(scanner.nextLine());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return output;
  }

}
