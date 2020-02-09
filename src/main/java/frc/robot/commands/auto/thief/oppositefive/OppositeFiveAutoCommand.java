package frc.robot.commands.auto.thief.oppositefive;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.AutoAimAndShootCommand;

public class OppositeFiveAutoCommand extends SequentialCommandGroup {
  public OppositeFiveAutoCommand(RobotContainer robotContainer) {
    addCommands(
        // Collect five balls from the enemy rendezvous
        new ParallelCommandGroup(
            new CollectCommand(robotContainer).withTimeout(9),
            new TrajectoryFollowerCommand(robotContainer, OppositeFiveTrajectories.OPPOSITE)
        ),
        // spin in place
        new TrajectoryFollowerCommand(robotContainer, OppositeFiveTrajectories.SPIN),
        // shoot five
        new AutoAimAndShootCommand(robotContainer)
    );
  }
}
