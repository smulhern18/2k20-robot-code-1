package frc.robot.commands.auto.thief.half;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.normal.trench.TrenchAutoCommand;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.drivetrain.TimeDriveCommand;
import frc.robot.commands.drivetrain.TimeReverseDriveCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.PrepShooterCommand;
import frc.robot.commands.shooter.RampUpShooterCommand;
import frc.robot.commands.shooter.ShootCommand;
import frc.robot.commands.shooter.VisionAimAndShootCommand;
import frc.robot.commands.turret.VisionAimTurretCommand;

/**
 * 10 ball auto
 * Steal two from opponent's trench, empty own rendezvous
 */
public class HalfThiefTrenchAutoCommand extends SequentialCommandGroup {
  public HalfThiefTrenchAutoCommand(RobotContainer robotContainer) {
    addCommands(
        // Collect two balls from enemy trench
        new InstantCommand(() -> robotContainer.ballPathSubsystem.preload(3), robotContainer.ballPathSubsystem),
//        new TimeDriveCommand(robotContainer).withTimeout(.25),
//        new TimeReverseDriveCommand(robotContainer).withTimeout(.25),
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, HalfThiefTrenchTrajectories.FIRST_TWO),
            new CollectCommand(robotContainer)
        ),
//        new TrajectoryFollowerCommand(robotContainer, HalfThiefTrenchTrajectories.FIRST_TWO),
        // move to port and untrench
        new ParallelDeadlineGroup(
            new TrajectoryFollowerCommand(robotContainer, HalfThiefTrenchTrajectories.SHOOT_ONE),
            new RampUpShooterCommand(robotContainer, 5500)
        ),
        // shoot 5 balls
        new VisionAimTurretCommand(robotContainer),
        new ShootCommand(robotContainer, 5500)
        // Collect 5 balls
//        new ParallelDeadlineGroup(
//            new TrajectoryFollowerCommand(robotContainer, HalfThiefTrenchTrajectories.UNDER_RENDEZ),
//            new CollectCommand(robotContainer),
//            new PrepShooterCommand(robotContainer)
//        ),
        // Shoot 5 balls
//        new VisionAimAndShootCommand(robotContainer)
    );
  }
}
