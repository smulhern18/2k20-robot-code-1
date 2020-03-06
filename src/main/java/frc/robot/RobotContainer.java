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
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.abrahamblinkin.AllianceColorCommand;
import frc.robot.commands.auto.ShootThreeAutoCommand;
import frc.robot.commands.auto.Trajectories;
import frc.robot.commands.auto.normal.trench.TrenchAutoCommand;
import frc.robot.commands.auto.test.TestTrajectories;
import frc.robot.commands.ballpath.DefaultShiftCellCommand;
import frc.robot.commands.ballpath.RunBallPathCommand;
import frc.robot.commands.ballpath.SpitInCommand;
import frc.robot.commands.ballpath.SpitOutCommand;
import frc.robot.commands.climber.ExtendClimbCommand;
import frc.robot.commands.climber.RetractClimbCommand;
import frc.robot.commands.climber.ToggleSlapCommand;
import frc.robot.commands.climber.TraverseCommand;
import frc.robot.commands.collector.CollectCommand;
import frc.robot.commands.collector.ToggleCollectCommand;
import frc.robot.commands.collector.ToggleCollectorPistonCommand;
import frc.robot.commands.drivetrain.DefaultDriveCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.ManualShootCommand;
import frc.robot.commands.shooter.RunShooterCommand;
import frc.robot.commands.shooter.PrepShooterCommand;
import frc.robot.commands.shooter.ShootCommand;
import frc.robot.commands.trenchable.ToggleTrenchabilityCommand;
import frc.robot.commands.turret.*;
import frc.robot.commands.vision.DefaultVisionCommand;
import frc.robot.input.AttackThree;
import frc.robot.input.ButtonBoxLeft;
import frc.robot.input.ButtonBoxRight;
import frc.robot.models.AutoChooser;
import frc.robot.models.Color;
import frc.robot.models.Trigger;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public AttackThree leftStick = new AttackThree(Constants.InputConstants.LEFT_JOYSTICK_CHANNEL);
  public AttackThree rightStick = new AttackThree(Constants.InputConstants.RIGHT_JOYSTICK_CHANNEL);
  public AbrahamBlinkinSubsystem abrahamBlinkinSubsystem = new AbrahamBlinkinSubsystem();
  public BallPathSubsystem ballPathSubsystem = new BallPathSubsystem();
  public ClimberSubsystem climberSubsystem = new ClimberSubsystem();
  public CollectorSubsystem collectorSubsystem = new CollectorSubsystem();
  public ColorWheelSubsystem colorWheelSubsystem;// = new ColorWheelSubsystem();
  public DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
  public ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  public TrenchableSubsystem trenchableSubsystem = new TrenchableSubsystem();
  public TurretSubsystem turretSubsystem = new TurretSubsystem();
  public VisionSubsystem visionSubsystem = new VisionSubsystem();
  private ButtonBoxLeft buttonBoxLeft = new ButtonBoxLeft(Constants.InputConstants.BUTTON_BOX_LEFT_CHANNEL);
  private ButtonBoxRight buttonBoxRight = new ButtonBoxRight(Constants.InputConstants.BUTTON_BOX_RIGHT_CHANNEL);
  private AutoChooser autoChooser;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    Shuffleboard.selectTab(Constants.SubsystemConstants.DEBUG_TAB_NAME);
//    Shuffleboard.selectTab(Constants.SubsystemConstants.DRIVER_TAB_NAME);
    configureButtonBindings();
    setDefaultCommands();
    // TODO: uncomment when subsystems exist
