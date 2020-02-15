package frc.robot.commands.auto.normal.twocell;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.VisionAimAndShootCommand;

/**
 * 5 ball auto
 * grab two from own rendezvous
 */
public class TwoCellAutoCommand extends SequentialCommandGroup {
  public TwoCellAutoCommand(RobotContainer robotContainer) {
    addCommands(
        // collect two balls
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, TwoBallTrajectories.TWO_GRAB),
            new CollectCommand(robotContainer)
        ),
        // shoot five balls
        new VisionAimAndShootCommand(robotContainer)
    );
  }
}
