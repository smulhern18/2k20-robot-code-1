package frc.robot.commands.trenchable;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.TrenchableSubsystem;

public class TrenchCommand extends CommandBase {
  TrenchableSubsystem trenchableSubsystem;
  ClimberSubsystem climberSubsystem;

  public TrenchCommand() {
    this.trenchableSubsystem = TrenchableSubsystem.getInstance();
    this.climberSubsystem = ClimberSubsystem.getInstance();
    addRequirements(trenchableSubsystem, climberSubsystem);
  }

  @Override
  public void initialize() {
    trenchableSubsystem.trench();
    climberSubsystem.slap();
  }

  @Override
  public boolean isFinished() {
    return trenchableSubsystem.getState() == TrenchableSubsystem.TrenchableState.TRENCHABLE;
  }
}