//    autoChooser = new AutoChooser(this);
  }

  /**
   * Gets the color from the DS
   */
  public static Color readColor() {
    String gameData = DriverStation.getInstance().getGameSpecificMessage();
    Color color = Color.CORRUPT;
    if (gameData.length() > 0) {
      color = Color.getColor(gameData.charAt(0));
    }
    return color;
  }

  /**
   * Maps commands to buttons.
   */
  private void configureButtonBindings() {
//    rightStick.getButton(1).whenPressed(new InstantCommand(collectorSubsystem::deploy, collectorSubsystem));
//    leftStick.getButton(1).whenPressed(new InstantCommand(collectorSubsystem::undeploy, collectorSubsystem));

    /* Driver sticks */
    // Trench or untrench when pressed
//    leftStick.getButton(1).whenPressed(new ToggleTrenchabilityCommand(this));
//    rightStick.getButton(1).whenPressed(new ManualShootCommand(this, 7000));
    // Auto aim turret, rev up shooter, empty robot of balls
    leftStick.getButton(1).whenPressed(new TurretCommand(this, 0));
    rightStick.getButton(1).whenPressed(new TurretCommand(this, 90));
//    rightStick.getButton(1).whileActiveOnce(new RunBallPathCommand(this, BallPathSubsystem.BallPathDirection.IN));
//rightStick.getButton(1).whileActiveOnce(new InstantCommand(() -> ballPathSubsystem.runIndexer(), ballPathSubsystem));
    /* Operator button box */

    /* Climb buttons */
    // Prepare climber by unslapping and extending
    buttonBoxLeft.extend.whenPressed(new ExtendClimbCommand(this));
//    // Toggle slapping onto coat hanger
    buttonBoxLeft.slap.whenPressed(new ToggleSlapCommand(this));
//    // Climb by retracting elevator
    buttonBoxLeft.retract.whileActiveOnce(new RetractClimbCommand(this));
//    // Traverse coat hanger left
    buttonBoxLeft.traverseLeft.whileActiveOnce(new TraverseCommand(this, ClimberSubsystem.TraverseDirection.LEFT));
//    // Traverse coat hanger right
    buttonBoxLeft.traverseRight.whileActiveOnce(new TraverseCommand(this, ClimberSubsystem.TraverseDirection.RIGHT));
//
//    /* Manual buttons */
//    // unused currently
////    buttonBoxLeft.resetIndexer.whenPressed(new WaitCommand(1));
//    // Set shooter RPM to default speed
    buttonBoxLeft.defaultShooterSpeed.whenPressed(new RunShooterCommand(this, Constants.ShooterConstants.DEFAULT_RPM));
//    // Sets turret straight forward
    buttonBoxRight.resetTurret.whenPressed(new ResetTurretCommand(this)); // TODO: locate button
    // manual turret left
    buttonBoxLeft.jogTurretLeft.whileActiveOnce(new JogTurretCommand(this, TurretSubsystem.TurretDirection.LEFT));
    buttonBoxLeft.jogTurretRight.whileActiveOnce(new JogTurretCommand(this, TurretSubsystem.TurretDirection.RIGHT));
//    // Spin ball path and collector in reverse
    buttonBoxLeft.spitOut.whileActiveOnce(new SpitOutCommand(this));
//    // Spin ball path and collector in the correct direction
    buttonBoxLeft.spitIn.whileActiveOnce(new SpitInCommand(this));
//
//    /* Main teleop buttons */
//    // Untrench, aim, spin up shooter wheel
    buttonBoxRight.autoTarget.whenPressed(new VisionAimTurretCommand(this));
//    // Collect 5 balls
    buttonBoxRight.collect.whenPressed(new CollectCommand(this));
//    // shoot until empty
//    buttonBoxRight.shoot.whenPressed(new VisionAimAndShootCommand(this));
    buttonBoxRight.shoot.whenPressed(new ShootCommand(this, 5500).withTimeout(10));
//    // toggle trenchability
    buttonBoxRight.trenchable.whenPressed(new ToggleTrenchabilityCommand(this));
//
//    /* Color wheel*/
//    // do color wheel rotation control
//    buttonBoxRight.rotationControl.whenPressed(new RotationalCommand(this));
//    // do color wheel position control
    buttonBoxRight.manualColorWheelRotation.whenPressed(new ToggleCollectCommand(this));
//    buttonBoxRight.positionControl.whe //TODO: write the command
    //TODO: manual spin

  }

  /**
   * Sets all the default commands of the subsystems. Helps with abstraction to do it here.
   * For instance, by doing it this way, the Drive subsystem does not know about the joysticks.
   */
  private void setDefaultCommands() {
    drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(this));
    new Trigger(this).whenActive(new DefaultShiftCellCommand(this));
//    visionSubsystem.setDefaultCommand(new DefaultVisionCommand(this));
    abrahamBlinkinSubsystem.setDefaultCommand(new AllianceColorCommand(this));
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
//    return new ShootThreeAutoCommand(this);
    return new TrenchAutoCommand(this);
  }
  public void teleopInit() {
    System.out.println("Teleop init");
    climberSubsystem.untriggerClimb();
    climberSubsystem.unslap();
    trenchableSubsystem.untrench();
    collectorSubsystem.undeploy();
    turretSubsystem.resetTurretEncoder();
  }
}
