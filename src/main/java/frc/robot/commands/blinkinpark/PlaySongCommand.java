package frc.robot.commands.blinkinpark;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AbrahamBlinkinSubsystem;
import frc.robot.subsystems.AbrahamBlinkinSubsystem.Hat;

public class PlaySongCommand extends CommandBase {

  private Hat hat;
  private AbrahamBlinkinSubsystem abrahamBlinkinSubsystem;

  /**
   * Plays song for inde
   *
   * @param hat                     hat to wear
   * @param abrahamBlinkinSubsystem the Abraham Blinkin
   */
  public PlaySongCommand(Hat hat, AbrahamBlinkinSubsystem abrahamBlinkinSubsystem) {//to get time out use withOut Function
    this.hat = hat;
    this.abrahamBlinkinSubsystem = abrahamBlinkinSubsystem;
    addRequirements(abrahamBlinkinSubsystem);
  }

  /**
   * Runs command, but with a timeout
   *
   * @param hat                     hat to wear
   * @param timeoutSeconds          length of time to run command in seconds
   * @param abrahamBlinkinSubsystem the Abraham Blinkin
   */
  public PlaySongCommand(Hat hat, double timeoutSeconds, AbrahamBlinkinSubsystem abrahamBlinkinSubsystem) {
    this(hat, abrahamBlinkinSubsystem);
    withTimeout(timeoutSeconds);
  }

  /**
   * Wears the hat
   */
  @Override
  public void initialize() {
    abrahamBlinkinSubsystem.wearHat(hat);
  }
}