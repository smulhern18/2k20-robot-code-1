package frc.robot.commands.auto.thief.oppositethree;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.PrepShooterCommand;
import frc.robot.commands.shooter.VisionAimAndShootCommand;

/**
 * 3 ball auto
 * steal 3 balls from opponent rendezvous
 */
public class OppositeThreeAutoCommand extends SequentialCommandGroup {
  public OppositeThreeAutoCommand(RobotContainer robotContainer) {
    addCommands(
        // steal three balls
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, OppositeThreeTrajectories.OPPOSITE_THREE_GRAB),
            new CollectCommand(robotContainer)
        ),
        // run away
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, OppositeThreeTrajectories.OPPOSITE_THREE_SHOOT),
            new PrepShooterCommand(robotContainer)
        ),
        // shoot three
        new VisionAimAndShootCommand(robotContainer)
    );
  }
}
