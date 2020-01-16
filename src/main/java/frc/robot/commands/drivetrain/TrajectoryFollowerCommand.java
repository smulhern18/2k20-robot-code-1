package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;

public class TrajectoryFollowerCommand extends RamseteCommand {
  public TrajectoryFollowerCommand(Trajectory trajectory, DrivetrainSubsystem drivetrain) {
    super(trajectory,
        drivetrain::getPose,
        new RamseteController(Constants.DrivetrainConstants.RAMSETE_B, Constants.DrivetrainConstants.RAMSETE_ZETA),
        Constants.DrivetrainConstants.DRIVE_KINEMATICS,
        drivetrain::driveVelocity,
        drivetrain);
  }
}
