package aoc.md;

import static org.junit.jupiter.api.Assertions.assertEquals;

import aoc.md.AoC10.Instruction;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AoC10Test {

  private AoC10 day10 = new AoC10();

  public static Stream<Arguments> provideInstructions() throws IOException {
    return Stream.of(
        Arguments.of(
            fromFileToInstructions(
                "/Users/mdenisi/workspace/aoc2022/src/main/resources/input/10_sample.txt"),
            13140),
        Arguments.of(
            fromFileToInstructions(
                "/Users/mdenisi/workspace/aoc2022/src/main/resources/input/10.txt"),
            14780));
  }

  private static Instruction[] fromFileToInstructions(final String filename) throws IOException {
    return Arrays.stream(Files.readString(Paths.get(filename)).split("\n"))
        .map(
            line -> {
              var tokens = line.split(" ");
              if (tokens[0].equals("noop")) {
                return new Instruction(1, 0);
              }
              return new Instruction(2, Integer.parseInt(tokens[1]));
            })
        .toArray(Instruction[]::new);
  }

  public static Stream<Arguments> provideInstructionsAndPrints() throws IOException {

    return Stream.of(
        Arguments.of(
            fromFileToInstructions(
                "/Users/mdenisi/workspace/aoc2022/src/main/resources/input/10_sample.txt"),
            """
            ##..##..##..##..##..##..##..##..##..##..
            ###...###...###...###...###...###...###.
            ####....####....####....####....####....
            #####.....#####.....#####.....#####.....
            ######......######......######......####
            #######.......#######.......#######.....
            """),
        Arguments.of(
            fromFileToInstructions(
                "/Users/mdenisi/workspace/aoc2022/src/main/resources/input/10.txt"),
            """
            ####.#....###..#....####..##..####.#....
            #....#....#..#.#.......#.#..#....#.#....
            ###..#....#..#.#......#..#......#..#....
            #....#....###..#.....#...#.##..#...#....
            #....#....#....#....#....#..#.#....#....
            ####.####.#....####.####..###.####.####.
            """));
  }

  @ParameterizedTest
  @MethodSource("provideInstructions")
  public void shouldCalculateStrength(final Instruction[] instructions, final int expected) {
    assertEquals(expected, day10.signalStrength(instructions));
  }

  @ParameterizedTest
  @MethodSource("provideInstructionsAndPrints")
  public void shouldPrintCrtScreen(final Instruction[] instructions, final String expected) {
    assertEquals(expected, day10.printCrtScreen(instructions));
  }
}
