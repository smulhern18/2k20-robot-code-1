package frc.robot.commands.auto.normal.crazy;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.VisionAimAndShootCommand;
import frc.robot.commands.trenchable.TrenchCommand;
import frc.robot.commands.trenchable.UntrenchCommand;

/**
 * 10 ball auto
 * preload 3, empty own trench, grab two from own rendezvous
 */
public class CrazyAutoCommand extends SequentialCommandGroup {
  public CrazyAutoCommand(RobotContainer robotContainer) {
    addCommands(
        // grab first two balls, to make 5 in robot
        new ParallelCommandGroup(
            new UntrenchCommand(robotContainer).withTimeout(3),
            new CollectCommand(robotContainer).withTimeout(3),
            new TrajectoryFollowerCommand(robotContainer, CrazyTrajectories.START)
        ),
        // shoot five balls
        new VisionAimAndShootCommand(robotContainer),
        new TrenchCommand(robotContainer),
        // go under color wheel, grab three balls
        new ParallelCommandGroup(
            new TrajectoryFollowerCommand(robotContainer, CrazyTrajectories.FIRST_THREE),
            new CollectCommand(robotContainer).withTimeout(3)
        ),
        new TrajectoryFollowerCommand(robotContainer, CrazyTrajectories.LAST_TWO),
        // collect last two balls
        new ParallelCommandGroup(
            new TrajectoryFollowerCommand(robotContainer, CrazyTrajectories.GRAB_TWO),
            new CollectCommand(robotContainer).withTimeout(2)
        ),
        // shoot five balls
        new VisionAimAndShootCommand(robotContainer)
    );
  }
}
