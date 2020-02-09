package frc.robot.commands.auto.normal.trench;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.AutoAimAndShootCommand;

public class TrenchAutoCommand extends SequentialCommandGroup {
  public TrenchAutoCommand(RobotContainer robotContainer) {
    addCommands(
        // Collect five balls from enemy trench
        new ParallelCommandGroup(
            new TrajectoryFollowerCommand(robotContainer, TrenchTrajectories.GRAB),
            new CollectCommand(robotContainer).withTimeout(5)
        ),
        // run away
        new TrajectoryFollowerCommand(robotContainer, TrenchTrajectories.RETURN),
        // shoot 5
        new AutoAimAndShootCommand(robotContainer)
    );
  }
}