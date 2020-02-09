package frc.robot.commands.blinkinpark;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AbrahamBlinkinSubsystem;
import frc.robot.subsystems.AbrahamBlinkinSubsystem.Hat;

public class ChangeHatCommand extends CommandBase {

  private Hat hat;
  private AbrahamBlinkinSubsystem abrahamBlinkinSubsystem;

  /**
   * Plays song for inde
   *
   * @param hat hat to wear
   */
  public ChangeHatCommand(Hat hat) {//to get time out use withOut Function
    this.hat = hat;
    this.abrahamBlinkinSubsystem = AbrahamBlinkinSubsystem.getInstance();
    addRequirements(abrahamBlinkinSubsystem);
  }

  /**
   * Runs command, but with a timeout
   *
   * @param hat                     hat to wear
   * @param timeoutSeconds          length of time to run command in seconds
   */
  public ChangeHatCommand(Hat hat, double timeoutSeconds) {
    this(hat);
    withTimeout(timeoutSeconds);
  }

  /**
   * Wears the hat
   */
  @Override
  public void initialize() {
    abrahamBlinkinSubsystem.changeHat(hat);
  }
}