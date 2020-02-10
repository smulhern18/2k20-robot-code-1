package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.blinkinpark.ChangeHatCommand;
import frc.robot.commands.trenchable.UntrenchCommand;
import frc.robot.commands.turret.AutoAimTurretCommand;
import frc.robot.subsystems.AbrahamBlinkinSubsystem;

public class AutoAimAndShootCommand extends SequentialCommandGroup {
  public AutoAimAndShootCommand(RobotContainer robotContainer) {
    addCommands(
        // untrench
        new UntrenchCommand(robotContainer),
        // aim turret
        new AutoAimTurretCommand(robotContainer),
        // set RPM
        new AutoSetRPMCommand(robotContainer),
        // shoot when ready
        new ShootCommand(robotContainer),
        //indicate when done
        new ChangeHatCommand(robotContainer, AbrahamBlinkinSubsystem.Hat.RainbowGlitter, 3)
    );
  }
}
