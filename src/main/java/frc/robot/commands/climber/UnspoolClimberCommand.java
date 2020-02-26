package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimberSubsystem;

public class UnspoolClimberCommand extends CommandBase {
  private ClimberSubsystem climberSubsystem;

  public UnspoolClimberCommand(RobotContainer robotContainer) {
    climberSubsystem = robotContainer.climberSubsystem;
    addRequirements(climberSubsystem);
  }

  /**
   * Climb to the top until slack is created
   */
  @Override
  public void execute() {

  }

  /**
   * Ends when the climber reaches the top
   * @return
   */
  @Override
  public boolean isFinished() {
    return true;
  }

  /**
   * Turns off the climber at the end
   * @param interrupted
   */
  @Override
  public void end(boolean interrupted) {
    climberSubsystem.climbOff();
  }
}
