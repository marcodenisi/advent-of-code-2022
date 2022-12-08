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
  private static final Long TOTAL_DISK = 70000000L;
  private static final Long UNUSUED_SPACE = 30000000L;

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

    System.out.println(
        folderSizeMap.values().stream()
            .filter(size -> size <= 100000L)
            .mapToLong(size -> size)
            .sum());

    final var currentSpace = folderSizeMap.get("/");
    final var neededFreeSpace = UNUSUED_SPACE - (TOTAL_DISK - currentSpace);

    System.out.println(
        folderSizeMap.values().stream()
            .filter(size -> size >= neededFreeSpace)
            .sorted()
            .findFirst()
            .get());
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
        default -> folderStack.push(createFolderPath(folderStack, folder));
      }
    }
  }

  private static String createFolderPath(ArrayDeque<String> folderStack, String folder) {
    final var sb = new StringBuilder();
    for (String f : folderStack) {
      sb.append(f);
    }
    sb.append(folder);
    return sb.toString();
  }
}
