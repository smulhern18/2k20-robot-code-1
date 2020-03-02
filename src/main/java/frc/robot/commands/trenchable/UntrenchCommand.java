package frc.robot.commands.trenchable;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.TrenchableSubsystem;

public class UntrenchCommand extends CommandBase {
  TrenchableSubsystem trenchableSubsystem;
  ClimberSubsystem climberSubsystem;
  boolean done = false;

  /**
   * Creats a new Untrench Command
   * @param robotContainer
   */
  public UntrenchCommand(RobotContainer robotContainer) {
    this.trenchableSubsystem = robotContainer.trenchableSubsystem;
    addRequirements(trenchableSubsystem);
  }


  /**
   * Upon startup, untrench everything
   */
  @Override
  public void initialize() {
    if (trenchableSubsystem.trenchablifier.get() == Constants.TrenchableConstants.UNTRENCHABLE)
      done = true;
    trenchableSubsystem.untrench();
  }

  @Override
  public boolean isFinished() {
    return done;
  }
}
