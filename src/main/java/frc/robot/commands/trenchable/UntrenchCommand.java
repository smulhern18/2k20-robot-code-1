package frc.robot.commands.trenchable;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.TrenchableSubsystem;

public class UntrenchCommand extends CommandBase {
  TrenchableSubsystem trenchableSubsystem;
  ClimberSubsystem climberSubsystem;

  /**
   * Creats a new Untrench Command
   * @param robotContainer
   */
  public UntrenchCommand(RobotContainer robotContainer) {
    this.trenchableSubsystem = robotContainer.trenchableSubsystem;
    this.climberSubsystem = robotContainer.climberSubsystem;
    addRequirements(trenchableSubsystem, climberSubsystem);
  }

  /**
   * Upon startup, untrench everything
   */
  @Override
  public void initialize() {
    trenchableSubsystem.untrench();
    climberSubsystem.unslap();
  }

  /**
   * Decides when untrenching is complete
   * @return when the state of the subsystem is untrenched
   */
  @Override
  public boolean isFinished() {
    return trenchableSubsystem.trenchablifier.get().equals(TrenchableSubsystem.TrenchableState.UNTRENCHABLE);
  }
}
