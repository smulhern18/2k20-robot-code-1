package frc.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class ButtonBoxRight extends Joystick {
  public JoystickButton autoTarget, collect, shoot, trenchable, rotationControl, positionControl, manualColorWheelRotation;


  /**
   * Construct an instance of a joystick. The joystick index is the USB port on the drivers
   * station.
   *
   * @param port The port on the Driver Station that the joystick is plugged into.
   */
  public ButtonBoxRight(int port) {
    super(port);
    autoTarget = new JoystickButton(this, 1);
    collect = new JoystickButton(this, 2);
    shoot = new JoystickButton(this, 3);
    trenchable = new JoystickButton(this, 4);
    rotationControl = new JoystickButton(this, 5);
    positionControl = new JoystickButton(this, 6);
    manualColorWheelRotation = new JoystickButton(this, 7);
  }
}
