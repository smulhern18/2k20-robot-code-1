package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.trenchable.UntrenchCommand;
import frc.robot.commands.turret.AutoAimTurretCommand;

public class AutoAimAndShootCommand extends SequentialCommandGroup {
  public AutoAimAndShootCommand() {
    addCommands(
        // untrench
        new UntrenchCommand(),
        // aim turret
        new AutoAimTurretCommand(),
        // set RPM
        new AutoSetRPMCommand(),
        // shoot when ready
        new ShootCommand()
    );
  }
}
