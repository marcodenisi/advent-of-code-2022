package aoc.md;

import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class AoC11 {

  static class Monkey {
    Deque<Long> items;
    Function<Long, Long> operation;
    Predicate<Long> test;
    int targetMonkeyTrue;
    int targetMonkeyFalse;

    public Monkey(
        Deque<Long> items,
        Function<Long, Long> operation,
        Predicate<Long> test,
        int targetMonkeyTrue,
        int targetMonkeyFalse) {
      this.items = items;
      this.operation = operation;
      this.test = test;
      this.targetMonkeyTrue = targetMonkeyTrue;
      this.targetMonkeyFalse = targetMonkeyFalse;
    }
  }

  public long getMonkeyBusinessLevel(
      final List<Monkey> monkeys, final boolean isFirstPart, final int magicNumber) {

    final var times = new long[monkeys.size()];
    final var rounds = isFirstPart ? 20 : 10000;

    for (int i = 0; i < rounds; i++) {

      for (int m = 0; m < monkeys.size(); m++) {
        final var monkey = monkeys.get(m);
        while (!monkey.items.isEmpty()) {
          times[m]++;
          var current = monkey.items.pollFirst();
          current = monkey.operation.apply(current);
          if (isFirstPart) {
            current = current / 3;
          } else {
            current = current % magicNumber;
          }

          if (monkey.test.test(current)) {
            monkeys.get(monkey.targetMonkeyTrue).items.addLast(current);
          } else {
            monkeys.get(monkey.targetMonkeyFalse).items.addLast(current);
          }
        }
      }
    }

    Arrays.sort(times);
    return times[times.length - 1] * times[times.length - 2];
  }
}
