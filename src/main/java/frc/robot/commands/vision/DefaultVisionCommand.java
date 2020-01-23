package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.VisionConstants;
import frc.robot.subsystems.VisionSubsystem;

public class DefaultVisionCommand extends CommandBase {
  private VisionSubsystem visionSubsystem;

  public DefaultVisionCommand(VisionSubsystem visionSubsystem) {
    this.visionSubsystem = visionSubsystem;
    addRequirements(visionSubsystem);
  }

  @Override
  public void initialize() {
    visionSubsystem.setLightRing(VisionConstants.LED_ON);
  }
}
