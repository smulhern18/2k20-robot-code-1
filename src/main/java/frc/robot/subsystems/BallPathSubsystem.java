package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants.BallpathConstants;

public class BallPathSubsystem extends BeefSubsystemBase {
  private WPI_TalonSRX kickerMotor;
  private WPI_TalonSRX indexerMotor;
  private WPI_TalonSRX pathMotor;

  private int amountOfBallsContained = 0;

  /**
   * Creates a new ExampleSubsystem.
   */
  public BallPathSubsystem() {
    kickerMotor = new WPI_TalonSRX(BallpathConstants.KICKER_MOTOR_CHANNEL);
    indexerMotor = new WPI_TalonSRX(BallpathConstants.INDEXER_MOTOR_CHANNEL);
    pathMotor = new WPI_TalonSRX(BallpathConstants.PATH_MOTOR_CHANNEL);

    createDoubleEntry(BallpathConstants.BALLS_CONTAINED_ENTRY, 9, 0, 1, 1, () -> amountOfBallsContained);
  }

  public void setPreloadedBalls(int amountOfPreloadedBalls) {
    amountOfBallsContained = amountOfPreloadedBalls;
  }


}
