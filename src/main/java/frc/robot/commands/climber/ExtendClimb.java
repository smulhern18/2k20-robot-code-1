package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.TrenchableSubsystem;

/**
 * First step of climb sequence
 */
public class ExtendClimb extends CommandBase {
  private ClimberSubsystem climberSubsystem;
  private TrenchableSubsystem trenchableSubsystem;

  /**
   * Requires climber and trenchable subsystem.
   */
  public ExtendClimb() {
    this.climberSubsystem = ClimberSubsystem.getInstance();
    this.trenchableSubsystem = TrenchableSubsystem.getInstance();
    addRequirements(climberSubsystem, trenchableSubsystem);
  }

  /**
   * Sets the climber state to the trenchable state
   */
  @Override
  public void initialize() {
    climberSubsystem.setState(trenchableSubsystem.getState() == TrenchableSubsystem.TrenchableState.TRENCHABLE ? ClimberSubsystem.ClimbState.TRENCHABLE : ClimberSubsystem.ClimbState.UNTRENCHABLE);
  }

  /**
   * Runs a state machine
   * untrench -> unslap -> extend -> done
   */
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

  /**
   * Sees if climb is extended
   *
   * @return true if climber is fully extended
   */
  @Override
  public boolean isFinished() {
    return climberSubsystem.getState() == ClimberSubsystem.ClimbState.EXTENDED;
  }

  /**
   * Turns off extending motor as a fail-safe
   *
   * @param interrupted unused
   */
  @Override
  public void end(boolean interrupted) {
    climberSubsystem.climbOff();
  }
}
