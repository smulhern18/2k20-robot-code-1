package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DrivetrainSubsystem;

public class TimeDriveCommand extends CommandBase {
  DrivetrainSubsystem drivetrainSubsystem;
  public TimeDriveCommand(RobotContainer robotContainer) {
    drivetrainSubsystem = robotContainer.drivetrainSubsystem;
    addRequirements(drivetrainSubsystem);
  }

  @Override
  public void execute() {
    drivetrainSubsystem.drive(-.5, -.5);
  }
}
