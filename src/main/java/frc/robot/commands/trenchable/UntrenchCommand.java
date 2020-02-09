package frc.robot.commands.trenchable;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TrenchableSubsystem;

public class UntrenchCommand extends CommandBase {
  TrenchableSubsystem trenchableSubsystem;

  public UntrenchCommand(TrenchableSubsystem trenchableSubsystem) {
    this.trenchableSubsystem = trenchableSubsystem;
    addRequirements(trenchableSubsystem);
  }

  @Override
  public void initialize() {
    trenchableSubsystem.untrench();
  }

  @Override
  public boolean isFinished() {
    return trenchableSubsystem.getState() == TrenchableSubsystem.TrenchableState.UNTRENCHABLE;
  }
}
