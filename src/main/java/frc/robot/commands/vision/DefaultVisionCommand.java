package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.VisionSubsystem;

public class DefaultVisionCommand extends CommandBase {
  private VisionSubsystem visionSubsystem;
  public DefaultVisionCommand(RobotContainer robotContainer) {
    visionSubsystem = robotContainer.visionSubsystem;
    addRequirements(visionSubsystem);
  }

  @Override
  public void initialize() {
    visionSubsystem.setLightRing(true);
  }
}
