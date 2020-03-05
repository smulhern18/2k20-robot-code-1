package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public abstract class BeefPIDCommandBase extends CommandBase {
  public PIDController pidController;
  private double output;
  private String errorTitle;
  public BeefPIDCommandBase(double p, double i, double d, double tolerance, String errorTitle) {
    pidController = new PIDController(p, i, d);
    setTolerance(tolerance);
    this.errorTitle = errorTitle;
  }

  @Override
  public final void execute() {
    output = pidController.calculate(getInput(), getSetpoint());
    logError(pidController.getPositionError());
    usePIDOutput(output);
  }

  @Override
  public boolean isFinished() {
    return atSetpoint();
  }

  public final void setTolerance(double threshold) {
    pidController.setTolerance(threshold);
  }

  public final boolean atSetpoint() {
    return pidController.atSetpoint();
  }

  public final void logError(double error) {
    SmartDashboard.putNumber(errorTitle, error);
  }

  public abstract void usePIDOutput(double output);

  public abstract double getInput();

  public abstract double getSetpoint();
}
