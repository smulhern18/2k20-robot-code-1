package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimberSubsystem;

public class RetractClimbCommand extends CommandBase {
  private ClimberSubsystem climberSubsystem;

  /**
   * Creates a new RetractClimbCommand
   * @param robotContainer
   */
  public RetractClimbCommand(RobotContainer robotContainer) {
    climberSubsystem = robotContainer.climberSubsystem;
    addRequirements(climberSubsystem);
  }

  /**
   * Spools the climber subsystem
   */
  @Override
  public void execute() {
    climberSubsystem.spool();
  }

  /**
   * Finishes when the climber reaches the bottom
   * @return climberSubsystem.atBottom()
   */
  @Override
  public boolean isFinished() {
    return climberSubsystem.atBottom();
  }

  /**
   * Turns the climber off
   * @param interrupted
   */
  @Override
  public void end(boolean interrupted) {
    climberSubsystem.climbOff();
  }
}
