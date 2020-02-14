package frc.robot.models.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

public class Tensiometer extends DigitalInput {
  public Tensiometer(int port) {
    super(port);
  }

  public final boolean isTaut() {
    return get();
  }
}
