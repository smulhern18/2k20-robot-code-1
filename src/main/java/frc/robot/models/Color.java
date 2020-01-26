package frc.robot.models;

/*
 * Color Enum
 *    Enum for the color provided by the FMS to a better format
 */

public enum Color {

  // Values
  BLUE("blue"), GREEN("green"), YELLOW("yellow"), RED("green"), CORRUPT("black");
  public String value;
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

  Color(String s) {
    value = s;
  }
}
