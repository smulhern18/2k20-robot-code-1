package frc.robot.models.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

public class BannerSensor extends DigitalInput {
  public BannerSensor(int port) {
    super(port);
  }

  public final boolean beamBroken() {
    return get();
  }
}
