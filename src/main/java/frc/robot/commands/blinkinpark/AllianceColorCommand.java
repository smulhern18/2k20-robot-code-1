package frc.robot.commands.blinkinpark;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.CommandBase;
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
   *
   * @param abrahamBlinkinSubsystem the Abraham Blinkin
   */
  public AllianceColorCommand(AbrahamBlinkinSubsystem abrahamBlinkinSubsystem) {
    this.abrahamBlinkinSubsystem = abrahamBlinkinSubsystem;
    addRequirements(abrahamBlinkinSubsystem);
  }

  /**
   * Sets the color to the alliance color
   */
  @Override
  public void initialize() {
    if (alliance != null) {
      setColor();
    }
  }

  /**
   * Changes the color if the alliance has changed
   */
  @Override
  public void execute() {
    Alliance newAlliance = DriverStation.getInstance().getAlliance();
    if (newAlliance != alliance) {
      alliance = newAlliance;
      setColor();
    }
  }

  /**
   * Sets the color depending on the alliance
   */
  private void setColor() {
    if (alliance == Alliance.Blue) {
      abrahamBlinkinSubsystem.wearHat(Hat.BPMOcean);
    } else if (alliance == Alliance.Red) {
      abrahamBlinkinSubsystem.wearHat(Hat.BPMLava);
    } else {
      abrahamBlinkinSubsystem.wearHat(Hat.RainbowForest);
    }
  }
}
