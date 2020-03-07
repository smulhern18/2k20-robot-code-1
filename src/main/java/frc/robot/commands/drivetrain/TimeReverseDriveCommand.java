package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DrivetrainSubsystem;

public class TimeReverseDriveCommand extends CommandBase {
  DrivetrainSubsystem drivetrainSubsystem;
  public TimeReverseDriveCommand(RobotContainer robotContainer) {
    drivetrainSubsystem = robotContainer.drivetrainSubsystem;
    addRequirements(drivetrainSubsystem);
  }

  @Override
  public void execute() {
    drivetrainSubsystem.drive(1, 1);
  }
}
