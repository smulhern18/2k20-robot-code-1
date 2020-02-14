package frc.robot.commands.auto.normal.twocell;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.AutoAimAndShootCommand;

/**
 * 5 ball auto
 * grab two from own rendezvous
 */
public class TwoCellAutoCommand extends SequentialCommandGroup {
  public TwoCellAutoCommand(RobotContainer robotContainer) {
    addCommands(
        // collect two balls
        new ParallelCommandGroup(
            new CollectCommand(robotContainer),
            new TrajectoryFollowerCommand(robotContainer, TwoBallTrajectories.TWO_GRAB)
        ),
        // shoot five balls
        new AutoAimAndShootCommand(robotContainer)
    );
  }
}
