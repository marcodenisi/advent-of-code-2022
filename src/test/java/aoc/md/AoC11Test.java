package aoc.md;

import static org.junit.jupiter.api.Assertions.assertEquals;

import aoc.md.AoC11.Monkey;
import java.util.ArrayDeque;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AoC11Test {

  private AoC11 day11 = new AoC11();

  public static Stream<Arguments> provideMonkeys() {
    return Stream.of(
        Arguments.of(
            List.of(
                new Monkey(
                    new ArrayDeque<>(List.of(84L, 66L, 62L, 69L, 88L, 91L, 91L)),
                    old -> old * 11,
                    val -> val % 2 == 0,
                    4,
                    7),
                new Monkey(
                    new ArrayDeque<>(List.of(98L, 50L, 76L, 99L)),
                    old -> old * old,
                    val -> val % 7 == 0,
                    3,
                    6),
                new Monkey(
                    new ArrayDeque<>(List.of(72L, 56L, 94L)),
                    old -> old + 1,
                    val -> val % 13 == 0,
                    4,
                    0),
                new Monkey(
                    new ArrayDeque<>(List.of(55L, 88L, 90L, 77L, 60L, 67L)),
                    old -> old + 2,
                    val -> val % 3 == 0,
                    6,
                    5),
                new Monkey(
                    new ArrayDeque<>(List.of(69L, 72L, 63L, 60L, 72L, 52L, 63L, 78L)),
                    old -> old * 13,
                    val -> val % 19 == 0,
                    1,
                    7),
                new Monkey(
                    new ArrayDeque<>(List.of(89L, 73L)),
                    old -> old + 5,
                    val -> val % 17 == 0,
                    2,
                    0),
                new Monkey(
                    new ArrayDeque<>(List.of(78L, 68L, 98L, 88L, 66L)),
                    old -> old + 6,
                    val -> val % 11 == 0,
                    2,
                    5),
                new Monkey(
                    new ArrayDeque<>(List.of(70L)), old -> old + 7, val -> val % 5 == 0, 1, 3)),
            new long[] {99840, 20683044837L},
            9699690),
        Arguments.of(
            List.of(
                new Monkey(
                    new ArrayDeque<>(List.of(79L, 98L)),
                    old -> old * 19,
                    val -> val % 23 == 0,
                    2,
                    3),
                new Monkey(
                    new ArrayDeque<>(List.of(54L, 65L, 75L, 74L)),
                    old -> old + 6,
                    val -> val % 19 == 0,
                    2,
                    0),
                new Monkey(
                    new ArrayDeque<>(List.of(79L, 60L, 97L)),
                    old -> old * old,
                    val -> val % 13 == 0,
                    1,
                    3),
                new Monkey(
                    new ArrayDeque<>(List.of(74L)), old -> old + 3, val -> val % 17 == 0, 0, 1)),
            new long[] {10605, 2713310158L},
            96577));
  }

  @ParameterizedTest
  @MethodSource("provideMonkeys")
  public void shouldGetMonkeyBusinessLevel(final List<Monkey> monkeys, final long[] expected) {

    assertEquals(expected[0], day11.getMonkeyBusinessLevel(monkeys, true, 0));
  }

  @ParameterizedTest
  @MethodSource("provideMonkeys")
  public void shouldGetMonkeyBusinessLevel10000Rounds(
      final List<Monkey> monkeys, final long[] expected, final int magicNumber) {
    assertEquals(expected[1], day11.getMonkeyBusinessLevel(monkeys, false, magicNumber));
  }
}
