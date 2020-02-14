package frc.robot.commands.trenchable;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.TrenchableSubsystem;

public class TrenchCommand extends CommandBase {
  TrenchableSubsystem trenchableSubsystem;
  ClimberSubsystem climberSubsystem;
  RobotContainer robotContainer;

  public TrenchCommand(RobotContainer robotContainer) {
    this.trenchableSubsystem = robotContainer.trenchableSubsystem;
    this.climberSubsystem = robotContainer.climberSubsystem;
    this.robotContainer = robotContainer;
    addRequirements(trenchableSubsystem, climberSubsystem);
  }

  /**
   * Turn off shooter and trench
   */
  @Override
  public void initialize() {
    new InstantCommand(() -> robotContainer.shooterSubsystem.stop(), robotContainer.shooterSubsystem);
    trenchableSubsystem.trench();
    climberSubsystem.slap();
  }

  @Override
  public boolean isFinished() {
    return trenchableSubsystem.getState() == TrenchableSubsystem.TrenchableState.TRENCHABLE;
  }
}
