package frc.robot.commands.collector;

import frc.robot.subsystems.ExampleSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class CollectCommand extends CommandBase{
  private final ExampleSubsystem m_subsystem;

  public CollectCommand(ExampleSubsystem subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}