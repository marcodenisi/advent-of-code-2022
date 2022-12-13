package aoc.md;

import static org.junit.jupiter.api.Assertions.assertEquals;

import aoc.md.AoC09.Instruction;
import aoc.md.AoC09.Position;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class AoC09Test {

  private AoC09 day9 = new AoC09();

  @ParameterizedTest
  @MethodSource("provideInputCoords")
  public void shouldCalculateAdjacency(Position head, Position tail, boolean expected) {
    assertEquals(expected, day9.areAdjecent(head, tail));
  }

  @Test
  public void shouldCountPositions() {
    final var instructions =
        List.of(
            new Instruction("R", 4),
            new Instruction("U", 4),
            new Instruction("L", 3),
            new Instruction("D", 1),
            new Instruction("R", 4),
            new Instruction("D", 1),
            new Instruction("L", 5),
            new Instruction("R", 2));
    final var positions = new Position[] {new Position(0, 0), new Position(0, 0)};
    assertEquals(day9.countTailPositionsWithMultipleNodes(instructions, positions), 13);
  }

  @Test
  public void shouldCountPositionsWithMultipleNodes() {
    final var instructions =
        List.of(
            new Instruction("R", 5),
            new Instruction("U", 8),
            new Instruction("L", 8),
            new Instruction("D", 3),
            new Instruction("R", 17),
            new Instruction("D", 10),
            new Instruction("L", 25),
            new Instruction("U", 20));
    final var positions =
        new Position[] {
          new Position(0, 0),
          new Position(0, 0),
          new Position(0, 0),
          new Position(0, 0),
          new Position(0, 0),
          new Position(0, 0),
          new Position(0, 0),
          new Position(0, 0),
          new Position(0, 0),
          new Position(0, 0)
        };
    assertEquals(36, day9.countTailPositionsWithMultipleNodes(instructions, positions));
  }

  public static Stream<Arguments> provideInputCoords() {
    return Stream.of(
        Arguments.of(new Position(0, 0), new Position(0, 0), true),
        Arguments.of(new Position(0, 0), new Position(1, 0), true),
        Arguments.of(new Position(0, 0), new Position(0, 1), true),
        Arguments.of(new Position(0, 0), new Position(0, -1), true),
        Arguments.of(new Position(0, 0), new Position(-1, 0), true),
        Arguments.of(new Position(0, 0), new Position(-1, -1), true),
        Arguments.of(new Position(0, 0), new Position(-1, 1), true),
        Arguments.of(new Position(0, 0), new Position(1, 1), true),
        Arguments.of(new Position(0, 0), new Position(1, -1), true),
        Arguments.of(new Position(0, 0), new Position(1, 2), false),
        Arguments.of(new Position(0, 0), new Position(-1, 2), false),
        Arguments.of(new Position(0, 0), new Position(1, -2), false),
        Arguments.of(new Position(0, 0), new Position(2, 1), false),
        Arguments.of(new Position(0, 0), new Position(5, 4), false));
  }
}
