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

public class ColorWheelSubsystem extends SubsystemBase {
  ColorSensorV3 colorSensor;
  ColorMatch m_colorMatcher = new ColorMatch();
  /**
   * Creates a new ColorWheelSubsystem.
   */
  public ColorWheelSubsystem() {
    colorSensor = new ColorSensorV3(ColorWheelConstants.COLOR_SENSOR_PORT);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
