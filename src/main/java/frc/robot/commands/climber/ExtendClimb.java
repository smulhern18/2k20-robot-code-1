package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.TrenchableSubsystem;

public class ExtendClimb extends CommandBase {
  private ClimberSubsystem climberSubsystem;
  private TrenchableSubsystem trenchableSubsystem;

  public ExtendClimb(ClimberSubsystem climberSubsystem, TrenchableSubsystem trenchableSubsystem) {
    this.climberSubsystem = climberSubsystem;
    this.trenchableSubsystem = trenchableSubsystem;
    addRequirements(climberSubsystem, trenchableSubsystem);
  }

  @Override
  public void initialize() {
    climberSubsystem.setState(trenchableSubsystem.getState() == TrenchableSubsystem.TrenchableState.TRENCHABLE ? ClimberSubsystem.ClimbState.TRENCHABLE : ClimberSubsystem.ClimbState.UNTRENCHABLE);
  }

  @Override
  public void execute() {
    switch (climberSubsystem.getState()) {
      case TRENCHABLE:
        trenchableSubsystem.untrench();
        break;
      case UNTRENCHABLE:
        climberSubsystem.unslap();
        break;
      case UNSLAPPED:
        climberSubsystem.extendClimb();
        break;
      case EXTENDED:
        break;
      default:
        System.out.println("UNEXPECTED CLIMBER STATE " + climberSubsystem.getState().toString());
        break;
    }
  }

  @Override
  public boolean isFinished() {
    return climberSubsystem.getState() == ClimberSubsystem.ClimbState.EXTENDED;
  }

  @Override
  public void end(boolean interrupted) {
    climberSubsystem.climbOff();
  }
}
