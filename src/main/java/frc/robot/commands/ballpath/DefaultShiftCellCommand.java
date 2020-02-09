package frc.robot.commands.ballpath;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallPathSubsystem;

public class DefaultShiftCellCommand extends CommandBase {
  BallPathSubsystem ballPathSubsystem;

  public DefaultShiftCellCommand(BallPathSubsystem ballPathSubsystem) {
    this.ballPathSubsystem = ballPathSubsystem;
    addRequirements(ballPathSubsystem);
  }

  /** 
   * Shifts the cells in the indexer
  */
  @Override
  public void execute() {
    if (ballPathSubsystem.getBeltBannerSensor() || ballPathSubsystem.getIndexerState() == BallPathSubsystem.IndexerState.SHIFTING)
      ballPathSubsystem.shiftIndexer();
  }
}
