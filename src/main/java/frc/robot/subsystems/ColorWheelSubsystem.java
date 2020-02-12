/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;

import frc.robot.Constants.ColorWheelConstants;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorMatchResult;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ColorWheelSubsystem extends BeefSubsystemBase {
  ColorSensorV3 colorSensor;
  ColorMatch colorMatcher;
  public String colorString;
  public double confidence;
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

  @Override
  public void periodic() {
    Color detectedColor = colorSensor.getColor();

    /**
     * Run the color match algorithm on our detected color
     */
    ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);

    if (match.color == ColorWheelConstants.BLUE_TARGET) {
      colorString = "Blue";
    } else if (match.color == ColorWheelConstants.GREEN_TARGET) {
      colorString = "Green";
    } else if (match.color == ColorWheelConstants.RED_TARGET) {
      colorString = "Red";
    } else if (match.color == ColorWheelConstants.YELLOW_TARGET) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }
  }
}

