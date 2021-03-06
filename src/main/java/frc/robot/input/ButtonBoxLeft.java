package frc.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class ButtonBoxLeft extends Joystick {
  public JoystickButton extend, slap, retract, traverseLeft, traverseRight,
      resetIndexer, spitIn, spitOut, defaultShooterSpeed, jogTurretLeft, jogTurretRight;

  /**
   * Construct an instance of a joystick. The joystick index is the USB port on the drivers
   * station.,
   *
   * @param port The port on the Driver Station that the joystick is plugged into.
   */
  public ButtonBoxLeft(int port) {
    super(port);
    extend = new JoystickButton(this, 1);
    slap = new JoystickButton(this, 2);
    retract = new JoystickButton(this, 3);
    traverseRight = new JoystickButton(this, 4);
    traverseLeft = new JoystickButton(this, 5);
    jogTurretLeft = new JoystickButton(this, 6);
    jogTurretRight = new JoystickButton(this, 7);
    resetIndexer = new JoystickButton(this, 8);
    defaultShooterSpeed = new JoystickButton(this, 9);
    spitIn = new JoystickButton(this, 11);
    spitOut = new JoystickButton(this, 10);
  }
}
