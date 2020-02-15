package frc.robot.commands.auto.thief.oppositetwo;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.PrepShooterCommand;
import frc.robot.commands.shooter.VisionAimAndShootCommand;

/**
 * 2 or 5 ball auto
 * steal 2 balls from opponent rendezvous
 */
public class OppositeTwoAutoCommand extends SequentialCommandGroup {
  public OppositeTwoAutoCommand(RobotContainer robotContainer) {
    addCommands(
        // collect two balls
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, OppositeTwoTrajectories.OPPOSITE_TWO_GRAB),
            new CollectCommand(robotContainer)
        ),
        // run away
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, OppositeTwoTrajectories.OPPOSITE_TWO_SHOOT),
            new PrepShooterCommand(robotContainer)
        ),
        // shoot two or five
        new VisionAimAndShootCommand(robotContainer)
    );
  }
}
