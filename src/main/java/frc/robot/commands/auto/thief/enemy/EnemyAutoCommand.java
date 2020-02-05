package frc.robot.commands.auto.thief.enemy;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

public class EnemyAutoCommand extends SequentialCommandGroup {
  public EnemyAutoCommand(DrivetrainSubsystem drivetrain) {
    addCommands(
        new TrajectoryFollowerCommand(EnemyTrajectories.FIRST_TWO, drivetrain),
        new TrajectoryFollowerCommand(EnemyTrajectories.SHOOT_ONE, drivetrain),
        new TrajectoryFollowerCommand(EnemyTrajectories.UNDER_RENDEZ, drivetrain));
  }
}
