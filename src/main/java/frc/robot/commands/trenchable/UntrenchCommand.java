package frc.robot.commands.trenchable;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.TrenchableSubsystem;

public class UntrenchCommand extends CommandBase {
  TrenchableSubsystem trenchableSubsystem;
  ClimberSubsystem climberSubsystem;

  public UntrenchCommand(RobotContainer robotContainer) {
    this.trenchableSubsystem = robotContainer.trenchableSubsystem;
    this.climberSubsystem = robotContainer.climberSubsystem;
    addRequirements(trenchableSubsystem, climberSubsystem);
  }

  @Override
  public void initialize() {
    trenchableSubsystem.untrench();
    climberSubsystem.unslap();
  }

  @Override
  public boolean isFinished() {
    return trenchableSubsystem.getState() == TrenchableSubsystem.TrenchableState.UNTRENCHABLE;
  }
}
