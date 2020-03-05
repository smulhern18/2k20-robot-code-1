package frc.robot.commands.trenchable;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.CollectorSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TrenchableSubsystem;

public class TrenchCommand extends CommandBase {
  private TrenchableSubsystem trenchableSubsystem;
  private ClimberSubsystem climberSubsystem;
  private RobotContainer robotContainer;
  private ShooterSubsystem shooterSubsystem;

  /**
   * Creates a new Trench Command
   * @param robotContainer
   */
  public TrenchCommand(RobotContainer robotContainer) {
    shooterSubsystem = robotContainer.shooterSubsystem;
    this.trenchableSubsystem = robotContainer.trenchableSubsystem;
    this.climberSubsystem = robotContainer.climberSubsystem;
    addRequirements(trenchableSubsystem, climberSubsystem);
  }

  /**
   * Turn off shooter and trench
   */
  @Override
  public void initialize() {
    shooterSubsystem.stop();
    trenchableSubsystem.trench();
    climberSubsystem.slap();
  }

  /**
   * Decides when the command is finished
   * @return if the subsystem is finished trenching
   */
  @Override
  public boolean isFinished() {
    return trenchableSubsystem.trenchablifier.get();
  }
}
