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
import frc.robot.commands.colorwheel.RotationalCommand;
import frc.robot.commands.drivetrain.DefaultDriveCommand;
import frc.robot.input.AttackThree;
import frc.robot.models.AutoChooser;
import frc.robot.models.Color;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public AttackThree leftStick = new AttackThree(DrivetrainConstants.LEFT_JOYSTICK_CHANNEL);
  public AttackThree rightStick = new AttackThree(DrivetrainConstants.RIGHT_JOYSTICK_CHANNEL);

  public AbrahamBlinkinSubsystem abrahamBlinkinSubsystem;// = new AbrahamBlinkinSubsystem();
  public BallPathSubsystem ballPathSubsystem;// = new BallPathSubsystem();
  public ClimberSubsystem climberSubsystem;// = new ClimberSubsystem();
  public CollectorSubsystem collectorSubsystem;// = new CollectorSubsystem();
  public ColorWheelSubsystem colorWheelSubsystem;// = new ColorWheelSubsystem();
  public DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
  public ShooterSubsystem shooterSubsystem;// = new ShooterSubsystem();
  public TrenchableSubsystem trenchableSubsystem;// = new TrenchableSubsystem();
  public TurretSubsystem turretSubsystem;// = new TurretSubsystem();
  public VisionSubsystem visionSubsystem;// = new VisionSubsystem();

  private AutoChooser autoChooser;

  private Color color = Color.CORRUPT;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    Shuffleboard.selectTab(Constants.SubsystemConstants.DEBUG_TAB_NAME);
//    Shuffleboard.selectTab();
    configureButtonBindings();
    setDefaultCommands();
    // TODO: uncomment when subsystems exist
//    autoChooser = new AutoChooser(this);
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
    drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(this));
//    shooterSubsystem.setDefaultCommand(new ShooterShuffleBoardCommand(this)); //TODO: remove after tuned
//    abrahamBlinkinSubsystem.setDefaultCommand(new AllianceColorCommand(this));
//    ballPathSubsystem.setDefaultCommand(new DefaultShiftCellCommand(this));
<<<<<<< HEAD
      colorWheelSubsystem.setDefaultCommand(new RotationalCommand(this));
=======
    colorWheelSubsystem.setDefaultCommand(new RotationalCommand(this));
>>>>>>> e9b387251901293e826045a480c730aa7f8de7b6
  }

  /**
   * Determines the appropriate auto command.
   *
   * @return the auto command
   */
  public Command getAutoCommand() {
    drivetrainSubsystem.resetAll();
    //TODO: uncomment for real robot
//    return autoChooser.getSelected();
    return new TestAutoCommand(this);
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
