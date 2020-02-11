package frc.robot.commands.auto.thief.trench;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.AutoAimAndShootCommand;
import frc.robot.commands.trenchable.UntrenchCommand;

public class TrenchThiefAutoCommand extends SequentialCommandGroup {
  public TrenchThiefAutoCommand(RobotContainer robotContainer) {
    addCommands(
        //
        new ParallelCommandGroup(
            new CollectCommand(robotContainer).withTimeout(8),
            new TrajectoryFollowerCommand(robotContainer, TrenchThiefTrajectories.STEAL_FIVE)
        ),
        new ParallelCommandGroup(
            new UntrenchCommand(robotContainer),
            new TrajectoryFollowerCommand(robotContainer, TrenchThiefTrajectories.RETREAT)
        ),
        new AutoAimAndShootCommand(robotContainer)
    );
  }
}
