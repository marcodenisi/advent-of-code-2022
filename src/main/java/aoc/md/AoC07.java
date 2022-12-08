package aoc.md;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.stream.Stream;

public class AoC07 {

  private static final String FILENAME =
      "/Users/mdenisi/workspace/aoc2022/src/main/resources/input/07.txt";

  public static void main(String[] args) throws IOException {
    final var input = Files.readString(Paths.get(FILENAME));

    // keep track of current directory
    final var folderStack = new ArrayDeque<String>();
    // keep track of the size of the directories
    final var folderSizeMap = new HashMap<String, Long>();

    Stream.of(input.split("\n"))
        .forEach(
            instruction -> {
              final var firstToken = instruction.split(" ")[0];
              switch (firstToken) {
                case "$":
                  // handle command
                  handleCommand(instruction, folderStack);
                  break;
                case "dir":
                  // ignore
                  break;
                default:
                  // handle file
                  handleFile(instruction, folderStack, folderSizeMap);
              }
            });

    folderSizeMap.entrySet().forEach(System.out::println);

    System.out.println(
        folderSizeMap.values().stream()
            .filter(size -> size <= 100000L)
            .mapToLong(size -> size)
            .sum());
  }

  private static void handleFile(
      final String instruction,
      final ArrayDeque<String> folderStack,
      final HashMap<String, Long> folderSizeMap) {
    final var instructionTokens = instruction.split(" ");
    final var size = Long.parseLong(instructionTokens[0]);

    for (String currentFolder : folderStack) {
      folderSizeMap.put(currentFolder, folderSizeMap.getOrDefault(currentFolder, 0L) + size);
    }
  }

  private static void handleCommand(
      final String instruction, final ArrayDeque<String> folderStack) {
    final var instructionTokens = instruction.split(" ");
    final var command = instructionTokens[1];
    if ("cd".equals(command)) {
      final var folder = instructionTokens[2];
      switch (folder) {
        case "/" -> {
          // empty stack; push '/'
          while (!folderStack.isEmpty()) {
            folderStack.pop();
          }
          folderStack.push(folder);
        }
        case ".." ->
        // pop
        folderStack.pop();
        default -> folderStack.push(folder);
      }
    }
  }
}
