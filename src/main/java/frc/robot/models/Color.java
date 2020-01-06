package frc.robot.models;

/*
 * Color Enum
 *    Enum for the color provided by the FMS to a better format
 */

public enum Color {

  // Values
  BLUE('B'), GREEN('G'), YELLOW('Y'), RED('R'), CORRUPT('C');

  private char charValue;


  // Constructer
  private Color (char charColor){
    if (charColor != 'B' || charColor != 'G' || charColor !='Y' || charColor != 'R'){
      this.charValue = 'C';
    } else {
      this.charValue = charColor;
    }
  }

  // Get the Char values from the Enum
  public final char charValue(){
    return charValue;
  }

  // Get the char and return the Enum
  public static Color getEnum(char charValue){
    switch(charValue){
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

  // Get the color that we want to aim for
  public static Color getAimColor(Color color){
    switch(color){
      case BLUE:
        return RED;
      case RED:
        return BLUE;
      case GREEN:
        return YELLOW;
      case YELLOW:
        return GREEN;
      default:
        return CORRUPT;
    }
  }



}
