package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.trenchable.UntrenchCommand;
import frc.robot.commands.turret.AutoAimTurretCommand;
import frc.robot.subsystems.*;

public class AutoAimAndShootCommand extends SequentialCommandGroup {
  public AutoAimAndShootCommand(BallPathSubsystem ballPathSubsystem, ClimberSubsystem climberSubsystem, DrivetrainSubsystem drivetrainSubsystem, ShooterSubsystem shooterSubsystem, TrenchableSubsystem trenchableSubsystem, TurretSubsystem turretSubsystem, VisionSubsystem visionSubsystem) {
    addCommands(
        // untrench
        new UntrenchCommand(trenchableSubsystem, climberSubsystem),
        // aim turret
        new AutoAimTurretCommand(turretSubsystem, visionSubsystem, drivetrainSubsystem),
        // set RPM
        new InstantCommand(() -> shooterSubsystem.setTargetRPM(shooterSubsystem.inchesToRPM(visionSubsystem.getDistanceToTarget())), shooterSubsystem),
        // shoot when ready
        new ShootCommand(shooterSubsystem, ballPathSubsystem)
    );
  }
}
