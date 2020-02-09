package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class AutoSetRPMCommand extends InstantCommand {
  public AutoSetRPMCommand() {
    super(() -> ShooterSubsystem.getInstance().setTargetRPM(ShooterSubsystem.getInstance().inchesToRPM(VisionSubsystem.getInstance().getDistanceToTarget())), ShooterSubsystem.getInstance());
  }
}
