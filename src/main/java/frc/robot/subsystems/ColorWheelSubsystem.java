package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Constants.ColorWheelConstants;

/**
 * The color wheel subsystem
 * <p>
 * input: color sensor
 * <p>
 * output: one motor for spinning
 */
public class ColorWheelSubsystem extends BeefSubsystemBase {

  private ColorSensorV3 colorSensor;
  private ColorMatch colorMatcher;
  private Color detectedColor;
  private ColorMatchResult match;
  private String colorString;
  private double confidence;

  /**
   * Creates a new ColorWheelSubsystem.
   */
  public ColorWheelSubsystem() {
    colorSensor = new ColorSensorV3(ColorWheelConstants.COLOR_SENSOR_PORT);
    colorMatcher = new ColorMatch();
    confidence = 0;
    colorString = "null";
    colorMatcher.addColorMatch(ColorWheelConstants.BLUE_TARGET);
    colorMatcher.addColorMatch(ColorWheelConstants.GREEN_TARGET);
    colorMatcher.addColorMatch(ColorWheelConstants.RED_TARGET);
    colorMatcher.addColorMatch(ColorWheelConstants.YELLOW_TARGET);
    createStringEntry("Detected Color", 4, 2, 1, 1, () -> colorString);
    createDoubleEntry("Color Confidence", 4, 3, 1, 1, () -> confidence);
  }

  /**
   * Detects Color and checks it with the confidence threshold to determine whether to actually use
   * @return the color string based on the detected color
   */
  public String detectColor() {
    detectedColor = colorSensor.getColor();

    match = colorMatcher.matchClosestColor(detectedColor);

    if (ColorWheelConstants.BLUE_TARGET.equals(match.color)) {
      colorString = "Blue";
    } else if (ColorWheelConstants.GREEN_TARGET.equals(match.color)) {
      colorString = "Green";
    } else if (ColorWheelConstants.RED_TARGET.equals(match.color)) {
      colorString = "Red";
    } else if (ColorWheelConstants.YELLOW_TARGET.equals(match.color)) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    if (match.confidence >= ColorWheelConstants.CONFIDENCE_THRESHOLD) {
      return colorString;
    }
    return ColorWheelConstants.UNKNOWN;
  }

}

