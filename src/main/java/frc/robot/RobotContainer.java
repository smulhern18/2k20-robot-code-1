/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.commands.TestAutoCommand;
import frc.robot.commands.drivetrain.DefaultDriveCommand;
import frc.robot.commands.shooter.DefaultShootCommand;
import frc.robot.input.AttackThree;
import frc.robot.models.Color;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private AttackThree leftStick = new AttackThree(DrivetrainConstants.LEFT_JOYSTICK_CHANNEL);
  private AttackThree rightStick = new AttackThree(DrivetrainConstants.RIGHT_JOYSTICK_CHANNEL);

  private DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
  private ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  private Color color = Color.CORRUPT;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureButtonBindings();
    setDefaultCommands();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  /**
   * Sets all the default commands of the susbsytems. Helps with abstraction to do it here.
   * For instance, by doing it this way, the Drive subsystem does not know about the joysticks.
   */
  private void setDefaultCommands() {
    drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(drivetrainSubsystem, leftStick, rightStick));
    shooterSubsystem.setDefaultCommand(new DefaultShootCommand(shooterSubsystem));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    drivetrainSubsystem.resetAll();
    return new TestAutoCommand(drivetrainSubsystem);
  }

  /**
   * Gets the color from the DS
   */
  public void readColor() {
    String gameData = DriverStation.getInstance().getGameSpecificMessage();
    if (gameData.length() > 0) {
      color = Color.getColor(gameData.charAt(0));
    } else {
      color = Color.CORRUPT;
    }
  }
}
