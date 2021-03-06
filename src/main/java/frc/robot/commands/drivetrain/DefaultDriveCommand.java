package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.input.AttackThree;
import frc.robot.input.AttackThree.AttackThreeAxis;
import frc.robot.subsystems.DrivetrainSubsystem;

/**
 * Default Drive Command
 */
public class DefaultDriveCommand extends CommandBase {
  private final DrivetrainSubsystem drivetrainSubsystem;

  private final AttackThree leftStick;
  private final AttackThree rightStick;


  public DefaultDriveCommand(RobotContainer robotContainer) {
    this.leftStick = robotContainer.leftStick;
    this.rightStick = robotContainer.rightStick;
    this.drivetrainSubsystem = robotContainer.drivetrainSubsystem;

    addRequirements(drivetrainSubsystem);
  }

  @Override
  public void initialize() {
    drivetrainSubsystem.setCoast();
  }

  /**
   * Called every time the scheduler runs while the command is scheduled.
   * Takes the joystick values and makes the robot move accordingly.
   */
  @Override
  public void execute() {
    double left = leftStick.getAxis(AttackThreeAxis.Y);
    left *= Math.abs(left); // squared to provide threshold
    double right = rightStick.getAxis(AttackThreeAxis.Y);
    right *= Math.abs(right); // squared to provide threshold
//    System.out.println(left+" "+right);
    drivetrainSubsystem.drive(left, right);
  }

  /**
   * At the end, stop the drivetrain.
   */
  @Override
  public void end(boolean interrupted) {
    drivetrainSubsystem.drive(0, 0);
  }
}
