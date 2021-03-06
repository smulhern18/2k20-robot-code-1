package frc.robot.commands.abrahamblinkin;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.AbrahamBlinkinSubsystem;
import frc.robot.subsystems.AbrahamBlinkinSubsystem.Hat;

/**
 * Sets LEDs to the color of the robot's alliance
 */
public class AllianceColorCommand extends CommandBase {
  private AbrahamBlinkinSubsystem abrahamBlinkinSubsystem;
  private Alliance alliance;

  /**
   * Requires the blinkin
   */
  public AllianceColorCommand(RobotContainer robotContainer) {
    this.abrahamBlinkinSubsystem = robotContainer.abrahamBlinkinSubsystem;
    addRequirements(abrahamBlinkinSubsystem);
  }

  /**
   * Sets the color to the alliance color
   */
  @Override
  public void initialize() {
    setColor();
  }

  /**
   * Changes the color if the alliance has changed
   */
  @Override
  public void execute() {
    Alliance newAlliance = DriverStation.getInstance().getAlliance();
    if (newAlliance != alliance) {
      alliance = newAlliance;
    }
    setColor();
  }

  /**
   * Sets the color depending on the alliance
   */
  private void setColor() {
    if (alliance == Alliance.Blue) {
      abrahamBlinkinSubsystem.changeHat(Hat.BPMOcean);
    } else if (alliance == Alliance.Red) {
      abrahamBlinkinSubsystem.changeHat(Hat.LightChaseRed);
    } else {
      abrahamBlinkinSubsystem.changeHat(Hat.RainbowForest);
    }
  }
}
