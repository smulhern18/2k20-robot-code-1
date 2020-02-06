package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class RetractClimb extends CommandBase {
  private ClimberSubsystem climberSubsystem;

  public RetractClimb(ClimberSubsystem climberSubsystem) {
    this.climberSubsystem = climberSubsystem;
    addRequirements(climberSubsystem);
  }

  @Override
  public void execute() {
    switch (climberSubsystem.getState()) {
      case SLAPPED:
        climberSubsystem.retractClimb();
        break;
      case DONE:
        climberSubsystem.climbOff();
        break;
      default:
        System.out.println("UNEXPECTED CLIMBER STATE " + climberSubsystem.getState().toString());
        break;
    }
  }

  @Override
  public boolean isFinished() {
    return climberSubsystem.getState() == ClimberSubsystem.ClimbState.DONE;
  }

  @Override
  public void end(boolean interrupted) {
    climberSubsystem.climbOff();
  }
}
