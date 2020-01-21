/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.models.Color;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.input.AttackThree;
import frc.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private AttackThree leftStick = new AttackThree(DrivetrainConstants.LEFT_JOYSTICK_CHANNEL);
  private AttackThree rightStick = new AttackThree(DrivetrainConstants.RIGHT_JOYSTICK_CHANNEL);

  private DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem(leftStick, rightStick);
  private ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  private Color color;


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
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

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    drivetrainSubsystem.resetAll();
    System.out.println(Units.degreesToRadians(45));
    Trajectory grabTrajectory = TrajectoryGenerator.generateTrajectory(
        List.of(
            new Pose2d(0, 0, new Rotation2d(0)),
            new Pose2d(1, 0, new Rotation2d(3.14 / 2))),
        drivetrainSubsystem.getForwardTrajectoryConfig());

    Trajectory returnTrajectory = TrajectoryGenerator.generateTrajectory(
        List.of(
            new Pose2d(1, 0, new Rotation2d(Units.degreesToRadians(45))),
            new Pose2d(0, 0, new Rotation2d(0))),
        drivetrainSubsystem.getBackwardTrajectoryConfig());

    Command grabCommand = new TrajectoryFollowerCommand(grabTrajectory, drivetrainSubsystem).andThen(() -> drivetrainSubsystem.drive(0, 0));
//    TrajectoryFollowerCommand returnCommand = new TrajectoryFollowerCommand(returnTrajectory, drivetrain);
    return grabCommand;//.andThen(returnCommand).andThen(() -> drivetrain.drive(0, 0));
  }
}
