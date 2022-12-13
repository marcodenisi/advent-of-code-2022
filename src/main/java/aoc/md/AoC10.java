package aoc.md;

public class AoC10 {

  public record Instruction(int duration, int value) {}

  public int signalStrength(final Instruction[] instructions) {
    var x = 1;
    var hit = 0;
    var signalStrength = 0;

    var currentIdx = 0;
    var current = instructions[currentIdx];
    var remainingDuration = current.duration;
    for (int i = 1; i <= 220; i++) {

      if (i == 20 + (40 * hit)) {
        signalStrength += ((20 + (40 * hit)) * x);
        hit++;
      }

      if (--remainingDuration == 0) {
        x += current.value;
        current = instructions[++currentIdx];
        remainingDuration = current.duration;
      }
    }

    return signalStrength;
  }

  public String printCrtScreen(final Instruction[] instructions) {
    var x = 1;

    var sb = new StringBuilder();

    var line = 0;
    var currentIdx = 0;
    var current = instructions[currentIdx];
    var remainingDuration = current.duration;
    for (int i = 0; i < 240; i++) {
      var pos = i + 1 - line * 40;

      if (pos >= x && pos < x + 3) {
        sb.append("#");
      } else {
        sb.append(".");
      }
      if (pos % 40 == 0) {
        sb.append("\n");
        line++;
      }

      if (--remainingDuration == 0) {
        x += current.value;
        if (++currentIdx < instructions.length) {
          current = instructions[currentIdx];
          remainingDuration = current.duration;
        }
      }
    }

    return sb.toString();
  }
}
