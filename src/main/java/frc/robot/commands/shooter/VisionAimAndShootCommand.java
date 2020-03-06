package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.abrahamblinkin.ChangeHatCommand;
import frc.robot.commands.trenchable.UntrenchCommand;
import frc.robot.commands.turret.VisionAimTurretCommand;
import frc.robot.subsystems.AbrahamBlinkinSubsystem;

public class VisionAimAndShootCommand extends SequentialCommandGroup {
  public VisionAimAndShootCommand(RobotContainer robotContainer) {
    addCommands(
        // untrench
        new UntrenchCommand(robotContainer).withTimeout(2),
        // aim turret
        new VisionAimTurretCommand(robotContainer),
        // set RPM and empty robot
        new ShootCommand(robotContainer, 5500).withTimeout(5),
        // turn off shooter wheel
        new StopShootingCommand(robotContainer),
        // indicate when done
        new ChangeHatCommand(robotContainer, AbrahamBlinkinSubsystem.Hat.RainbowGlitter).withTimeout(3)
    );
  }
}
