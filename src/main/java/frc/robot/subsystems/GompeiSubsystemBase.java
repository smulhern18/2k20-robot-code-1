package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SubsystemConstants;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

/**
 * Subsystem base with better Shuffleboard integration
 */
public abstract class GompeiSubsystemBase extends SubsystemBase {


  /**
   * Adds a String entry to Shuffleboard
   *
   * @param title    String title of entry
   * @param x        vertical location of entry
   * @param y        horizontal location of entry
   * @param width    width of entry
   * @param height   height of entry
   * @param supplier String supplier, called periodically
   */
  public void createStringEntry(String title, int x, int y, int width, int height, Supplier<String> supplier) {
    var widget = SubsystemConstants.TAB.addString(title, supplier);
    widget.withPosition(y, x).withSize(width, height);
  }

  /**
   * Adds a Double entry to Shuffleboard
   *
   * @param title    String title of entry
   * @param x        vertical location of entry
   * @param y        horizontal location of entry
   * @param width    width of entry
   * @param height   height of entry
   * @param supplier Double supplier, called periodically
   */
  public void createDoubleEntry(String title, int x, int y, int width, int height, DoubleSupplier supplier) {
    var widget = SubsystemConstants.TAB.addNumber(title, supplier);
    widget.withPosition(y, x).withSize(width, height);
  }

  /**
   * Adds a Boolean entry to Shuffleboard
   *
   * @param title    String title of entry
   * @param x        vertical location of entry
   * @param y        horizontal location of entry
   * @param width    width of entry
   * @param height   height of entry
   * @param supplier Boolean supplier, called periodically
   */
  public void createBooleanEntry(String title, int x, int y, int width, int height, BooleanSupplier supplier) {
    var widget = SubsystemConstants.TAB.addBoolean(title, supplier);
    widget.withPosition(y, x).withSize(width, height);
  }
}