package frc.robot.commands.auto.thief.oppositetwo;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.AutoAimAndShootCommand;

public class OppositeTwoAutoCommand extends SequentialCommandGroup {
  public OppositeTwoAutoCommand(RobotContainer robotContainer) {
    addCommands(
        // collect two balls
        new ParallelCommandGroup(
            new CollectCommand(robotContainer).withTimeout(10),
            new TrajectoryFollowerCommand(robotContainer, OppositeTwoTrajectories.OPPOSITE_TWO_GRAB)
        ),
        // run away
        new TrajectoryFollowerCommand(robotContainer, OppositeTwoTrajectories.OPPOSITE_TWO_SHOOT),
        // shoot two or five
        new AutoAimAndShootCommand(robotContainer)
    );
  }
}
