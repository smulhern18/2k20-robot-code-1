package frc.robot.commands.auto.normal.crazy;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.AutoAimAndShootCommand;
import frc.robot.commands.trenchable.TrenchCommand;
import frc.robot.commands.trenchable.UntrenchCommand;

public class CrazyAutoCommand extends SequentialCommandGroup {
  public CrazyAutoCommand() {
    addCommands(
        // grab first two balls, to make 5 in robot
        new ParallelCommandGroup(
            new UntrenchCommand(),
            new CollectCommand().withTimeout(3),
            new TrajectoryFollowerCommand(CrazyTrajectories.START)
        ),
        // shoot five balls
        new AutoAimAndShootCommand(),
        new TrenchCommand(),
        // go under color wheel, grab three balls
        new ParallelCommandGroup(
            new TrajectoryFollowerCommand(CrazyTrajectories.FIRST_THREE),
            new CollectCommand().withTimeout(3)
        ),
        new TrajectoryFollowerCommand(CrazyTrajectories.LAST_TWO),
        // collect last two balls
        new ParallelCommandGroup(
            new TrajectoryFollowerCommand(CrazyTrajectories.GRAB_TWO),
            new CollectCommand().withTimeout(2)
        ),
        // shoot five balls
        new AutoAimAndShootCommand()
    );
  }
}
