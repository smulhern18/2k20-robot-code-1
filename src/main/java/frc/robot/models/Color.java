package frc.robot.models;

/*
 * Color Enum
 *    Enum for the color provided by the FMS to a better format
 */

public enum Color {

  // Values
  BLUE, GREEN, YELLOW, RED, CORRUPT;

  /**
   * Convert the char from FMS to enum
   * @param value FMS data
   * @return corresponding enum
   */
  public static Color getColor(char value) {
    switch (value) {
      case 'B':
        return BLUE;
      case 'G':
        return GREEN;
      case 'R':
        return RED;
      case 'Y':
        return YELLOW;
      default:
        return CORRUPT;
    }
  }
}
