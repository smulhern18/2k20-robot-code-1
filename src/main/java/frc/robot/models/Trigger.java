package frc.robot.models;

import frc.robot.RobotContainer;
import frc.robot.subsystems.BallPathSubsystem;

public class Trigger extends edu.wpi.first.wpilibj2.command.button.Trigger {
  BallPathSubsystem ballPathSubsystem;
  public Trigger(RobotContainer robotContainer) {
    ballPathSubsystem = robotContainer.ballPathSubsystem;
  }

  @Override
  public boolean get() {
    return ballPathSubsystem.beltBannerSensor.beamBroken();
  }
}
