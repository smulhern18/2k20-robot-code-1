package frc.robot.commands.auto.normal.threecell;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.AutoAimAndShootCommand;

public class ThreeCellAutoCommand extends SequentialCommandGroup {
  public ThreeCellAutoCommand(RobotContainer robotContainer) {
    addCommands(
        // shoot three balls
        new AutoAimAndShootCommand(robotContainer),
        // collect three balls
        new ParallelCommandGroup(
            new CollectCommand(robotContainer).withTimeout(10),
            new TrajectoryFollowerCommand(robotContainer, ThreeCellTrajectories.THREE_CELL)
        ),
        // shoot three balls
        new AutoAimAndShootCommand(robotContainer)
    );
  }
}
