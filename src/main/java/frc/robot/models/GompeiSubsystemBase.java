package frc.robot.models;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SubsystemConstants;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Subsystem base with better Shuffleboard integration
 */
public abstract class GompeiSubsystemBase extends SubsystemBase {
  private static HashMap<String, NetworkTableEntry> entries = new HashMap<>();
  private static HashMap<String, Supplier<String>> stringSuppliers = new HashMap<>();
  private static HashMap<String, Supplier<Double>> doubleSuppliers = new HashMap<>();
  private static HashMap<String, Supplier<Boolean>> booleanSuppliers = new HashMap<>();
  private static HashMap<String, Supplier<Integer>> integerSuppliers = new HashMap<>();


  public void createStringEntry(String title, int x, int y, int width, int height, Supplier<String> supplier) {
    SimpleWidget widget = SubsystemConstants.TAB.add(title, "null");
    widget.withPosition(y, x).withSize(width, height);
    entries.put(title, widget.getEntry());
    stringSuppliers.put(title, supplier);
  }

  public void createDoubleEntry(String title, int x, int y, int width, int height, Supplier<Double> supplier) {
    SimpleWidget widget = SubsystemConstants.TAB.add(title, 0.0d);
    widget.withPosition(y, x).withSize(width, height);
    entries.put(title, widget.getEntry());
    doubleSuppliers.put(title, supplier);
  }

  public void createBooleanEntry(String title, int x, int y, int width, int height, Supplier<Boolean> supplier) {
    SimpleWidget widget = SubsystemConstants.TAB.add(title, false);
    widget.withPosition(y, x).withSize(width, height);
    entries.put(title, widget.getEntry());
    booleanSuppliers.put(title, supplier);
  }

  public void createIntegerEntry(String title, int x, int y, int width, int height, Supplier<Integer> supplier) {
    SimpleWidget widget = SubsystemConstants.TAB.add(title, 0);
    widget.withPosition(y, x).withSize(width, height);
    entries.put(title, widget.getEntry());
    integerSuppliers.put(title, supplier);
  }

  private NetworkTableEntry getEntry(String key) {
    return entries.get(key);
  }

  @Override
  public void periodic() {
    NetworkTableEntry entry;
    for (String key : entries.keySet()) {
      entry = getEntry(key);
      if (stringSuppliers.containsKey(key)) {
        entry.setString(stringSuppliers.get(key).get());
      } else if (doubleSuppliers.containsKey(key)) {
        entry.setDouble(doubleSuppliers.get(key).get());
      } else if (booleanSuppliers.containsKey(key)) {
        entry.setBoolean(booleanSuppliers.get(key).get());
      } else if (integerSuppliers.containsKey(key)) {
        entry.setNumber(integerSuppliers.get(key).get());
      } else {
        System.out.println(key + ": key not found!");
      }
    }
    update();
  }

  public abstract void update();
}