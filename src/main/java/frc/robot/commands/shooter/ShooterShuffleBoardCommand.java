package frc.robot.commands.shooter;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterShuffleBoardCommand extends CommandBase {
  public NetworkTableEntry targetRPMEntry;
  private ShooterSubsystem shooterSubsystem;
  private NetworkTableEntry PEntry, IEntry, DEntry, FEntry;
  private double newP, newI, newD, newF;


  public ShooterShuffleBoardCommand() {
    this.shooterSubsystem = ShooterSubsystem.getInstance();
    PEntry = Shuffleboard.getTab(Constants.SubsystemConstants.TAB_NAME).add(ShooterConstants.CONFIG_P_ENTRY, ShooterConstants.P).getEntry();
    IEntry = Shuffleboard.getTab(Constants.SubsystemConstants.TAB_NAME).add(ShooterConstants.CONFIG_I_ENTRY, ShooterConstants.I).getEntry();
    FEntry = Shuffleboard.getTab(Constants.SubsystemConstants.TAB_NAME).add(ShooterConstants.CONFIG_D_ENTRY, ShooterConstants.D).getEntry();
    DEntry = Shuffleboard.getTab(Constants.SubsystemConstants.TAB_NAME).add(ShooterConstants.CONFIG_F_ENTRY, ShooterConstants.F).getEntry();
    targetRPMEntry = Shuffleboard.getTab(Constants.SubsystemConstants.TAB_NAME).add(ShooterConstants.TARGET_ENTRY, 0).getEntry();
    addRequirements(shooterSubsystem);
  }

  @Override
  public void execute() {
    newP = PEntry.getDouble(ShooterConstants.P);
    newI = PEntry.getDouble(ShooterConstants.P);
    newD = PEntry.getDouble(ShooterConstants.P);
    newF = PEntry.getDouble(ShooterConstants.P);
    shooterSubsystem.setTargetRPM(targetRPMEntry.getDouble(0));
    shooterSubsystem.getPairMotor().configPIDF(ShooterConstants.SLOT_ID, newP, newI, newD, newF);
  }


}
