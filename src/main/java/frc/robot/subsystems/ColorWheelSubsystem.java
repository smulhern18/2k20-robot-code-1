package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
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

  private String colorString = "null";
  private double confidence = 0;
  private ColorSensorV3 colorSensor;
  private ColorMatch colorMatcher;
  private WPI_TalonSRX colorWheelMotor;

  /**
   * Creates a new ColorWheelSubsystem.
   */
  public ColorWheelSubsystem() {
    colorSensor = new ColorSensorV3(ColorWheelConstants.COLOR_SENSOR_PORT);
    colorMatcher = new ColorMatch();
    colorMatcher.addColorMatch(ColorWheelConstants.BLUE_TARGET);
    colorMatcher.addColorMatch(ColorWheelConstants.GREEN_TARGET);
    colorMatcher.addColorMatch(ColorWheelConstants.RED_TARGET);
    colorMatcher.addColorMatch(ColorWheelConstants.YELLOW_TARGET);

    colorWheelMotor = new WPI_TalonSRX(ColorWheelConstants.COLOR_WHEEL_MOTOR_CHANNEL);

    createStringEntry("Detected Color", 4, 2, 1, 1, () -> colorString);
    createDoubleEntry("Color Confidence", 4, 3, 1, 1, () -> confidence);
  }

  public String detectColor() {
    Color detectedColor = colorSensor.getColor();

    ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);

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

  public void rotateWheel() {
    colorWheelMotor.set(1);
  }

  public void stopWheel() {
    colorWheelMotor.set(0);
  }

}

