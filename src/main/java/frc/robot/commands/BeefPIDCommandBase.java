package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public abstract class BeefPIDCommandBase extends CommandBase {
  public PIDController pidController;
  private double output, tolerance, p, i, d;
  private double count = 0;
  private String errorTitle;
  public BeefPIDCommandBase(double p, double i, double d, double tolerance, String errorTitle) {
    this.tolerance = tolerance;
    this.errorTitle = errorTitle;
    this.p = p;
    this.i = i;
    this.d = d;
  }

  @Override
  public void initialize() {
    pidController = new PIDController(p, i, d);
    setTolerance(tolerance);
    count = 0;
  }

  @Override
  public final void execute() {
    output = pidController.calculate(getInput(), getSetpoint());
    logError(pidController.getPositionError());
    usePIDOutput(output);
  }

  @Override
  public boolean isFinished() {
    if (atSetpoint())
      count++;
    else
      count = 0;
    return count > 5;
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("PID Ended");
  }

  public final void setTolerance(double threshold) {
    pidController.setTolerance(threshold);
  }

  public final boolean atSetpoint() {
    return pidController.atSetpoint();
  }

  public final void logError(double error) {
    SmartDashboard.putNumber(errorTitle, error);
//    System.out.println(errorTitle +" "+error);
  }

  public abstract void usePIDOutput(double output);

  public abstract double getInput();

  public abstract double getSetpoint();
}
