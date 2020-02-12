/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ColorWheelConstants;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorMatchResult;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ColorWheelSubsystem extends SubsystemBase {
  ColorSensorV3 colorSensor;
  ColorMatch colorMatcher;
  /**
   * Creates a new ColorWheelSubsystem.
   */
  public ColorWheelSubsystem() {
    colorSensor = new ColorSensorV3(ColorWheelConstants.COLOR_SENSOR_PORT);
    colorMatcher = new ColorMatch();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void robotInit() {
    colorMatcher.addColorMatch(ColorWheelConstants.BLUE_TARGET);
    colorMatcher.addColorMatch(ColorWheelConstants.GREEN_TARGET);
    colorMatcher.addColorMatch(ColorWheelConstants.RED_TARGET);
    colorMatcher.addColorMatch(ColorWheelConstants.YELLOW_TARGET);    
  }

  public void robotPeriodic() {
    /**
     * The method GetColor() returns a normalized color value from the sensor and can be
     * useful if outputting the color to an RGB LED or similar. To
     * read the raw color, use GetRawColor().
     * 
     * The color sensor works best when within a few inches from an object in
     * well lit conditions (the built in LED is a big help here!). The farther
     * an object is the more light from the surroundings will bleed into the 
     * measurements and make it difficult to accurately determine its color.
     */
    Color detectedColor = colorSensor.getColor();

    /**
     * Run the color match algorithm on our detected color
     */
    String colorString;
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

    /**
     * Open Smart Dashboard or Shuffleboard to see the color detected by the 
     * sensor.
     */
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
  }
}
}
