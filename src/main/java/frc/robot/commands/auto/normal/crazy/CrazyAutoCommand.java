package frc.robot.commands.auto.normal.crazy;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.AutoAimAndShootCommand;
import frc.robot.commands.trenchable.TrenchCommand;
import frc.robot.commands.trenchable.UntrenchCommand;
import frc.robot.subsystems.*;

public class CrazyAutoCommand extends SequentialCommandGroup {
  public CrazyAutoCommand(BallPathSubsystem ballPathSubsystem, ClimberSubsystem climberSubsystem, CollectorSubsystem collectorSubsystem, DrivetrainSubsystem drivetrainSubsystem, ShooterSubsystem shooterSubsystem, TrenchableSubsystem trenchableSubsystem, TurretSubsystem turretSubsystem, VisionSubsystem visionSubsystem) {
    addCommands(
        // grab first two balls, to make 5 in robot
        new ParallelCommandGroup(
            new UntrenchCommand(trenchableSubsystem, climberSubsystem),
            new CollectCommand(collectorSubsystem, ballPathSubsystem).withTimeout(3),
            new TrajectoryFollowerCommand(CrazyTrajectories.START, drivetrainSubsystem)
        ),
        // shoot five balls
        new AutoAimAndShootCommand(ballPathSubsystem, climberSubsystem, drivetrainSubsystem, shooterSubsystem, trenchableSubsystem, turretSubsystem, visionSubsystem),
        new TrenchCommand(trenchableSubsystem, climberSubsystem),
        // go under color wheel, grab three balls
        new ParallelCommandGroup(
            new TrajectoryFollowerCommand(CrazyTrajectories.FIRST_THREE, drivetrainSubsystem),
            new CollectCommand(collectorSubsystem, ballPathSubsystem).withTimeout(3)
        ),
        new TrajectoryFollowerCommand(CrazyTrajectories.LAST_TWO, drivetrainSubsystem),
        // collect last two balls
        new ParallelCommandGroup(
            new TrajectoryFollowerCommand(CrazyTrajectories.GRAB_TWO, drivetrainSubsystem),
            new CollectCommand(collectorSubsystem, ballPathSubsystem).withTimeout(2)
        ),
        // shoot five balls
        new AutoAimAndShootCommand(ballPathSubsystem, climberSubsystem, drivetrainSubsystem, shooterSubsystem, trenchableSubsystem, turretSubsystem, visionSubsystem)
    );
  }
}
