package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.abrahamblinkin.ChangeHatCommand;
import frc.robot.commands.trenchable.UntrenchCommand;
import frc.robot.commands.turret.AutoAimTurretCommand;
import frc.robot.subsystems.AbrahamBlinkinSubsystem;

public class VisionAimAndShootCommand extends SequentialCommandGroup {
  public VisionAimAndShootCommand(RobotContainer robotContainer) {
    addCommands(
        // untrench
        new UntrenchCommand(robotContainer),
        // aim turret
        new AutoAimTurretCommand(robotContainer),
        // set RPM and empty robot
        new VisionShootCommand(robotContainer),
        //wait for 3 seconds before shutting off the gooseneck
        new WaitCommand(3),
        // turn off shooter wheel
        new StopShootingCommand(robotContainer),
        // indicate when done
        new ChangeHatCommand(robotContainer, AbrahamBlinkinSubsystem.Hat.RainbowGlitter, 3)
    );
  }
}
