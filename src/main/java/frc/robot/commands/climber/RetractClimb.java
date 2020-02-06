package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

/**
 * Winches up
 */
public class RetractClimb extends CommandBase {
  private ClimberSubsystem climberSubsystem;

  /**
   * Requires climber subsystem
   * @param climberSubsystem the climber subsystem
   */
  public RetractClimb(ClimberSubsystem climberSubsystem) {
    this.climberSubsystem = climberSubsystem;
    addRequirements(climberSubsystem);
  }

  /**
   * Runs a state machine
   * retract -> done
   */
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

  /**
   * Finished if fully retracted
   * @return true if fully retracted
   */
  @Override
  public boolean isFinished() {
    return climberSubsystem.getState() == ClimberSubsystem.ClimbState.DONE;
  }

  /**
   * Turns off climber retracting/extending as a fail-safe
   * @param interrupted
   */
  @Override
  public void end(boolean interrupted) {
    climberSubsystem.climbOff();
  }
}
