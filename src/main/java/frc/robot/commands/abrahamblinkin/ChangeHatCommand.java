package frc.robot.commands.abrahamblinkin;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
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
  public ChangeHatCommand(RobotContainer robotContainer, Hat hat) {//to get time out use withOut Function
    this.hat = hat;
    this.abrahamBlinkinSubsystem = robotContainer.abrahamBlinkinSubsystem;
    addRequirements(abrahamBlinkinSubsystem);
  }

  /**
   * Wears the hat
   */
  @Override
  public void initialize() {
    abrahamBlinkinSubsystem.changeHat(hat);
  }
}