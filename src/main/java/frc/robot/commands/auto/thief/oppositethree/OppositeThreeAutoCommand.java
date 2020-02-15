package frc.robot.commands.auto.thief.oppositethree;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.VisionAimAndShootCommand;

/**
 * 3 ball auto
 * steal 3 balls from opponent rendezvous
 */
public class OppositeThreeAutoCommand extends SequentialCommandGroup {
  public OppositeThreeAutoCommand(RobotContainer robotContainer) {
    addCommands(
        // steal three balls
        new ParallelCommandGroup(
            new CollectCommand(robotContainer).withTimeout(10),
            new TrajectoryFollowerCommand(robotContainer, OppositeThreeTrajectories.OPPOSITE_THREE_GRAB)
        ),
        // run away
        new TrajectoryFollowerCommand(robotContainer, OppositeThreeTrajectories.OPPOSITE_THREE_SHOOT),
        // shoot three
        new VisionAimAndShootCommand(robotContainer)
    );
  }
}
