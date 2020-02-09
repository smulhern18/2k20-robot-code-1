/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.commands.auto.test.TestAutoCommand;
import frc.robot.commands.ballpath.DefaultShiftCellCommand;
import frc.robot.commands.blinkinpark.AllianceColorCommand;
import frc.robot.commands.drivetrain.DefaultDriveCommand;
import frc.robot.commands.shooter.ShooterShuffleBoardCommand;
import frc.robot.input.AttackThree;
import frc.robot.models.Color;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private AttackThree leftStick = new AttackThree(DrivetrainConstants.LEFT_JOYSTICK_CHANNEL);
  private AttackThree rightStick = new AttackThree(DrivetrainConstants.RIGHT_JOYSTICK_CHANNEL);

  private AbrahamBlinkinSubsystem abrahamBlinkinSubsystem = AbrahamBlinkinSubsystem.getInstance();
  private BallPathSubsystem ballPathSubsystem = BallPathSubsystem.getInstance();
  private ClimberSubsystem climberSubsystem = ClimberSubsystem.getInstance();
  private CollectorSubsystem collectorSubsystem = CollectorSubsystem.getInstance();
  private DrivetrainSubsystem drivetrainSubsystem = DrivetrainSubsystem.getInstance();
  private ShooterSubsystem shooterSubsystem = ShooterSubsystem.getInstance();
  private TrenchableSubsystem trenchableSubsystem = TrenchableSubsystem.getInstance();
  private TurretSubsystem turretSubsystem = TurretSubsystem.getInstance();
  private VisionSubsystem visionSubsystem = VisionSubsystem.getInstance();

  private Color color = Color.CORRUPT;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    Shuffleboard.selectTab(Constants.SubsystemConstants.TAB_NAME);
    configureButtonBindings();
    setDefaultCommands();
  }

  /**
   * Maps commands to buttons.
   */
  private void configureButtonBindings() {
  }

  /**
   * Sets all the default commands of the subsystems. Helps with abstraction to do it here.
   * For instance, by doing it this way, the Drive subsystem does not know about the joysticks.
   */
  private void setDefaultCommands() {
    drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(leftStick, rightStick));
    shooterSubsystem.setDefaultCommand(new ShooterShuffleBoardCommand()); //TODO: remove after tuned
    abrahamBlinkinSubsystem.setDefaultCommand(new AllianceColorCommand());
    ballPathSubsystem.setDefaultCommand(new DefaultShiftCellCommand());
  }

  /**
   * Determines the appropriate auto command.
   *
   * @return the auto command
   */
  public Command getAutoCommand() {
    drivetrainSubsystem.resetAll();
    return new TestAutoCommand();
  }

  /**
   * Gets the color from the DS
   */
  public Color readColor() {
    String gameData = DriverStation.getInstance().getGameSpecificMessage();
    if (gameData.length() > 0) {
      color = Color.getColor(gameData.charAt(0));
    } else {
      color = Color.CORRUPT;
    }
    return color;
  }
}
