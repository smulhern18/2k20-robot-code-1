package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;
import frc.robot.Constants.BallpathConstants;

public class BallPathSubsystem extends BeefSubsystemBase {
  private WPI_TalonSRX kickerMotor, indexerMotor, firstStageMotor;

  private int amountOfBallsContained = 0;

  private DigitalInput firstStageBannerSensor,indexer1BannerSensor, indexer2BannerSensor, indexer3BannerSensor, indexer4BannerSensor, indexer5BannerSensor, kickerBannerSensor;

  /**
   * Creates a new ExampleSubsystem.
   */
  public BallPathSubsystem() {

    kickerMotor = new WPI_TalonSRX(BallpathConstants.KICKER_MOTOR_CHANNEL);
    indexerMotor = new WPI_TalonSRX(BallpathConstants.INDEXER_MOTOR_CHANNEL);
    firstStageMotor = new WPI_TalonSRX(BallpathConstants.FIRST_STAGE_MOTOR_CHANNEL);

    firstStageBannerSensor = new DigitalInput(BallpathConstants.FIRST_STAGE_BANNER_CHANNEL);
    indexer1BannerSensor = new DigitalInput(BallpathConstants.INDEXER1_BANNER_CHANNEL);
    indexer2BannerSensor = new DigitalInput(BallpathConstants.INDEXER2_BANNER_CHANNEL);
    indexer3BannerSensor = new DigitalInput(BallpathConstants.INDEXER3_BANNER_CHANNEL);
    indexer4BannerSensor = new DigitalInput(BallpathConstants.INDEXER4_BANNER_CHANNEL);
    indexer5BannerSensor = new DigitalInput(BallpathConstants.INDEXER5_BANNER_CHANNEL);
    kickerBannerSensor = new DigitalInput(BallpathConstants.KICKER_BANNER_CHANNEL);

    createDoubleEntry(BallpathConstants.BALLS_CONTAINED_ENTRY, 9, 0, 1, 1, () -> amountOfBallsContained);
  }

  public void setPreloadedBalls(int amountOfPreloadedBalls) {
    amountOfBallsContained = amountOfPreloadedBalls;
  }


}
